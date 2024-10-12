package com.example.radio_active_mushroom.services;

import com.example.radio_active_mushroom.dto.UserRegistrationDto;
import com.example.radio_active_mushroom.models.UserEntity;

public interface UserRegistrationService {
    public abstract UserRegistrationDto GetUserRegistrationForm();
    public abstract UserEntity RegisterNewUser(UserRegistrationDto userRegistrationDto);
}
