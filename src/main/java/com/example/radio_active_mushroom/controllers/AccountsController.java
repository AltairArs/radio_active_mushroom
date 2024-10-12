package com.example.radio_active_mushroom.controllers;

import com.example.radio_active_mushroom.dto.UserRegistrationDto;
import com.example.radio_active_mushroom.services.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("verify/{verification_token}/")
    public String verify(@PathVariable String verification_token, Model model) {
        if (userRegistrationService.ValidateVerificationToken(verification_token)) {
            return "redirect:/accounts/verification/";
        } else {
            return "redirect:/accounts/verification/";
        }
    }
}
