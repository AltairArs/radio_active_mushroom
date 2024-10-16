package com.example.radio_active_mushroom.db_drawer;

import com.example.radio_active_mushroom.db_drawer.js_objects.JS_FieldSet;
import com.example.radio_active_mushroom.db_drawer.js_objects.JS_Table;
import com.example.radio_active_mushroom.models.documents.DB_DrawerSettings;
import com.example.radio_active_mushroom.models.documents.TableDocument;
import com.example.radio_active_mushroom.repo.FieldDocumentRepository;
import com.example.radio_active_mushroom.repo.FieldSetDocumentRepository;
import com.example.radio_active_mushroom.repo.TableDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DB_Drawer {

    @Autowired
    private FieldDocumentRepository fieldRepository;

    @Autowired
    private TableDocumentRepository tableRepository;

    @Autowired
    private FieldSetDocumentRepository fieldSetRepository;

    public void convertTables(List<TableDocument> tables, DB_DrawerSettings settings) {

    }

    public List<JS_Table> generateTables(List<TableDocument> tables) {
        List<JS_Table> js_tables = new ArrayList<JS_Table>();
        for (TableDocument table : tables) {
            JS_Table js_table = new JS_Table();
        }
        return js_tables;
    }
    public JS_Table generateTable(TableDocument table) {
        JS_Table js_table = new JS_Table();
        js_table.setPosition_y(table.getPosition_y());
        js_table.setPosition_x(table.getPosition_x());
        return js_table;
    }
}
