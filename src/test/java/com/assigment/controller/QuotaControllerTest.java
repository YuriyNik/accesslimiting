package com.assigment.controller;
import com.assigment.service.QuotaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

@WebMvcTest(QuotaController.class)
@AutoConfigureMockMvc
public class QuotaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuotaService quotaService;
    @Test
    public void testConsumeQuotaSuccess() throws Exception {
        String userId = "user1";
        when(quotaService.consumeQuota(userId)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/rateLimit/consumeQuota/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Quota consumed successfully"));
    }

    @Test
    public void testConsumeQuotaExceeded() throws Exception {
        String userId = "user2";
        when(quotaService.consumeQuota(userId)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/rateLimit/consumeQuota/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isTooManyRequests())
                .andExpect(MockMvcResultMatchers.content().string("Quota exceeded"));
    }

    @Test
    public void testGetUsersQuota() throws Exception {
        Map<String, Integer> expectedQuota = new HashMap<>();
        expectedQuota.put("user1", 3);
        when(quotaService.getUsersQuota()).thenReturn(expectedQuota);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/rateLimit/getUsersQuota")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{\"user1\":3}"));
    }
}
