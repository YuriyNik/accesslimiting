package com.assigment.config;

import com.assigment.service.QuotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuotaResetConfig {
    @Autowired
    private QuotaService quotaService;

  //  @Scheduled(fixedRate = 15000) // Reset quotas each 15 secs
    public void resetQuotas() {
        quotaService.resetAllQuotes();
    }
}
