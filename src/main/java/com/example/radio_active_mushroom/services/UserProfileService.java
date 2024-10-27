package com.example.radio_active_mushroom.services;

import com.example.radio_active_mushroom.dto.entity.UserSettingsDto;
import com.example.radio_active_mushroom.models.documents.ThemeDocument;
import com.example.radio_active_mushroom.models.entity.UserEntity;

public interface UserProfileService {
    public abstract ThemeDocument getUserTheme(String username);
    public abstract void saveUserSettings(UserSettingsDto userSettings, String username);
    public abstract UserSettingsDto getUserSettings(String username);
    public abstract UserEntity getUserEntity(String username);
}
