package com.assigment.service;

import com.assigment.config.ActiveRepository;
import com.assigment.model.User;
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
    @Value("${app.maxRequestsPerUser:5}")
    private int maxRequestsPerUser=6;

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
        System.out.println("resetAllQuotes");
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

