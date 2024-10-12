package com.example.radio_active_mushroom.services;

import com.example.radio_active_mushroom.dto.UserLoginDto;

public interface AccountsService {
    public abstract UserLoginDto GetUserLoginForm();
    public abstract void Login(UserLoginDto userLoginDto);
    public abstract void Logout();
}
