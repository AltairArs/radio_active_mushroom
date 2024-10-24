package com.example.radio_active_mushroom.controllers.rest;

import com.example.radio_active_mushroom.dto.document.CreateTableDto;
import com.example.radio_active_mushroom.services.DB_DrawerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("projects/edit/{username}/{projectName}/")
public class ProjectRestController {
    @Autowired
    private DB_DrawerService dbDrawerService;

    @PostMapping("add/table/")
    public @ResponseBody ResponseEntity<CreateTableDto> addTable(@PathVariable String username, @PathVariable String projectName, @ModelAttribute("formCreateTable") @Valid @RequestBody CreateTableDto formCreateTable, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            formCreateTable.setErrorsFromErrorList(bindingResult.getAllErrors());
            return ResponseEntity.ok().body(formCreateTable);
        } else {
            return ResponseEntity.ok().body(formCreateTable);
        }
    }
}
