package com.example.radio_active_mushroom.controllers;

import com.example.radio_active_mushroom.authentication.CustomAuthenticationService;
import com.example.radio_active_mushroom.dto.entity.UserLoginDto;
import com.example.radio_active_mushroom.dto.entity.UserSettingsDto;
import com.example.radio_active_mushroom.models.entity.UserEntity;
import com.example.radio_active_mushroom.services.UserProfileService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("accounts/")
public class AccountsController {

    @Autowired
    private CustomAuthenticationService authenticationService;

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("login/")
    public String login(Model model) {
        model.addAttribute("form", new UserLoginDto());
        return "accounts/loginForm";
    }

    @PostMapping("login/")
    public String loginPost(@ModelAttribute("form") UserLoginDto form, BindingResult bindingResult, Model model, HttpServletRequest request, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("form", form);
            return "accounts/loginForm";
        } else {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth.getPrincipal() == null || auth instanceof AnonymousAuthenticationToken || auth.getPrincipal().equals("anonymous")) {
                bindingResult.addError(new ObjectError("form", "Неверные данные или аккаунт еще не активирован"));
                model.addAttribute("form", form);
                return "accounts/loginForm";
            } else {
                authenticationService.Login(authenticationService.getCurrentUser(auth).getUsername());
                return "redirect:/";
            }
        }
    }

    @GetMapping("logout/")
    public String logout() {
        return "accounts/logoutForm";
    }

    @PostMapping("logout/")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        SecurityContextHolder.clearContext();
        session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        for (Cookie cookie : request.getCookies()) {
            cookie.setMaxAge(0);
        }
        return "redirect:/accounts/login/";
    }

    @GetMapping("profile/")
    public String profile(Model model) {
        UserEntity user = authenticationService.getCurrentUser(SecurityContextHolder.getContext().getAuthentication());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("lastName", user.getLastName());
        return "accounts/profile";
    }

    @GetMapping("profile/settings/")
    public String profileSettings(Model model) {
        UserEntity user = authenticationService.getCurrentUser(SecurityContextHolder.getContext().getAuthentication());
        model.addAttribute("form", userProfileService.getUserSettings(user.getUsername()));
        return "accounts/profileSettings";
    }

    @PostMapping("profile/settings/")
    public String profileSettingsPost(@ModelAttribute("form") UserSettingsDto form) {
        UserEntity user = authenticationService.getCurrentUser(SecurityContextHolder.getContext().getAuthentication());
        userProfileService.saveUserSettings(form, user.getUsername());
        return "redirect:/accounts/profile/";
    }
}
