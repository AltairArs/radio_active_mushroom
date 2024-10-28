package com.example.radio_active_mushroom.controllers;

import com.example.radio_active_mushroom.authentication.CustomAuthenticationService;
import com.example.radio_active_mushroom.dto.document.ChangeTablePositionDto;
import com.example.radio_active_mushroom.dto.document.CreateTableDto;
import com.example.radio_active_mushroom.dto.document.DeleteTableDto;
import com.example.radio_active_mushroom.dto.document.EditTableDto;
import com.example.radio_active_mushroom.dto.entity.ProjectCreateDto;
import com.example.radio_active_mushroom.dto.entity.ProjectSettingsDto;
import com.example.radio_active_mushroom.enums.ProjectKindPermissionsEnum;
import com.example.radio_active_mushroom.models.entity.ProjectEntity;
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

import java.util.ArrayList;
import java.util.List;
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
        UserEntity currentUser = authenticationService.getCurrentUser(authentication);
        model.addAttribute("projectsAsOwner", user.getProjectsAsOwner());
        model.addAttribute("projectsAsMember", user.getProjectsAsMember());
        if (user == currentUser) {
            model.addAttribute("membershipRequests", currentUser.getMembershipRequests());
            return "projects/myList";
        } else {
            return "projects/list";
        }
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
            UserEntity currentUser = authenticationService.getCurrentUser(authentication);
            model.addAttribute("project", projectService.getProject(user, projectName));
            if (user == currentUser) {
                return "projects/myDetails";
            } else {
                return "projects/details";
            }
        }
    }

    @GetMapping("delete/{username}/{projectName}/")
    public String deleteProject(Authentication authentication, @PathVariable String projectName, Model model, @PathVariable String username) {
        ProjectEntity project = projectService.getProject(userProfileService.getUserEntity(username), projectName);
        UserEntity owner = userProfileService.getUserEntity(username);
        UserEntity user = authenticationService.getCurrentUser(authentication);
        if (owner != user){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        } else {
            model.addAttribute("project", project);
            return "projects/delete";
        }
    }

    @PostMapping("delete/{username}/{projectName}/")
    public String deleteProjectPost(Authentication authentication, @PathVariable String projectName, Model model, @PathVariable String username) {
        UserEntity owner = userProfileService.getUserEntity(username);
        UserEntity user = authenticationService.getCurrentUser(authentication);
        if (owner != user){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        } else {
            projectService.deleteProject(owner, projectName);
            return "redirect:/projects/list/" + owner.getUsername() + "/";
        }
    }

    @GetMapping("settings/{username}/{projectName}/")
    public String settingsProject(Authentication authentication, @PathVariable String projectName, Model model, @PathVariable String username) {
        ProjectEntity project = projectService.getProject(userProfileService.getUserEntity(username), projectName);
        UserEntity owner = userProfileService.getUserEntity(username);
        UserEntity user = authenticationService.getCurrentUser(authentication);
        if (owner != user){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        } else {
            model.addAttribute("form", projectService.getProjectSettings(owner, projectName));
            model.addAttribute("project", project);
            return "projects/settings";
        }
    }

    @PostMapping("settings/{username}/{projectName}/")
    public String settingsProjectPost(Authentication authentication, @PathVariable String projectName, @Valid @ModelAttribute("form") ProjectSettingsDto form, BindingResult bindingResult, Model model, @PathVariable String username) {
        UserEntity owner = userProfileService.getUserEntity(username);
        UserEntity user = authenticationService.getCurrentUser(authentication);
        if (owner != user){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        } else {
            projectService.updateProjectSettings(owner, projectName, form);
            return "redirect:/projects/details/" + owner.getUsername() + "/" + projectName + "/";
        }
    }

    @GetMapping("workspace/{username}/{projectName}/")
    public String workspaceProject(Authentication authentication, @PathVariable String projectName, Model model, @PathVariable String username) {
        Set<ProjectKindPermissionsEnum> permissions = projectService.getAskerPermissions(userProfileService.getUserEntity(username), projectName, authenticationService.getCurrentUser(authentication));
        if (!permissions.contains(ProjectKindPermissionsEnum.CAN_SEE_WORKSPACE)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        } else {
            List<String> permission_names = new ArrayList<String>();
            for (ProjectKindPermissionsEnum permission : permissions){
                permission_names.add(permission.name());
            }
            UserEntity user = userProfileService.getUserEntity(username);
            model.addAttribute("project", projectService.getProject(user, projectName));
            model.addAttribute("formCreateTable", new CreateTableDto());
            model.addAttribute("formChangeTablePosition", new ChangeTablePositionDto());
            model.addAttribute("formEditTable", new EditTableDto());
            model.addAttribute("formDeleteTable", new DeleteTableDto());
            model.addAttribute("permissions", permission_names);
            return "projects/workspace";
        }
    }
}
