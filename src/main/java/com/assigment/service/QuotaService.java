package com.assigment.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class QuotaService {

    @Value("${app.maxRequestsPerUser:5}")
    private int maxRequestsPerUser=6;
    private Map<String, Integer> userRequestCount = new HashMap<>();

    public boolean consumeQuota(String userId) {
        int userRequests = userRequestCount.getOrDefault(userId, 0);
        if (userRequests < maxRequestsPerUser) {
            userRequestCount.put(userId, userRequests + 1);
            return true;
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

