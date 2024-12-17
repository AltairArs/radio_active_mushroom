package com.example.radio_active_mushroom.controllers;

import com.example.radio_active_mushroom.authentication.CustomAuthenticationService;
import com.example.radio_active_mushroom.dto.entity.AddUserToProjectMembersDto;
import com.example.radio_active_mushroom.models.entity.UserEntity;
import com.example.radio_active_mushroom.services.ProjectService;
import com.example.radio_active_mushroom.services.UserProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequiredArgsConstructor
@RequestMapping("projects/members/{username}/{projectName}/")
public class ProjectMembersController {

    private final CustomAuthenticationService authenticationService;

    private final UserProfileService userProfileService;

    private final ProjectService projectService;

    @GetMapping("delete/{memberUsername}/")
    public String deleteMember(Authentication authentication, @PathVariable String projectName, @PathVariable String username, @PathVariable String memberUsername) {
        if (!projectService.getProject(userProfileService.getUserEntity(username), projectName).getOwner().equals(authenticationService.getCurrentUser(authentication))) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        } else {
            return "projects/deleteMember";
        }
    }

    @PostMapping("delete/{memberUsername}/")
    public String deleteMemberPost(Authentication authentication, @PathVariable String projectName, @PathVariable String username, @PathVariable String memberUsername) {
        UserEntity user = authenticationService.getCurrentUser(authentication);
        if (!projectService.getProject(userProfileService.getUserEntity(username), projectName).getOwner().equals(user)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        } else {
            projectService.removeMember(projectService.getProject(userProfileService.getUserEntity(username), projectName), memberUsername);
            return "redirect:/projects/list/" + user.getUsername() + "/";
        }
    }

    @GetMapping("add/{memberUsername}/")
    public String addMember(Authentication authentication, @PathVariable String projectName, @PathVariable String memberUsername, @PathVariable String username, Model model) {
        UserEntity user = authenticationService.getCurrentUser(authentication);
        if (!projectService.getProject(userProfileService.getUserEntity(username), projectName).getOwner().equals(user)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        } else {
            model.addAttribute("addMemberForm", new AddUserToProjectMembersDto());
            return "projects/addMember";
        }
    }

    @PostMapping("add/{memberUsername}/")
    public String addMemberPost(Authentication authentication, @PathVariable String projectName, @PathVariable String memberUsername, @PathVariable String username, Model model, @Valid @ModelAttribute("addMemberForm") AddUserToProjectMembersDto addMemberForm, BindingResult bindingResult) {
        UserEntity user = authenticationService.getCurrentUser(authentication);
        if (!projectService.getProject(userProfileService.getUserEntity(username), projectName).getOwner().equals(user)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        } else {
            if (bindingResult.hasErrors()){
                model.addAttribute("addMemberForm", addMemberForm);
                return "projects/addMember";
            } else {
                if(projectService.addMember(projectService.getProject(userProfileService.getUserEntity(username), projectName), memberUsername)){
                    return "redirect:/projects/list/" + user.getUsername() + "/";
                } else {
                    model.addAttribute("addMemberForm", addMemberForm);
                    bindingResult.addError(new ObjectError("addMemberForm", "Пользователь уже участник"));
                    return "projects/addMember";
                }
            }
        }
    }
}
