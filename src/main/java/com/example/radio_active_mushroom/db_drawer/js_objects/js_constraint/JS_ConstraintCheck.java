package com.example.radio_active_mushroom.db_drawer.js_objects.js_constraint;

import com.example.radio_active_mushroom.db_drawer.js_objects.js_field.JS_FieldId;
import com.example.radio_active_mushroom.enums.constraint.ConstraintCheckComparatorTypeEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JS_ConstraintCheck extends JS_Constraint{
    private ConstraintCheckComparatorTypeEnum comparator;
    private String first_simple_value;
    private String second_simple_value;
    private JS_FieldId first_field_value;
    private JS_FieldId second_field_value;
}
