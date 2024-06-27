package com.grey.saas.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.MappedInterceptor;

@Configuration
public class WebConfig {

    @Bean
    public MappedInterceptor interceptor() {
        String[] includeUrls = {"/**"};
        String[] excludeUrls = {"/tenants/**"};
        return new MappedInterceptor(includeUrls, excludeUrls, new TenantInterceptor());
    }

}
