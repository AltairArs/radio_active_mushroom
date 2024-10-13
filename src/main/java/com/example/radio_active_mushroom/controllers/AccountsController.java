package com.example.radio_active_mushroom.controllers;

import com.example.radio_active_mushroom.dto.UserLoginDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("accounts/")
public class AccountsController {
    @GetMapping("login/")
    public String login(Model model) {
        model.addAttribute("form", new UserLoginDto());
        return "accounts/login_form";
    }

    @GetMapping("logout/")
    public String logout() {
        return "accounts/logout_form";
    }
}
