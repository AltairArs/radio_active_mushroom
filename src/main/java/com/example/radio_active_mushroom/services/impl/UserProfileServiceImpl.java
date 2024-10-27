package com.example.radio_active_mushroom.services.impl;

import com.example.radio_active_mushroom.dto.entity.UserSettingsDto;
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
    public ThemeDocument getUserTheme(String username) {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            Optional<ThemeDocument> userTheme = themeDocumentRepository.findByUsername(username);
            if (userTheme.isPresent()) {
                return userTheme.get();
            } else {
                ThemeDocument createdTheme = new ThemeDocument();
                createdTheme.setUsername(username);
                createdTheme.setColor(ThemeColorEnum.DEFAULT);
                createdTheme.setMode(ThemeModeEnum.LIGHT);
                createdTheme.setColorization(ThemeColorizationEnum.FULL);
                themeDocumentRepository.save(createdTheme);
                return createdTheme;
            }
        }
        return null;
    }

    @Override
    public void saveUserSettings(UserSettingsDto userSettings, String username) {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        Optional<ThemeDocument> theme = themeDocumentRepository.findByUsername(username);
        if (user.isPresent() && theme.isPresent()) {
            UserEntity userEntity = user.get();
            ThemeDocument themeDocument = theme.get();

            userEntity.setFirstName(userSettings.getFirstName());
            userEntity.setLastName(userSettings.getLastName());

            themeDocument.setColorization(userSettings.getColorization());
            themeDocument.setMode(userSettings.getMode());
            themeDocument.setColor(userSettings.getColor());

            userRepository.save(userEntity);
            themeDocumentRepository.save(themeDocument);
        }
    }

    @Override
    public UserSettingsDto getUserSettings(String username) {
        UserSettingsDto userSettings = modelMapper.map(getUserTheme(username), UserSettingsDto.class);
        UserEntity user = userRepository.findByUsername(username).get();
        userSettings.setFirstName(user.getFirstName());
        userSettings.setLastName(user.getLastName());
        return userSettings;
    }

    @Override
    public UserEntity getUserEntity(String username) {
        return userRepository.findByUsername(username).get();
    }
}
