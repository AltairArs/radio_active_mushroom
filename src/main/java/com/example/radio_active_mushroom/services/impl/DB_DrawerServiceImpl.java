package com.example.radio_active_mushroom.services.impl;

import com.example.radio_active_mushroom.dto.document.ChangeTablePositionDto;
import com.example.radio_active_mushroom.dto.document.CreateTableDto;
import com.example.radio_active_mushroom.dto.document.DeleteTableDto;
import com.example.radio_active_mushroom.dto.document.EditTableDto;
import com.example.radio_active_mushroom.dto.jsObjects.JS_FieldSet;
import com.example.radio_active_mushroom.dto.jsObjects.JS_Table;
import com.example.radio_active_mushroom.models.documents.FieldSetDocument;
import com.example.radio_active_mushroom.models.documents.TableDocument;
import com.example.radio_active_mushroom.models.documents.objects.Position;
import com.example.radio_active_mushroom.repo.documents.FieldSetDocumentRepository;
import com.example.radio_active_mushroom.repo.documents.TableDocumentRepository;
import com.example.radio_active_mushroom.services.DB_DrawerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DB_DrawerServiceImpl implements DB_DrawerService {

    private final FieldSetDocumentRepository fieldSetDocumentRepository;

    private final TableDocumentRepository tableDocumentRepository;

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
                generateFieldSet(fieldSetDocumentRepository.getById(tableDocument.getFieldSet()))
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
    public boolean addTable(String projectName, String projectOwnerName, CreateTableDto createTableDto) {
        if (fieldSetDocumentRepository.existsByProjectNameAndProjectOwnerUsernameAndName(projectName, projectOwnerName, createTableDto.getName())) {
            return false;
        } else {
            TableDocument tableDocument = new TableDocument();
            tableDocument.setPosition(new Position(
                    createTableDto.getPosition_x(),
                    createTableDto.getPosition_y()
            ));

            FieldSetDocument fieldSetDocument = new FieldSetDocument();
            fieldSetDocument.setProjectName(projectName);
            fieldSetDocument.setProjectOwnerUsername(projectOwnerName);
            fieldSetDocument.setFriendlyName(createTableDto.getFriendlyName());
            fieldSetDocument.setDescription(createTableDto.getDescription());
            fieldSetDocument.setName(createTableDto.getName());

            fieldSetDocumentRepository.save(fieldSetDocument);
            tableDocumentRepository.save(tableDocument);

            fieldSetDocument.setTable(tableDocument);
            tableDocument.setFieldSet(fieldSetDocument.getId());

            fieldSetDocumentRepository.save(fieldSetDocument);
            tableDocumentRepository.save(tableDocument);
            return true;
        }
    }

    @Override
    public void changeTablePosition(String projectName, String projectOwnerName, ChangeTablePositionDto changeTablePositionDto) {
        FieldSetDocument fieldSetDocument = fieldSetDocumentRepository.getByProjectNameAndProjectOwnerUsernameAndName(projectName, projectOwnerName, changeTablePositionDto.getTableName());
        fieldSetDocument.getTable().setPosition(changeTablePositionDto.toPosition());
        tableDocumentRepository.save(fieldSetDocument.getTable());
    }

    @Override
    public void editTable(String projectName, String projectOwnerName, EditTableDto editTableDto) {
        FieldSetDocument fieldSetDocument = fieldSetDocumentRepository.getByProjectNameAndProjectOwnerUsernameAndName(projectName, projectOwnerName, editTableDto.getTableName());
        if (fieldSetDocument != null) {
            fieldSetDocument.setDescription(editTableDto.getDescription());
            fieldSetDocument.setFriendlyName(editTableDto.getFriendlyName());
            fieldSetDocumentRepository.save(fieldSetDocument);
        }
    }

    @Override
    public void deleteTable(String projectName, String projectOwnerName, DeleteTableDto deleteTableDto) {
        FieldSetDocument fieldSetDocument = fieldSetDocumentRepository.getByProjectNameAndProjectOwnerUsernameAndName(projectName, projectOwnerName, deleteTableDto.getTableName());
        if (fieldSetDocument != null){
            tableDocumentRepository.delete(fieldSetDocument.getTable());
            fieldSetDocumentRepository.delete(fieldSetDocument);
        }
    }

    @Override
    public JS_Table getTable(String projectName, String projectOwnerName, String tableName) {
        return generateTable(fieldSetDocumentRepository.getByProjectNameAndProjectOwnerUsernameAndName(projectName, projectOwnerName, tableName).getTable());
    }
}
