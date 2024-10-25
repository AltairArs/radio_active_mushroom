package com.example.radio_active_mushroom.services;

import com.example.radio_active_mushroom.dto.document.ChangeTablePositionDto;
import com.example.radio_active_mushroom.dto.document.CreateTableDto;
import com.example.radio_active_mushroom.dto.document.DeleteTableDto;
import com.example.radio_active_mushroom.dto.document.EditTableDto;
import com.example.radio_active_mushroom.dto.jsObjects.JS_FieldSet;
import com.example.radio_active_mushroom.dto.jsObjects.JS_Table;
import com.example.radio_active_mushroom.models.documents.FieldSetDocument;
import com.example.radio_active_mushroom.models.documents.TableDocument;
import com.example.radio_active_mushroom.models.documents.objects.Position;

import java.util.List;

public interface DB_DrawerService {
    public abstract List<JS_Table> generateTables(List<TableDocument> tableDocuments);
    public abstract JS_Table generateTable(TableDocument tableDocument);
    public abstract JS_FieldSet generateFieldSet(FieldSetDocument fieldSetDocument);
    public abstract List<TableDocument> findTables(String projectName, String projectOwnerName);
    public abstract List<JS_Table> getTables(String projectName, String projectOwnerName);
    public abstract boolean addTable(String projectName, String projectOwnerName, CreateTableDto createTableDto);
    public abstract void changeTablePosition(String projectName, String projectOwnerName, ChangeTablePositionDto changeTablePositionDto);
    public abstract void editTable(String projectName, String projectOwnerName, EditTableDto editTableDto);
    public abstract void deleteTable(String projectName, String projectOwnerName, DeleteTableDto deleteTableDto);
    public abstract JS_Table getTable(String projectName, String projectOwnerName, String tableName);
}
