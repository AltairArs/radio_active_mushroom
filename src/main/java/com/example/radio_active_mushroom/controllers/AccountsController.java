package com.example.radio_active_mushroom.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("accounts/")
public class AccountsController {

    @GetMapping("login/")
    public String login() {
        return "accounts/login";
    }

    @PostMapping("login/")
    public String login_post() {
        return "accounts/login";
    }

    @GetMapping("logout/")
    public String logout() {
        return "accounts/logout";
    }

    @PostMapping("logout/")
    public String logout_post() {
        return "accounts/logout";
    }
}
