package com.example.radio_active_mushroom.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("accounts/")
public class AccountsController {
    @GetMapping("registration/")
    public String registration_from(Model model) {
        return "accounts/registration_form";
    }
}
