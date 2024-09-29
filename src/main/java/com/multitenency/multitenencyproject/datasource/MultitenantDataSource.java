package com.multitenency.multitenencyproject.datasource;

import com.multitenency.multitenencyproject.context.TenantContext;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultitenantDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String tenant = TenantContext.getCurrentTenant();
        if (tenant == null) {
            throw new IllegalStateException("No tenant selected!");
        }
        return tenant;
    }
}