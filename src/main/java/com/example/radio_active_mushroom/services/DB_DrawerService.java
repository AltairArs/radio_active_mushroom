package com.example.radio_active_mushroom.services;

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
    public abstract boolean addTable(Position position, String projectName, String projectOwnerName, String name, String description, String friendlyName);
}
