package com.example.radio_active_mushroom.authentication;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private final CustomAuthenticationService authenticationService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("[AUTHENTICATION] Start authentication by provider");
        return authenticationService.authenticate((CustomAuthenticationToken) authentication);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(CustomAuthenticationToken.class);
    }
}
