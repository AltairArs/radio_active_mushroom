package com.example.radio_active_mushroom.controllers;

import com.example.radio_active_mushroom.authentication.CustomAuthenticationService;
import com.example.radio_active_mushroom.dto.document.ChangeTablePositionDto;
import com.example.radio_active_mushroom.dto.document.CreateTableDto;
import com.example.radio_active_mushroom.dto.document.DeleteTableDto;
import com.example.radio_active_mushroom.dto.document.EditTableDto;
import com.example.radio_active_mushroom.dto.entity.ProjectCreateDto;
import com.example.radio_active_mushroom.dto.entity.ProjectSettingsDto;
import com.example.radio_active_mushroom.enums.ProjectKindPermissionsEnum;
import com.example.radio_active_mushroom.models.entity.UserEntity;
import com.example.radio_active_mushroom.services.ProjectService;
import com.example.radio_active_mushroom.services.UserProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@Controller
@RequestMapping("projects/")
public class ProjectsController {

    @Autowired
    private CustomAuthenticationService authenticationService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("list/{username}/")
    public String listProjects(Authentication authentication, Model model, @PathVariable String username) {
        UserEntity user = userProfileService.getUserEntity(username);
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
    public String createProjectPost(Authentication authentication, @Valid @ModelAttribute("form") ProjectCreateDto projectCreateDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "projects/create";
        } else {
            UserEntity user = authenticationService.getCurrentUser(authentication);
            if (!projectService.createNewProject(projectCreateDto, user)) {
                bindingResult.addError(new ObjectError("form", "У вас уже есть проект с таким названием"));
                return "projects/create";
            } else {
                return "redirect:/projects/list/" + user.getUsername() + "/";
            }
        }
    }

    @GetMapping("details/{username}/{projectName}/")
    public String detailsProject(Authentication authentication, @PathVariable String projectName, Model model, @PathVariable String username) {
        if (!projectService.determineCanActionBeTaken(userProfileService.getUserEntity(username), projectName, authenticationService.getCurrentUser(authentication), ProjectKindPermissionsEnum.CAN_SEE)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        } else {
            UserEntity user = userProfileService.getUserEntity(username);
            model.addAttribute("project", projectService.getProject(user, projectName));
            return "projects/myDetails";
        }
    }

    @GetMapping("delete/{username}/{projectName}/")
    public String deleteProject(@PathVariable String projectName, Model model, @PathVariable String username) {
        model.addAttribute("project", projectService.getProject(userProfileService.getUserEntity(username), projectName));
        return "projects/delete";
    }

    @PostMapping("delete/{username}/{projectName}/")
    public String deleteProjectPost(@PathVariable String projectName, Model model, @PathVariable String username) {
        UserEntity user = userProfileService.getUserEntity(username);
        projectService.deleteProject(user, projectName);
        return "redirect:/projects/list/" + user.getUsername() + "/";
    }

    @GetMapping("settings/{username}/{projectName}/")
    public String settingsProject(@PathVariable String projectName, Model model, @PathVariable String username) {
        UserEntity user = userProfileService.getUserEntity(username);
        model.addAttribute("form", projectService.getProjectSettings(user, projectName));
        model.addAttribute("project", projectService.getProject(user, projectName));
        return "projects/settings";
    }

    @PostMapping("settings/{username}/{projectName}/")
    public String settingsProjectPost(@PathVariable String projectName, @Valid @ModelAttribute("form") ProjectSettingsDto form, BindingResult bindingResult, Model model, @PathVariable String username) {
        UserEntity user = userProfileService.getUserEntity(username);
        projectService.updateProjectSettings(user, projectName, form);
        return "redirect:/projects/details/" + user.getUsername() + "/" + projectName + "/";
    }

    @GetMapping("workspace/{username}/{projectName}/")
    public String workspaceProject(Authentication authentication, @PathVariable String projectName, Model model, @PathVariable String username) {
        Set<ProjectKindPermissionsEnum> permissions = projectService.getAskerPermissions(userProfileService.getUserEntity(username), projectName, authenticationService.getCurrentUser(authentication));
        if (!permissions.contains(ProjectKindPermissionsEnum.CAN_SEE_WORKSPACE)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        } else {
            UserEntity user = userProfileService.getUserEntity(username);
            model.addAttribute("project", projectService.getProject(user, projectName));
            model.addAttribute("formCreateTable", new CreateTableDto());
            model.addAttribute("formChangeTablePosition", new ChangeTablePositionDto());
            model.addAttribute("formEditTable", new EditTableDto());
            model.addAttribute("formDeleteTable", new DeleteTableDto());
            model.addAttribute("permissions", permissions);
            return "projects/workspace";
        }
    }
}
