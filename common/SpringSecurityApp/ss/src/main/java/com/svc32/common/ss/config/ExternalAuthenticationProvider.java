package com.svc32.common.ss.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExternalAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // Здесь должна быть интеграция с CyberArk, LDAP и пр.
        // В примере — эмуляция
        if ("admin".equals(username) && "pwd".equals(password)) {
            return new UsernamePasswordAuthenticationToken(username, password,
                    List.of(new SimpleGrantedAuthority("ADMIN_OWNER")));
        }
        if ("case".equals(username) && "pwd".equals(password)) {
            return new UsernamePasswordAuthenticationToken(username, password,
                    List.of(new SimpleGrantedAuthority("CASE_OWNER")));
        }
        if ("support".equals(username) && "pwd".equals(password)) {
            return new UsernamePasswordAuthenticationToken(username, password,
                    List.of(new SimpleGrantedAuthority("SUPPORTER")));
        }

        throw new RuntimeException("Неверный логин или пароль");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}