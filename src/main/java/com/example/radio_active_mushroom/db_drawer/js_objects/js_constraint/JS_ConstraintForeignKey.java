package com.example.radio_active_mushroom.db_drawer.js_objects.js_constraint;

import com.example.radio_active_mushroom.db_drawer.js_objects.js_field.JS_FieldId;
import com.example.radio_active_mushroom.enums.constraint.ConstraintForeignKeyTypeRelationshipEnum;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JS_ConstraintForeignKey extends JS_Constraint{
    private List<String> fields_names = new ArrayList<String>();
    private List<JS_FieldId> ref_fields = new ArrayList<JS_FieldId>();
    private ConstraintForeignKeyTypeRelationshipEnum relationship;
}
