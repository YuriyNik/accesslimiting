package com.assigment.service;

import com.assigment.repository.ActiveRepository;
import com.assigment.repository.PrintUserRepository;
import com.assigment.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuotaServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PrintUserRepository printUserRepository;

    @Autowired
    private ActiveRepository activeRepository;
    @Autowired
    private QuotaService quotaService;

    @BeforeAll
    public static void init(){

    }

    @Test
    void testMaxRequestsPerUser() {
        assertEquals(5,quotaService.getMaxRequestsPerUser());
    }

    @Test
    void testConsumeQuota() {
        String userId = "user1";
        quotaService.setMaxRequestsPerUser(3);
        assertTrue(quotaService.consumeQuota(userId));
        assertTrue(quotaService.consumeQuota(userId));
        assertTrue(quotaService.consumeQuota(userId));
        assertFalse(quotaService.consumeQuota(userId)); // Should return false, as the limit is reached
    }

    @Test
    void testResetQuotaForUser() {
        String userId = "user1";

        quotaService.consumeQuota(userId);
        quotaService.resetQuotaForUser(userId);

        assertEquals(0, quotaService.getUsersQuota().get(userId));
    }

    @Test
    void testResetAllQuotes() {
        String userId1 = "user1";
        String userId2 = "user2";

        quotaService.consumeQuota(userId1);
        quotaService.consumeQuota(userId2);
        quotaService.resetAllQuotes();

        assertTrue(quotaService.getUsersQuota().isEmpty());
    }
}
