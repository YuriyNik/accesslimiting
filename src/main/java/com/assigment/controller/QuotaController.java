package com.assigment.controller;

import com.assigment.service.QuotaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api/rateLimit")
public class QuotaController {
    private static final Logger log = LoggerFactory.getLogger(QuotaController.class);

    @Autowired
    private QuotaService quotaService;

    @GetMapping("/consumeQuota/{userId}")
    public ResponseEntity<String> consumeQuota(@PathVariable String userId) {
        if (quotaService.consumeQuota(userId)) {
            return ResponseEntity.ok("Quota consumed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Quota exceeded");
        }
    }

    @GetMapping("/getUsersQuota")
    public ResponseEntity<Map<String, Integer>> getUsersQuota() {
        Map<String, Integer> usersQuota = quotaService.getUsersQuota();
        return ResponseEntity.ok(usersQuota);
    }
}
