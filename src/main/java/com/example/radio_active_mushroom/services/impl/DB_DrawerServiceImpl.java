package com.example.radio_active_mushroom.services.impl;

import com.example.radio_active_mushroom.dto.jsObjects.JS_FieldSet;
import com.example.radio_active_mushroom.dto.jsObjects.JS_Table;
import com.example.radio_active_mushroom.models.documents.FieldSetDocument;
import com.example.radio_active_mushroom.models.documents.TableDocument;
import com.example.radio_active_mushroom.models.documents.objects.Position;
import com.example.radio_active_mushroom.repo.documents.FieldSetDocumentRepository;
import com.example.radio_active_mushroom.repo.documents.TableDocumentRepository;
import com.example.radio_active_mushroom.services.DB_DrawerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DB_DrawerServiceImpl implements DB_DrawerService {

    @Autowired
    private FieldSetDocumentRepository fieldSetDocumentRepository;

    @Autowired
    private TableDocumentRepository tableDocumentRepository;

    @Override
    public List<JS_Table> generateTables(List<TableDocument> tableDocuments) {
        List<JS_Table> tables = new ArrayList<>();
        for (TableDocument tableDocument : tableDocuments) {
            tables.add(generateTable(tableDocument));
        }
        return tables;
    }

    @Override
    public JS_Table generateTable(TableDocument tableDocument) {
        return new JS_Table(
                tableDocument.getPosition(),
                generateFieldSet(tableDocument.getFieldSet())
        );
    }

    @Override
    public JS_FieldSet generateFieldSet(FieldSetDocument fieldSetDocument) {
        return new JS_FieldSet(
                fieldSetDocument.getName(),
                fieldSetDocument.getDescription(),
                fieldSetDocument.getFriendlyName()
        );
    }

    @Override
    public List<TableDocument> findTables(String projectName, String projectOwnerName) {
        List<TableDocument> tableDocuments = new ArrayList<>();
        for (FieldSetDocument fieldSetDocument : fieldSetDocumentRepository.findAllByProjectNameAndProjectOwnerUsername(projectName, projectOwnerName)) {
            if (fieldSetDocument.getTable() != null) {
                tableDocuments.add(fieldSetDocument.getTable());
            }
        }
        return tableDocuments;
    }

    @Override
    public List<JS_Table> getTables(String projectName, String projectOwnerName) {
        return generateTables(findTables(projectName, projectOwnerName));
    }

    @Override
    public boolean addTable(Position position, String projectName, String projectOwnerName, String name, String description, String friendlyName) {
        if (fieldSetDocumentRepository.existsByProjectNameAndProjectOwnerUsernameAndName(projectName, projectOwnerName, name)) {
            return false;
        } else {
            TableDocument tableDocument = new TableDocument();
            tableDocument.setPosition(position);

            FieldSetDocument fieldSetDocument = new FieldSetDocument();
            fieldSetDocument.setProjectName(projectName);
            fieldSetDocument.setProjectOwnerUsername(projectOwnerName);
            fieldSetDocument.setFriendlyName(friendlyName);
            fieldSetDocument.setDescription(description);
            fieldSetDocument.setName(name);

            fieldSetDocument.setTable(tableDocument);
            tableDocument.setFieldSet(fieldSetDocument);

            fieldSetDocumentRepository.save(fieldSetDocument);
            return true;
        }
    }
}
