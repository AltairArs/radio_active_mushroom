package com.example.radio_active_mushroom.services;

import com.example.radio_active_mushroom.dto.UserRegistrationDto;

public interface UserRegistrationService {
    public abstract void RegisterNewUser(UserRegistrationDto userRegistrationDto);
    public abstract boolean ValidateVerificationToken(String verificationToken);
}
