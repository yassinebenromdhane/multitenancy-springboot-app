package com.multitenency.multitenencyproject.controllers;

import com.multitenency.multitenencyproject.context.TenantContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TenantController {
    @GetMapping("/current-tenant")
    public String getCurrentTenant(@RequestHeader(value = "X-TenantID", required = false) String tenantId) {
        String currentTenant = TenantContext.getCurrentTenant();
        if (currentTenant != null && !currentTenant.isEmpty()) {
            return "Current tenant database: " + currentTenant;
        } else {
            return "No tenant specified!";
        }
    }
}
