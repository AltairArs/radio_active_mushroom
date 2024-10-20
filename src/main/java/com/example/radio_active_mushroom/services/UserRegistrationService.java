package com.example.radio_active_mushroom.services;

import com.example.radio_active_mushroom.dto.entity.UserRegistrationDto;

public interface UserRegistrationService {
    public abstract void registerNewUser(UserRegistrationDto userRegistrationDto);
    public abstract boolean validateVerificationToken(String verificationToken);
}
