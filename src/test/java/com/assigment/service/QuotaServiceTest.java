package com.assigment.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuotaServiceTest {
    @InjectMocks
    private QuotaService quotaService;

    @Test
    void testMaxRequestsPerUser() {
        assertEquals(6,quotaService.getMaxRequestsPerUser());
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
