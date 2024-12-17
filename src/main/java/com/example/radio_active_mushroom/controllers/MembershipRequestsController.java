package com.example.radio_active_mushroom.controllers;

import com.example.radio_active_mushroom.authentication.CustomAuthenticationService;
import com.example.radio_active_mushroom.dto.entity.EditMembershipRequestDto;
import com.example.radio_active_mushroom.dto.entity.SendMembershipRequestDto;
import com.example.radio_active_mushroom.models.entity.MembershipRequestEntity;
import com.example.radio_active_mushroom.models.entity.UserEntity;
import com.example.radio_active_mushroom.services.MembershipRequestsService;
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
@RequestMapping("projects/requests/")
public class MembershipRequestsController {

    private final CustomAuthenticationService authenticationService;

    private final UserProfileService userProfileService;

    private final MembershipRequestsService membershipRequestsService;

    @GetMapping("send/")
    public String membershipRequestSend(Model model) {
        model.addAttribute("sendMembershipRequestForm", new SendMembershipRequestDto());
        return "projects/membershipRequestForm";
    }

    @PostMapping("send/")
    public String membershipRequestSendPost(Authentication authentication, Model model, @ModelAttribute("sendMembershipRequestForm") @Valid SendMembershipRequestDto sendMembershipRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("sendMembershipRequestForm", sendMembershipRequestDto);
            return "projects/membershipRequestForm";
        } else {
            if (membershipRequestsService.createMembershipRequest(sendMembershipRequestDto, authenticationService.getCurrentUser(authentication))){
                return "projects/membershipRequestSendDone";
            } else {
                model.addAttribute("sendMembershipRequestForm", sendMembershipRequestDto);
                bindingResult.addError(new ObjectError("sendMembershipRequestForm", "Вы уже отправили запрос или использовали некорректные данные или уже являетесь участником"));
                return "projects/membershipRequestForm";
            }
        }
    }

    @GetMapping("details/{username}/{projectName}/{senderUsername}/")
    public String requestDetails(Authentication authentication, @PathVariable String projectName, @PathVariable String username, Model model, @PathVariable String senderUsername) {
        UserEntity user = authenticationService.getCurrentUser(authentication);
        MembershipRequestEntity membershipRequestEntity = membershipRequestsService.getMembershipRequest(username, projectName, senderUsername);
        if (membershipRequestEntity.getSender() != user && membershipRequestEntity.getProject().getOwner() != user){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        } else {
            model.addAttribute("request", membershipRequestEntity);
            if (membershipRequestEntity.getSender() == user){
                return "projects/myDetailsMembershipRequest";
            } else {
                return "projects/detailsMembershipRequest";
            }
        }
    }

    @GetMapping("accept/{username}/{projectName}/{senderUsername}/")
    public String acceptRequest(Authentication authentication, @PathVariable String projectName, @PathVariable String username, Model model, @PathVariable String senderUsername) {
        UserEntity user = authenticationService.getCurrentUser(authentication);
        MembershipRequestEntity membershipRequestEntity = membershipRequestsService.getMembershipRequest(username, projectName, senderUsername);
        if (membershipRequestEntity.getSender() != user && membershipRequestEntity.getProject().getOwner() != user){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        } else {
            model.addAttribute("request", membershipRequestEntity);
            return "projects/acceptMembershipRequest";
        }
    }

    @PostMapping("accept/{username}/{projectName}/{senderUsername}/")
    public String acceptPost(Authentication authentication, @PathVariable String projectName, @PathVariable String username, Model model, @PathVariable String senderUsername) {
        UserEntity owner = userProfileService.getUserEntity(username);
        UserEntity user = authenticationService.getCurrentUser(authentication);
        MembershipRequestEntity membershipRequestEntity = membershipRequestsService.getMembershipRequest(username, projectName, senderUsername);
        if (membershipRequestEntity.getSender() != user && membershipRequestEntity.getProject().getOwner() != user){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        } else {
            membershipRequestsService.acceptMembershipRequest(membershipRequestEntity);
            return "redirect:/projects/list/" + owner.getUsername() + "/";
        }
    }

    @GetMapping("reject/{username}/{projectName}/{senderUsername}/")
    public String rejectRequest(Authentication authentication, @PathVariable String projectName, @PathVariable String username, Model model, @PathVariable String senderUsername) {
        UserEntity user = authenticationService.getCurrentUser(authentication);
        MembershipRequestEntity membershipRequestEntity = membershipRequestsService.getMembershipRequest(username, projectName, senderUsername);
        if (membershipRequestEntity.getSender() != user && membershipRequestEntity.getProject().getOwner() != user){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        } else {
            model.addAttribute("request", membershipRequestEntity);
            return "projects/rejectMembershipRequest";
        }
    }

    @PostMapping("reject/{username}/{projectName}/{senderUsername}/")
    public String rejectRequestPost(Authentication authentication, @PathVariable String projectName, @PathVariable String username, Model model, @PathVariable String senderUsername) {
        UserEntity owner = userProfileService.getUserEntity(username);
        UserEntity user = authenticationService.getCurrentUser(authentication);
        MembershipRequestEntity membershipRequestEntity = membershipRequestsService.getMembershipRequest(username, projectName, senderUsername);
        if (membershipRequestEntity.getSender() != user && membershipRequestEntity.getProject().getOwner() != user){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        } else {
            membershipRequestsService.rejectMembershipRequest(membershipRequestEntity);
            return "redirect:/projects/list/" + owner.getUsername() + "/";
        }
    }

    @GetMapping("delete/{username}/{projectName}/{senderUsername}/")
    public String deleteRequest(Authentication authentication, @PathVariable String projectName, @PathVariable String senderUsername, @PathVariable String username, Model model) {
        UserEntity user = authenticationService.getCurrentUser(authentication);
        MembershipRequestEntity membershipRequestEntity = membershipRequestsService.getMembershipRequest(username, projectName, senderUsername);
        if (membershipRequestEntity.getSender() != user){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        } else {
            model.addAttribute("request", membershipRequestEntity);
            return "projects/deleteMembershipRequest";
        }
    }

    @PostMapping("delete/{username}/{projectName}/{senderUsername}/")
    public String deleteRequestPost(Authentication authentication, @PathVariable String projectName, @PathVariable String senderUsername, Model model, @PathVariable String username) {
        UserEntity user = authenticationService.getCurrentUser(authentication);
        MembershipRequestEntity membershipRequestEntity = membershipRequestsService.getMembershipRequest(username, projectName, senderUsername);
        if (membershipRequestEntity.getSender() != user){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        } else {
            membershipRequestsService.deleteMembershipRequest(membershipRequestEntity);
            return "redirect:/projects/list/" + user.getUsername() + "/";
        }
    }

    @GetMapping("edit/{username}/{projectName}/{senderUsername}/")
    public String editRequest(Authentication authentication, @PathVariable String projectName, @PathVariable String senderUsername, Model model, @PathVariable String username) {
        UserEntity user = authenticationService.getCurrentUser(authentication);
        MembershipRequestEntity membershipRequestEntity = membershipRequestsService.getMembershipRequest(username, projectName, senderUsername);
        if (membershipRequestEntity.getSender() != user){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        } else {
            model.addAttribute("editMembershipRequestForm", membershipRequestsService.getEditMembershipRequest(membershipRequestEntity));
            model.addAttribute("request", membershipRequestEntity);
            return "projects/editMembershipRequestForm";
        }
    }

    @PostMapping("edit/{username}/{projectName}/{senderUsername}/")
    public String editRequestPost(Authentication authentication, @PathVariable String projectName, @PathVariable String senderUsername, Model model, @PathVariable String username, @ModelAttribute("editMembershipRequestForm") EditMembershipRequestDto editMembershipRequestDto) {
        UserEntity user = authenticationService.getCurrentUser(authentication);
        MembershipRequestEntity membershipRequestEntity = membershipRequestsService.getMembershipRequest(username, projectName, senderUsername);
        if (membershipRequestEntity.getSender() != user){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        } else {
            membershipRequestsService.saveMembershipRequest(editMembershipRequestDto, membershipRequestEntity);
            return "redirect:/projects/list/" + user.getUsername() + "/";
        }
    }
}
