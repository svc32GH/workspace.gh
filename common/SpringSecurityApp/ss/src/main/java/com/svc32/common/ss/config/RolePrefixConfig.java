package com.svc32.common.ss.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.core.GrantedAuthorityDefaults;

@Configuration
public class RolePrefixConfig {
    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(""); // убираем префикс ROLE_
    }
}