package com.example.radio_active_mushroom.services;

import com.example.radio_active_mushroom.dto.UserRegistrationDto;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    @Override
    public UserRegistrationDto GetUserRegistrationForm() {
        return new UserRegistrationDto();
    }
}
