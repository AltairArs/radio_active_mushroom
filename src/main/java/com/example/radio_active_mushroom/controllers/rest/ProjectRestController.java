package com.example.radio_active_mushroom.controllers.rest;

import com.example.radio_active_mushroom.dto.document.CreateTable;
import com.example.radio_active_mushroom.services.DB_DrawerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("projects/edit/{username}/{projectName}/")
public class ProjectRestController {
    @Autowired
    private DB_DrawerService dbDrawerService;

    @PostMapping("add/table/")
    public @ResponseBody String addTable(@PathVariable String username, @PathVariable String projectName, @ModelAttribute("formCreateTable") @Valid @RequestBody CreateTable formCreateTable) {
        return formCreateTable.toString();
    }
}
