package com.assigment.config;

import com.assigment.service.QuotaService;
import com.assigment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class QuotaResetConfig {
    @Autowired
    private QuotaService quotaService;

    @Scheduled(cron = "0 * * * * *") // Reset quotas daily at midnight
    public void resetQuotas() {
        quotaService.resetAllQuotes();
    }
}
