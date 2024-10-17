package com.example.radio_active_mushroom.db_drawer;

import com.example.radio_active_mushroom.db_drawer.constraint.Constraint;
import com.example.radio_active_mushroom.db_drawer.constraint.ConstraintCheck;
import com.example.radio_active_mushroom.db_drawer.constraint.ConstraintForeignKey;
import com.example.radio_active_mushroom.db_drawer.constraint.ConstraintOnFields;
import com.example.radio_active_mushroom.db_drawer.field_type.FieldTypeFieldSet;
import com.example.radio_active_mushroom.db_drawer.field_type.FieldTypeSimple;
import com.example.radio_active_mushroom.db_drawer.js_objects.*;
import com.example.radio_active_mushroom.db_drawer.js_objects.js_constraint.JS_Constraint;
import com.example.radio_active_mushroom.db_drawer.js_objects.js_constraint.JS_ConstraintCheck;
import com.example.radio_active_mushroom.db_drawer.js_objects.js_constraint.JS_ConstraintForeignKey;
import com.example.radio_active_mushroom.db_drawer.js_objects.js_constraint.JS_ConstraintOnFields;
import com.example.radio_active_mushroom.db_drawer.js_objects.js_field.JS_Field;
import com.example.radio_active_mushroom.db_drawer.js_objects.js_field.JS_FieldFieldSet;
import com.example.radio_active_mushroom.db_drawer.js_objects.js_field.JS_FieldId;
import com.example.radio_active_mushroom.models.documents.DB_DrawerSettings;
import com.example.radio_active_mushroom.models.documents.FieldDocument;
import com.example.radio_active_mushroom.models.documents.FieldSetDocument;
import com.example.radio_active_mushroom.models.documents.TableDocument;
import com.example.radio_active_mushroom.models.embeddable.FieldId;
import com.example.radio_active_mushroom.models.embeddable.FieldSetId;
import com.example.radio_active_mushroom.repo.documents.FieldDocumentRepository;
import com.example.radio_active_mushroom.repo.documents.FieldSetDocumentRepository;
import com.example.radio_active_mushroom.repo.documents.TableDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DB_Drawer {

    @Autowired
    private FieldDocumentRepository fieldRepository;

    @Autowired
    private TableDocumentRepository tableRepository;

    @Autowired
    private FieldSetDocumentRepository fieldSetRepository;

    public List<JS_Table> convertTables(List<TableDocument> tables, DB_DrawerSettings settings) {
        List<JS_Table> js_tables = new ArrayList<JS_Table>();

        return js_tables;
    }

    public List<JS_Table> generateTables(List<TableDocument> tables) {
        List<JS_Table> js_tables = new ArrayList<JS_Table>();
        for (TableDocument table : tables) {
            js_tables.add(generateTable(table));
        }
        return js_tables;
    }
    private JS_Table generateTable(TableDocument table) {
        JS_Table js_table = new JS_Table();
        js_table.setPosition_y(table.getPosition_y());
        js_table.setPosition_x(table.getPosition_x());
        js_table.setFieldSet(generateFieldSet(fieldSetRepository.findByField_set_nameAndProject_owner_usernameAndProject_name(
                table.getField_set_name(),
                table.getProject_owner_username(),
                table.getProject_name()
        ).get()));
        return js_table;
    }
    private JS_FieldSet generateFieldSet(FieldSetDocument fieldSet) {
        JS_FieldSet js_fieldSet = new JS_FieldSet();
        js_fieldSet.setName(fieldSet.getField_set_name());
        js_fieldSet.setFriendly_name(fieldSet.getFriendly_name());
        js_fieldSet.setDescription(fieldSet.getDescription());

        for (Constraint constraint : fieldSet.getConstraints()){
            js_fieldSet.getConstraints().add(generateConstraint(constraint));
        }
        for (FieldDocument fieldDocument : fieldRepository.findAllByField_set_id(
                new FieldSetId(
                        fieldSet.getField_set_name(),
                        fieldSet.getProject_owner_username(),
                        fieldSet.getProject_name()
                )
        )) {
            js_fieldSet.getFields().add(generateField(fieldDocument));
        }

        return js_fieldSet;
    }
    private JS_Field generateField(FieldDocument field) {
        switch (field.getType().getElement_type()) {
            case BASE: {
                JS_FieldSimple js_field = new JS_FieldSimple();
                js_field.setName(field.getName());
                js_field.setFriendly_name(field.getFriendly_name());
                js_field.setDescription(field.getDescription());

                FieldTypeSimple field_type = (FieldTypeSimple) field.getType();
                js_field.setField_simple_type(field_type.getField_simple_type());
                js_field.setParameters(field_type.getParameters());
                return js_field;
            }
            case FIELDSET: {
                JS_FieldFieldSet js_field = new JS_FieldFieldSet();
                js_field.setName(field.getName());
                js_field.setFriendly_name(field.getFriendly_name());
                js_field.setDescription(field.getDescription());
                FieldTypeFieldSet field_type = (FieldTypeFieldSet) field.getType();
                js_field.setField_set_type(generateFieldSet(fieldSetRepository.findByField_set_nameAndProject_owner_usernameAndProject_name(
                        field_type.getField_set_id().getField_set_name(),
                        field_type.getField_set_id().getProject_owner_username(),
                        field_type.getField_set_id().getProject_name()
                ).get()));
                return js_field;
            }
            default: {
                return null;
            }
        }
    }
    private JS_Constraint generateConstraint(Constraint constraint) {
        switch(constraint.getType()){
            case CHECK: {
                JS_ConstraintCheck js_constraint = new JS_ConstraintCheck();
                ConstraintCheck constraintCheck = (ConstraintCheck) constraint;
                js_constraint.setName(constraint.getName());
                js_constraint.setType(constraint.getType());
                js_constraint.setComparator(constraintCheck.getComparator());
                js_constraint.setFirst_simple_value(constraintCheck.getFirst_simple_value());
                js_constraint.setSecond_simple_value(constraintCheck.getSecond_simple_value());
                js_constraint.setFirst_field_value(generateFieldId(constraintCheck.getFirst_field_value()));
                js_constraint.setSecond_field_value(generateFieldId(constraintCheck.getSecond_field_value()));
                return js_constraint;
            }
            case FOREIGN_KEY: {
                JS_ConstraintForeignKey js_constraint = new JS_ConstraintForeignKey();
                ConstraintForeignKey constraintForeignKey = (ConstraintForeignKey) constraint;
                js_constraint.setRelationship(constraintForeignKey.getRelationship());
                js_constraint.setName(constraint.getName());
                js_constraint.setType(constraint.getType());
                for (FieldId fieldId : constraintForeignKey.getFields()){
                    js_constraint.getFields_names().add(fieldId.getName());
                }
                for (FieldId fieldId : constraintForeignKey.getRef_fields()){
                    js_constraint.getRef_fields().add(generateFieldId(fieldId));
                }
                return js_constraint;
            }
            case UNIQUE, PRIMARY_KEY: {
                JS_ConstraintOnFields js_constraint = new JS_ConstraintOnFields();
                js_constraint.setName(constraint.getName());
                js_constraint.setType(constraint.getType());
                ConstraintOnFields constraintOnFields = (ConstraintOnFields) constraint;
                for (FieldId fieldId : constraintOnFields.getFields()){
                    js_constraint.getFields_names().add(fieldId.getName());
                }
                return js_constraint;
            }
            default: {
                JS_Constraint js_constraint = new JS_Constraint();
                js_constraint.setName(constraint.getName());
                js_constraint.setType(constraint.getType());
                return js_constraint;
            }
        }
    }
    private JS_FieldId generateFieldId(FieldId fieldId) {
        JS_FieldId js_fIeldId = new JS_FieldId();
        js_fIeldId.setName(fieldId.getName());
        js_fIeldId.setField_set_name(fieldId.getField_set_id().getField_set_name());
        return js_fIeldId;
    }

    public List<TableDocument> getTableDocuments(String project_owner_username, String project_name){
        return tableRepository.findByProject_nameAndProject_owner_username(project_name, project_owner_username);
    }

    public boolean addTable(String name, String project_owner_username, String project_name, Integer position_x, Integer position_y){
        if (!tableRepository.findByProject_nameAndProject_ownerAndField_set_name(project_owner_username, project_name, name).isPresent()){
            TableDocument tableDocument = new TableDocument();
            tableDocument.setField_set_name(name);
            tableDocument.setProject_name(project_name);
            tableDocument.setPosition_x(position_x);
            tableDocument.setPosition_y(position_y);
            tableDocument.setProject_owner_username(project_owner_username);
            tableRepository.save(tableDocument);
            return true;
        } else return false;
    }
    public void deleteTable(String name, String project_owner_username, String project_name){
        Optional<TableDocument> tableDocument = tableRepository.findByProject_nameAndProject_ownerAndField_set_name(project_owner_username, project_name, name);
        if (tableDocument.isPresent()){
            tableRepository.delete(tableDocument.get());
        }
    }
    public boolean updateTable(String name, String project_owner_username, String project_name, String new_name){
        Optional<FieldSetDocument> fieldSetDocument = fieldSetRepository.findByField_set_nameAndProject_owner_usernameAndProject_name(new_name, project_owner_username, project_name);
        if (fieldSetDocument.isPresent()){
            return false;
        } else {
            Optional<TableDocument> tableDocument = tableRepository.findByProject_nameAndProject_ownerAndField_set_name(project_owner_username, project_name, name);
            if (tableDocument.isPresent()){
                TableDocument tableDoc = tableDocument.get();
                tableDoc.setField_set_name(new_name);
                tableRepository.save(tableDocument.get());
            }
            return true;
        }
    }
    public void changeTablePosition(String name, String project_owner_username, String project_name, Integer new_position_x, Integer new_position_y){
        Optional<TableDocument> tableDocument = tableRepository.findByProject_nameAndProject_ownerAndField_set_name(project_owner_username, project_name, name);
        if (tableDocument.isPresent()){
            TableDocument tableDoc = tableDocument.get();
            tableDoc.setPosition_x(new_position_x);
            tableDoc.setPosition_y(new_position_y);
            tableRepository.save(tableDocument.get());
        }
    }
}
