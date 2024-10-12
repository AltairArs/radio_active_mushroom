package com.example.radio_active_mushroom.controllers;

import com.example.radio_active_mushroom.dto.UserRegistrationDto;
import com.example.radio_active_mushroom.services.UserRegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("accounts/")
public class RegistrationController {
    @Autowired
    UserRegistrationService userRegistrationService;

    @GetMapping("registration/")
    public String registration_form(Model model) {
        model.addAttribute("form", userRegistrationService.GetUserRegistrationForm());
        return "registration/registration_form";
    }

    @PostMapping("registration/")
    public String registration_form_post(@Valid @ModelAttribute("form") UserRegistrationDto form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("form", form);
            return "registration/registration_form";
        } else
            return "redirect:/accounts/registration/done/";
    }

    @GetMapping("registration/done/")
    public String registration_done(Model model) {
        return "registration/registration_done";
    }

    @GetMapping("verification/complete/")
    public String verification_complete(Model model) {
        return "registration/verification_complete";
    }

    @GetMapping("verification/failed/")
    public String verification_failed(Model model) {
        return "registration/verification_failed";
    }

    @GetMapping("verify/{verification_token}/")
    public String verify(@PathVariable String verification_token, Model model) {
        if (userRegistrationService.ValidateVerificationToken(verification_token)) {
            return "redirect:/accounts/verification/complete/";
        } else {
            return "redirect:/accounts/verification/failed/";
        }
    }
}
