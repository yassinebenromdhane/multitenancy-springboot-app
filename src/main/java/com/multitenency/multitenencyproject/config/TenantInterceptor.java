package com.multitenency.multitenencyproject.config;

import com.multitenency.multitenencyproject.context.TenantContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class TenantInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String tenantId = request.getHeader("X-TenantID"); // Example of getting tenant from header
        if (tenantId != null) {
            TenantContext.setCurrentTenant(tenantId);
        } else {
            TenantContext.clear(); // No tenant selected
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        TenantContext.clear();
    }
}
