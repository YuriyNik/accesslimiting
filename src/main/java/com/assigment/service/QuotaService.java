package com.assigment.service;

import com.assigment.model.User;
import com.assigment.repository.ActiveRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class QuotaService {
    private static final Logger log = LoggerFactory.getLogger(QuotaService.class);
  //  @Value("${app.maxRequestsPerUser:5}")
    private int maxRequestsPerUser=5;

    @Autowired
    private ActiveRepository activeRepository;

    private ConcurrentMap<String, Integer> userRequestCount = new ConcurrentHashMap<>();

    public boolean consumeQuota(String userId) {
       Optional<User> optionalUser = activeRepository.getRepository().findById(userId);
        if (optionalUser.isPresent()) {
            int userRequests = Objects.requireNonNullElse(userRequestCount.putIfAbsent(userId, 0),0);
            if (userRequests < maxRequestsPerUser) {
                User user = optionalUser.get();
                user.setLastLoginTimeUtc(LocalDateTime.now());
                activeRepository.getRepository().save(user);
                return consumeQuota(userId,userRequests);
            }
            log.info("Quota exceeded for "+userId);
        } else log.info(userId+" not registered");
        return false;
    }
    // method updates user's quota via CAS. check that quota is not updated by parallel request or not exceeded yet.
    public boolean consumeQuota(String userId, int userRequests) {
        if (userRequestCount.getOrDefault(userId, 0)<maxRequestsPerUser) {
               if (userRequestCount.replace(userId,userRequests, userRequests + 1)){
                    log.info("Quota updated for "+userId+";requests now "+(userRequests+1));
                    return true;
               }
               //quota updated
                 else consumeQuota(userId,Objects.requireNonNullElse(userRequestCount.putIfAbsent(userId, 0),0));
            }
        log.info("Quota exceeded for "+userId);
      return false;
    }

    public void resetQuotaForUser(String userId){
        userRequestCount.put(userId,0);
    }

    public void resetAllQuotes(){
        userRequestCount.clear();
        log.info("resetAllQuotes");
    }

    public Map<String, Integer> getUsersQuota() {
        return Collections.unmodifiableMap(userRequestCount);
    }

    public void setMaxRequestsPerUser(int maxRequestsPerUser) {
        this.maxRequestsPerUser = maxRequestsPerUser;
    }

    public int getMaxRequestsPerUser() {
        return maxRequestsPerUser;
    }
}

