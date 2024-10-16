package com.example.radio_active_mushroom.controllers;

import com.example.radio_active_mushroom.authentication.CustomAuthenticationService;
import com.example.radio_active_mushroom.dto.ProjectCreateDto;
import com.example.radio_active_mushroom.models.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("projects/")
public class ProjectsController {

    @Autowired
    private CustomAuthenticationService authenticationService;

    @GetMapping("list/my/")
    public String listMyProjects(Model model) {
        UserEntity user = authenticationService.GetCurrentUser(SecurityContextHolder.getContext().getAuthentication());
        model.addAttribute("projects_as_owner", user.getProjects_as_owner());
        model.addAttribute("projects_as_member", user.getProjects_as_member());
        return "projects/my_list";
    }

    @GetMapping("create/")
    public String createProject(Model model) {
        model.addAttribute("form", new ProjectCreateDto());
        return "projects/create";
    }
}
