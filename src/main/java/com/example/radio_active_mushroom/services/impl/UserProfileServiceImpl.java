package com.example.radio_active_mushroom.services.impl;

import com.example.radio_active_mushroom.dto.UserSettingsDto;
import com.example.radio_active_mushroom.enums.theme.ThemeColorEnum;
import com.example.radio_active_mushroom.enums.theme.ThemeColorizationEnum;
import com.example.radio_active_mushroom.enums.theme.ThemeModeEnum;
import com.example.radio_active_mushroom.models.documents.ThemeDocument;
import com.example.radio_active_mushroom.models.entity.UserEntity;
import com.example.radio_active_mushroom.repo.documents.ThemeDocumentRepository;
import com.example.radio_active_mushroom.repo.entity.UserRepository;
import com.example.radio_active_mushroom.services.UserProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private ThemeDocumentRepository themeDocumentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ThemeDocument GetUserTheme(String username) {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            Optional<ThemeDocument> userTheme = themeDocumentRepository.findByUsername(username);
            if (userTheme.isPresent()) {
                return userTheme.get();
            } else {
                ThemeDocument created_theme = new ThemeDocument();
                created_theme.setUsername(username);
                created_theme.setColor(ThemeColorEnum.DEFAULT);
                created_theme.setMode(ThemeModeEnum.LIGHT);
                created_theme.setColorization(ThemeColorizationEnum.FULL);
                themeDocumentRepository.save(created_theme);
                return created_theme;
            }
        }
        return null;
    }

    @Override
    public void SaveUserSettings(UserSettingsDto userSettings, String username) {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        Optional<ThemeDocument> theme = themeDocumentRepository.findByUsername(username);
        if (user.isPresent() && theme.isPresent()) {
            UserEntity userEntity = user.get();
            ThemeDocument themeDocument = theme.get();

            userEntity.setFirst_name(userSettings.getFirst_name());
            userEntity.setLast_name(userSettings.getLast_name());

            themeDocument.setColorization(userSettings.getColorization());
            themeDocument.setMode(userSettings.getMode());
            themeDocument.setColor(userSettings.getColor());

            userRepository.save(userEntity);
            themeDocumentRepository.save(themeDocument);
        } else {

        }
    }

    @Override
    public UserSettingsDto GetUserSettings(String username) {
        UserSettingsDto userSettings = modelMapper.map(GetUserTheme(username), UserSettingsDto.class);
        UserEntity user = userRepository.findByUsername(username).get();
        userSettings.setFirst_name(user.getFirst_name());
        userSettings.setLast_name(user.getLast_name());
        return userSettings;
    }
}
