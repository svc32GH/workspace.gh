package com.svc32.common.ss.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/getUserRights").hasAnyRole("ADMIN_OWNER", "CASE_OWNER")
                        .requestMatchers("/getUserList").hasAnyRole("CASE_OWNER", "SUPPORTER")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(ExternalAuthenticationProvider provider) {
        return new ProviderManager(provider);
    }

    @Bean
    public UserDetailsService users(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("password"))
                .roles("ADMIN_OWNER")
                .build();

        UserDetails caseOwner = User.builder()
                .username("case")
                .password(passwordEncoder.encode("password"))
                .roles("CASE_OWNER")
                .build();

        UserDetails supporter = User.builder()
                .username("support")
                .password(passwordEncoder.encode("password"))
                .roles("SUPPORTER")
                .build();

        return new InMemoryUserDetailsManager(admin, caseOwner, supporter);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
