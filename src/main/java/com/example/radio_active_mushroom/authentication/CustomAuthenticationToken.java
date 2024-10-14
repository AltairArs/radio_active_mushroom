package com.example.radio_active_mushroom.authentication;

import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public class CustomAuthenticationToken implements Authentication {

    private boolean isAuthenticated;

    private CustomUserDetails userDetails;

    private String password;

    private String username;

    private String email;

    public CustomAuthenticationToken(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isAuthenticated = false;
    }

    public CustomAuthenticationToken(CustomUserDetails userDetails) {
        this.userDetails = userDetails;
        this.isAuthenticated = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (userDetails != null) {
            return userDetails.getAuthorities();
        } else {
            return null;
        }
    }

    @Override
    public Object getCredentials() {
        return userDetails.getPassword();
    }

    @Override
    public Object getDetails() {
        return userDetails.isEnabled();
    }

    @Override
    public Object getPrincipal() {
        return new String[]{userDetails.getUsername(), userDetails.getEmail()};
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new IllegalArgumentException("Not supported, use constructor");
    }

    @Override
    public String getName() {
        return userDetails.getUsername();
    }
}
