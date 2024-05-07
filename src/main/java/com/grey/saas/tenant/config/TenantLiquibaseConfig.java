package com.grey.saas.tenant.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

@Lazy(false)
@Configuration
@ConditionalOnProperty(name = "spring.liquibase.enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(LiquibaseProperties.class)
public class TenantLiquibaseConfig {

    @Primary
    @Bean
    @ConfigurationProperties("spring.liquibase")
    public LiquibaseProperties tenantLiquibaseProperties() {
        return new LiquibaseProperties();
    }

    @Bean
    public DynamicDataSourceBasedMultiTenantSpringLiquibase tenantLiquibase() {
        return new DynamicDataSourceBasedMultiTenantSpringLiquibase();
    }

}
