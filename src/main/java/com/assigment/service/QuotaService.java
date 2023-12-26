package com.assigment.service;

import com.assigment.repository.ActiveRepository;
import com.assigment.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class QuotaService {
    private static final Logger log = LoggerFactory.getLogger(QuotaService.class);
    @Value("${app.maxRequestsPerUser:5}")
    private int maxRequestsPerUser=5;

    @Autowired
    private ActiveRepository activeRepository;

    private Map<String, Integer> userRequestCount = new HashMap<>();

    public boolean consumeQuota(String userId) {
        Optional<User> optionalUser = activeRepository.getRepository().findById(userId);
        if (optionalUser.isPresent()) {
            int userRequests = userRequestCount.getOrDefault(userId, 0);
            if (userRequests < maxRequestsPerUser) {
                User user = optionalUser.get();
                user.setLastLoginTimeUtc(LocalDateTime.now());
                activeRepository.getRepository().save(user);
                userRequestCount.put(userId, userRequests + 1);
                return true;
            }
        }
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

