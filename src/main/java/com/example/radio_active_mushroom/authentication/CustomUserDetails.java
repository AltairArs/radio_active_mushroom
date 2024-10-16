package com.example.radio_active_mushroom.authentication;

import com.example.radio_active_mushroom.enums.UserRoleEnum;
import com.example.radio_active_mushroom.models.documents.ThemeDocument;
import com.example.radio_active_mushroom.services.UserProfileService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class CustomUserDetails implements UserDetails {

    private String username;

    private String password;

    private String email;

    private UserRoleEnum role;

    private boolean is_active;

    private UserProfileService userProfileService;

    private ThemeDocument getTheme() {
        return userProfileService.GetUserTheme(username);
    }

    public String getColorization() {
        return getTheme().getColorization().name();
    }

    public String getMode() {
        return getTheme().getMode().name();
    }

    public String getColor() {
        return getTheme().getColor().name();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isEnabled() {
        return is_active;
    }
}
