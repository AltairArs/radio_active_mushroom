package com.example.radio_active_mushroom.controllers;

import com.example.radio_active_mushroom.authentication.CustomAuthenticationService;
import com.example.radio_active_mushroom.dto.document.CreateTable;
import com.example.radio_active_mushroom.dto.entity.ProjectCreateDto;
import com.example.radio_active_mushroom.dto.entity.ProjectSettingsDto;
import com.example.radio_active_mushroom.models.entity.UserEntity;
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
        UserEntity user = authenticationService.getCurrentUser(SecurityContextHolder.getContext().getAuthentication());
        model.addAttribute("projectsAsOwner", user.getProjectsAsOwner());
        model.addAttribute("projectsAsMember", user.getProjectsAsMember());
        return "projects/myList";
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
            UserEntity user = authenticationService.getCurrentUser(SecurityContextHolder.getContext().getAuthentication());
            if (!projectService.createNewProject(projectCreateDto, user)) {
                bindingResult.addError(new ObjectError("form", "У вас уже есть проект с таким названием"));
                return "projects/create";
            } else {
                return "redirect:/projects/list/my/";
            }
        }
    }

    @GetMapping("details/my/{projectName}/")
    public String detailsMyProject(@PathVariable String projectName, Model model) {
        UserEntity user = authenticationService.getCurrentUser(SecurityContextHolder.getContext().getAuthentication());
        model.addAttribute("project", projectService.getProject(user, projectName));
        return "projects/myDetails";
    }

    @GetMapping("delete/my/{projectName}/")
    public String deleteMyProject(@PathVariable String projectName, Model model) {
        return "projects/delete";
    }

    @PostMapping("delete/my/{projectName}/")
    public String deleteMyProjectPost(@PathVariable String projectName, Model model) {
        UserEntity user = authenticationService.getCurrentUser(SecurityContextHolder.getContext().getAuthentication());
        projectService.deleteProject(user, projectName);
        return "redirect:/projects/list/my/";
    }

    @GetMapping("settings/my/{projectName}/")
    public String settingsMyProject(@PathVariable String projectName, Model model) {
        UserEntity user = authenticationService.getCurrentUser(SecurityContextHolder.getContext().getAuthentication());
        model.addAttribute("form", projectService.getProjectSettings(user, projectName));
        return "projects/settings";
    }

    @PostMapping("settings/my/{projectName}/")
    public String settingsMyProjectPost(@PathVariable String projectName, @Valid @ModelAttribute("form") ProjectSettingsDto form, BindingResult bindingResult, Model model) {
        UserEntity user = authenticationService.getCurrentUser(SecurityContextHolder.getContext().getAuthentication());
        projectService.updateProjectSettings(user, projectName, form);
        return "redirect:/projects/details/my/" + projectName + "/";
    }

    @GetMapping("workspace/my/{projectName}/")
    public String workspaceMyProject(@PathVariable String projectName, Model model) {
        UserEntity user = authenticationService.getCurrentUser(SecurityContextHolder.getContext().getAuthentication());
        model.addAttribute("project", projectService.getProject(user, projectName));
        model.addAttribute("ownerUsername", user.getUsername());
        model.addAttribute("formCreateTable", new CreateTable());
        return "projects/workspace";
    }
}
