package com.example.radio_active_mushroom.services;

import com.example.radio_active_mushroom.dto.UserLoginDto;
import org.springframework.stereotype.Service;

@Service
public class AccountsServiceImpl implements AccountsService {
    @Override
    public UserLoginDto GetUserLoginForm() {
        return new UserLoginDto();
    }
}
