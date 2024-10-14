package com.example.radio_active_mushroom.authentication;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@AllArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter {
    public static final String SPRING_SECURITY_FORM_EMAIL_KEY = "email";
    public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";
    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";

    private final AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String email = request.getParameter(SPRING_SECURITY_FORM_EMAIL_KEY);
        String password = request.getParameter(SPRING_SECURITY_FORM_PASSWORD_KEY);
        String username = request.getParameter(SPRING_SECURITY_FORM_USERNAME_KEY);

        email = email == "" ? null : email;
        username = username == "" ? null : username;
        password = password == "" ? null : password;

        log.info("[AUTHENTICATION] Start executing custom authentication filter " + request.getRequestURI());

        if (password == null || (email == null && username == null)) {
            log.error("[AUTHENTICATION] Did not find password or email and username");
            filterChain.doFilter(request, response);
            return;
        }

        try {
            log.info("[AUTHENTICATION] Find: username=" + username + ", email=" + email + ", pswd length=" + password.length());
            Authentication auth = authenticationManager.authenticate(new CustomAuthenticationToken(username, password, email));
            SecurityContextHolder.getContext().setAuthentication(auth);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error("[AUTHENTICATION] Custom authentication failed", e);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            //filterChain.doFilter(request, response);
        }
    }
}
