package com.example.radio_active_mushroom.controllers.rest;

import com.example.radio_active_mushroom.authentication.CustomAuthenticationService;
import com.example.radio_active_mushroom.dto.AJAX_Form;
import com.example.radio_active_mushroom.dto.document.ChangeTablePositionDto;
import com.example.radio_active_mushroom.dto.document.CreateTableDto;
import com.example.radio_active_mushroom.dto.document.DeleteTableDto;
import com.example.radio_active_mushroom.dto.document.EditTableDto;
import com.example.radio_active_mushroom.dto.jsObjects.JS_Table;
import com.example.radio_active_mushroom.enums.ProjectKindPermissionsEnum;
import com.example.radio_active_mushroom.services.DB_DrawerService;
import com.example.radio_active_mushroom.services.ProjectService;
import com.example.radio_active_mushroom.services.UserProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("projects/edit/{username}/{projectName}/")
public class ProjectRestController {

    @Autowired
    private DB_DrawerService dbDrawerService;

    @Autowired
    private CustomAuthenticationService authenticationService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserProfileService userProfileService;

    @PostMapping("table/add/")
    public @ResponseBody ResponseEntity<AJAX_Form> addTable(Authentication authentication, @PathVariable String username, @PathVariable String projectName, @ModelAttribute("formCreateTable") @Valid @RequestBody CreateTableDto formCreateTable, BindingResult bindingResult) {
        if (!projectService.determineCanActionBeTaken(userProfileService.getUserEntity(username), projectName, authenticationService.getCurrentUser(authentication), ProjectKindPermissionsEnum.CAN_EDIT_WORKSPACE)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        AJAX_Form form = new AJAX_Form();
        if (bindingResult.hasErrors()) {
            form.setErrorsFromErrorList(bindingResult.getAllErrors());
        } else {
            if (dbDrawerService.addTable(projectName, username, formCreateTable)) {
                form.setTables(dbDrawerService.getTables(projectName, username));
            } else {
                bindingResult.addError(new FieldError("formCreateTable", "name", "Таблица с таким именем уже есть в проекте"));
                form.setErrorsFromErrorList(bindingResult.getAllErrors());
            }
        }
        return ResponseEntity.ok().body(form);
    }

    @GetMapping("table/get/all/")
    public @ResponseBody ResponseEntity<AJAX_Form> getAll(Authentication authentication, @PathVariable String username, @PathVariable String projectName) {
        if (!projectService.determineCanActionBeTaken(userProfileService.getUserEntity(username), projectName, authenticationService.getCurrentUser(authentication), ProjectKindPermissionsEnum.CAN_SEE_WORKSPACE)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        AJAX_Form form = new AJAX_Form();
        form.setTables(dbDrawerService.getTables(projectName, username));
        return ResponseEntity.ok().body(form);
    }

    @PostMapping("table/edit/position/")
    public @ResponseBody ResponseEntity<AJAX_Form> changeTablePosition(Authentication authentication, @PathVariable String username, @PathVariable String projectName, @ModelAttribute("formChangeTablePosition") @RequestBody ChangeTablePositionDto changeTablePositionDto){
        if (!projectService.determineCanActionBeTaken(userProfileService.getUserEntity(username), projectName, authenticationService.getCurrentUser(authentication), ProjectKindPermissionsEnum.CAN_EDIT_WORKSPACE)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        dbDrawerService.changeTablePosition(projectName, username, changeTablePositionDto);
        return ResponseEntity.ok().body(new AJAX_Form());
    }

    @PostMapping("table/edit/")
    public @ResponseBody ResponseEntity<AJAX_Form> editTable(Authentication authentication, @PathVariable String username, @PathVariable String projectName, @ModelAttribute("formEditTable") @RequestBody EditTableDto editTableDto){
        if (!projectService.determineCanActionBeTaken(userProfileService.getUserEntity(username), projectName, authenticationService.getCurrentUser(authentication), ProjectKindPermissionsEnum.CAN_EDIT_WORKSPACE)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        dbDrawerService.editTable(projectName, username, editTableDto);
        return getAll(authentication, username, projectName);
    }

    @PostMapping("table/delete/")
    public @ResponseBody ResponseEntity<AJAX_Form> deleteTable(Authentication authentication, @PathVariable String username, @PathVariable String projectName, @ModelAttribute("formDeleteTable") @RequestBody DeleteTableDto deleteTableDto) {
        if (!projectService.determineCanActionBeTaken(userProfileService.getUserEntity(username), projectName, authenticationService.getCurrentUser(authentication), ProjectKindPermissionsEnum.CAN_EDIT_WORKSPACE)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        dbDrawerService.deleteTable(projectName, username, deleteTableDto);
        return getAll(authentication, username, projectName);
    }

    @GetMapping("table/get/{tableName}/")
    public @ResponseBody ResponseEntity<JS_Table> getTable(Authentication authentication, @PathVariable String username, @PathVariable String projectName, @PathVariable String tableName) {
        if (!projectService.determineCanActionBeTaken(userProfileService.getUserEntity(username), projectName, authenticationService.getCurrentUser(authentication), ProjectKindPermissionsEnum.CAN_EDIT_WORKSPACE)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok().body(dbDrawerService.getTable(projectName, username, tableName));
    }
}
