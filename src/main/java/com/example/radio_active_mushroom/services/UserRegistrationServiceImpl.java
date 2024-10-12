package com.example.radio_active_mushroom.services;

import com.example.radio_active_mushroom.dto.UserRegistrationDto;
import com.example.radio_active_mushroom.models.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    @Override
    public UserRegistrationDto GetUserRegistrationForm() {
        return new UserRegistrationDto();
    }

    @Override
    public UserEntity RegisterNewUser(UserRegistrationDto userRegistrationDto) {
        return null;
    }
}
