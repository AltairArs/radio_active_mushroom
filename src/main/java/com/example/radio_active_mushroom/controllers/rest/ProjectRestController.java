package com.example.radio_active_mushroom.controllers.rest;

import com.example.radio_active_mushroom.dto.AJAX_Form;
import com.example.radio_active_mushroom.dto.document.CreateTableDto;
import com.example.radio_active_mushroom.services.DB_DrawerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("projects/edit/{username}/{projectName}/")
public class ProjectRestController {
    @Autowired
    private DB_DrawerService dbDrawerService;

    @PostMapping("add/table/")
    public @ResponseBody ResponseEntity<AJAX_Form> addTable(@PathVariable String username, @PathVariable String projectName, @ModelAttribute("formCreateTable") @Valid @RequestBody CreateTableDto formCreateTable, BindingResult bindingResult) {
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

    @GetMapping("get/all/")
    public @ResponseBody ResponseEntity<AJAX_Form> getAll(@PathVariable String username, @PathVariable String projectName) {
        AJAX_Form form = new AJAX_Form();
        form.setTables(dbDrawerService.getTables(projectName, username));
        return ResponseEntity.ok().body(form);
    }
}
