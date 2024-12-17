package com.example.radio_active_mushroom.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String mainN() {
        return "main/main";
    }
}
