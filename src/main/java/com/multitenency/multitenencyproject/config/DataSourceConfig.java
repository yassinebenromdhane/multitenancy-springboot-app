package com.multitenency.multitenencyproject.config;
import com.multitenency.multitenencyproject.datasource.MultitenantDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.jdbc.DataSourceBuilder;

@Configuration
public class DataSourceConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        Map<Object, Object> dataSourceMap = new HashMap<>();

        // Configure master database
        DataSource masterDataSource = DataSourceBuilder.create()
                .url(env.getProperty("spring.datasource.url"))
                .username("postgres") // Add your master DB username
                .password("postgres") // Add your master DB password
                .build();

        // Configure data source for tenant1
        DataSource tenant1DataSource = DataSourceBuilder.create()
                .url(env.getProperty("spring.datasource.tenant1.url"))
                .username(env.getProperty("spring.datasource.tenant1.username"))
                .password(env.getProperty("spring.datasource.tenant1.password"))
                .build();
        dataSourceMap.put("tenant1", tenant1DataSource);

        // Configure data source for tenant2
        DataSource tenant2DataSource = DataSourceBuilder.create()
                .url(env.getProperty("spring.datasource.tenant2.url"))
                .username(env.getProperty("spring.datasource.tenant2.username"))
                .password(env.getProperty("spring.datasource.tenant2.password"))
                .build();
        dataSourceMap.put("tenant2", tenant2DataSource);

        // Set the routing data source
        MultitenantDataSource multitenantDataSource = new MultitenantDataSource();
        multitenantDataSource.setTargetDataSources(dataSourceMap);

        return multitenantDataSource;
    }
}
