package com.example.radio_active_mushroom.controllers;

import com.example.radio_active_mushroom.dto.UserRegistrationDto;
import com.example.radio_active_mushroom.services.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("accounts/")
public class AccountsController {
    @Autowired
    UserRegistrationService userRegistrationService;

    @GetMapping("registration/")
    public String registration_form(Model model) {
        model.addAttribute("form", userRegistrationService.GetUserRegistrationForm());
        return "accounts/registration_form";
    }

    @PostMapping("registration/")
    public String registration_form_post(@ModelAttribute UserRegistrationDto form, Model model) {
        model.addAttribute("form", form);
        return "accounts/registration_form";
    }
}
