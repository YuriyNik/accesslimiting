package com.assigment.aspect;

import com.assigment.exceptions.RateLimitExceededException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RateLimitAspect {
    private static final Logger log = LoggerFactory.getLogger(RateLimitAspect.class);

    private RateLimiter rateLimiter = new RateLimiter(5);

    @Before("@annotation(rateLimited)")
    public void enforceRateLimit(RateLimited rateLimited) {
        String key = rateLimited.key();
        if (!rateLimiter.tryConsume(key)) {
            log.info("Rate limit exceeded for key: " + key);
            throw new RateLimitExceededException("Rate limit exceeded for key: " + key);
        }
    }
}
