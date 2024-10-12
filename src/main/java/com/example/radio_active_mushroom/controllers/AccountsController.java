package com.example.radio_active_mushroom.controllers;

import com.example.radio_active_mushroom.dto.UserLoginDto;
import com.example.radio_active_mushroom.services.AccountsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("accounts/")
public class AccountsController {

    @Autowired
    private AccountsService accountsService;

    @GetMapping("login/")
    public String login(Model model) {
        model.addAttribute("form", accountsService.GetUserLoginForm());
        return "accounts/login_form";
    }

    @PostMapping("login/")
    public String login_post(@Valid @ModelAttribute("form") UserLoginDto form, BindingResult bindingResult, Model model) {
        return "accounts/login_form";
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
