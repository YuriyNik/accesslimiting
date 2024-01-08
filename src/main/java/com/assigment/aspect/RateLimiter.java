package com.assigment.aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class RateLimiter {
    private static final Logger log = LoggerFactory.getLogger(RateLimiter.class);

    private final int limit;
    private final Map<String, Long> lastRequestTimestamps = new HashMap<>();

    public RateLimiter(int limit) {
        this.limit = limit;
    }

    public boolean tryConsume(String key) {
        long now = System.currentTimeMillis();
        lastRequestTimestamps.putIfAbsent(key, now);

        long lastRequestTimestamp = lastRequestTimestamps.get(key);
        if (now - lastRequestTimestamp > 1000) {
            lastRequestTimestamps.put(key, now);
            log.info("limit consumed for key="+key);
            return true;
        }

        return false;
    }
}
