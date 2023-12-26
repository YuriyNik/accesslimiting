package com.assigment.service;

import com.assigment.repository.ActiveRepository;
import com.assigment.repository.PrintUserRepository;
import com.assigment.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuotaServiceTest {


    @Mock
    private UserRepository userRepository;

    @MockBean
    private PrintUserRepository printUserRepository;

    @MockBean
    private ActiveRepository activeRepository;
    @MockBean
    private QuotaService quotaService;

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
