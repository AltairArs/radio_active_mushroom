package com.example.radio_active_mushroom.services;

import com.example.radio_active_mushroom.dto.UserSettingsDto;
import com.example.radio_active_mushroom.models.documents.ThemeDocument;

public interface UserProfileService {
    public abstract ThemeDocument GetUserTheme(String username);
    public abstract void SaveUserSettings(UserSettingsDto userSettings, String username);
    public abstract UserSettingsDto GetUserSettings(String username);
}
