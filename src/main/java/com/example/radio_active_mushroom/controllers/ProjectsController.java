package com.example.radio_active_mushroom.controllers;

import com.example.radio_active_mushroom.authentication.CustomAuthenticationService;
import com.example.radio_active_mushroom.dto.ProjectCreateDto;
import com.example.radio_active_mushroom.models.UserEntity;
import com.example.radio_active_mushroom.services.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("projects/")
public class ProjectsController {

    @Autowired
    private CustomAuthenticationService authenticationService;

    @Autowired
    private ProjectService projectService;

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

    @PostMapping("create/")
    public String createProjectPost(@Valid @ModelAttribute("form") ProjectCreateDto projectCreateDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "projects/create";
        } else {
            UserEntity user = authenticationService.GetCurrentUser(SecurityContextHolder.getContext().getAuthentication());
            if (!projectService.createNewProject(projectCreateDto, user)) {
                bindingResult.addError(new ObjectError("form", "У вас уже есть проект с таким названием"));
                return "projects/create";
            } else {
                return "redirect:/projects/list/my/";
            }
        }
    }

    @GetMapping("details/my/{project_name}/")
    public String detailsMyProject(@PathVariable String project_name, Model model) {
        UserEntity user = authenticationService.GetCurrentUser(SecurityContextHolder.getContext().getAuthentication());
        model.addAttribute("project", projectService.getProject(user, project_name));
        return "projects/my_details";
    }
}
