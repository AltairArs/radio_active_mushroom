package com.example.radio_active_mushroom.controllers;

import com.example.radio_active_mushroom.dto.entity.UserRegistrationDto;
import com.example.radio_active_mushroom.services.UserRegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("accounts/")
public class RegistrationController {
    @Autowired
    UserRegistrationService userRegistrationService;

    @GetMapping("registration/")
    public String registrationForm(Model model) {
        model.addAttribute("form", new UserRegistrationDto());
        return "registration/registrationForm";
    }

    @PostMapping("registration/")
    public String registrationFormPost(@Valid @ModelAttribute("form") UserRegistrationDto form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("form", form);
            return "registration/registrationForm";
        } else {
            userRegistrationService.registerNewUser(form);
            return "redirect:/accounts/registration/done/";
        }

    }

    @GetMapping("registration/done/")
    public String registrationDone(Model model) {
        return "registration/registrationDone";
    }

    @GetMapping("verification/complete/")
    public String verificationComplete(Model model) {
        return "registration/verificationComplete";
    }

    @GetMapping("verification/failed/")
    public String verificationFailed(Model model) {
        return "registration/verificationFailed";
    }

    @GetMapping("verify/{verificationToken}/")
    public String verify(@PathVariable String verificationToken, Model model) {
        if (userRegistrationService.validateVerificationToken(verificationToken)) {
            return "redirect:/accounts/verification/complete/";
        } else {
            return "redirect:/accounts/verification/failed/";
        }
    }
}
