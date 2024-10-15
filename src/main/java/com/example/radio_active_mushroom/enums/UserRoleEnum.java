package com.example.radio_active_mushroom.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

public enum UserRoleEnum {
    ROLE_USER, ROLE_STAFF, ROLE_ADMIN, ROLE_MARKETER, ROLE_DB_DRAWER_EDITOR;

    public Collection<SimpleGrantedAuthority> getAuthorities() {
        return null;
    }
}
