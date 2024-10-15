package com.example.radio_active_mushroom.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;

public enum UserRoleEnum {
    ROLE_USER, ROLE_STAFF, ROLE_ADMIN, ROLE_MARKETER, ROLE_DB_DRAWER_EDITOR;

    private final static String[] permissions = new String[]{
            "_U_S_A_M_D__PROFILE",
            "_U_S_A_M_D__LOGOUT"
    };

    private Collection<SimpleGrantedAuthority> getPermissions(String id) {
        return Arrays.stream(permissions)
            .filter(authority -> authority.contains(id))
            .map(permission -> new SimpleGrantedAuthority(permission.substring(permission.lastIndexOf("__") + 1)))
            .toList();
    }

    public Collection<SimpleGrantedAuthority> getAuthorities() {
        return switch (this) {
            case ROLE_USER -> getPermissions("_U_");
            case ROLE_STAFF -> getPermissions("_S_");
            case ROLE_ADMIN -> getPermissions("_A_");
            case ROLE_MARKETER -> getPermissions("_M_");
            case ROLE_DB_DRAWER_EDITOR -> getPermissions("_D_");
        };
    }
}
