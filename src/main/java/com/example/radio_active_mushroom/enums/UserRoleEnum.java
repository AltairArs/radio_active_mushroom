package com.example.radio_active_mushroom.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;

public enum UserRoleEnum {
    ROLE_USER, ROLE_STAFF, ROLE_ADMIN, ROLE_MARKETER, ROLE_DB_DRAWER_EDITOR;

    /*
    Permission:
    [Role_Identifiers]__[Permission_Name]
    Role_Identifiers:
        USER                _U_
        STAFF               _S_
        ADMIN               _A_
        MARKETER            _M_
        DB_DRAWER_EDITOR    _D_
    Example:
    _U_S__PERMISSION
     */
    private final static String[] permissions = new String[]{
            "_U_S_A_M_D__PROFILE",
            "_U_S_A_M_D__LOGOUT"
    };

    private Collection<SimpleGrantedAuthority> getPermissions(String id) {
        return Arrays.stream(permissions)
            .filter(authority -> authority.contains(id))
            .map(permission -> new SimpleGrantedAuthority(permission.substring(permission.lastIndexOf("__") + 2)))
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
