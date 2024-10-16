package com.example.radio_active_mushroom.db_drawer.js_objects;

import com.example.radio_active_mushroom.enums.ConstraintCheckComparatorTypeEnum;
import com.example.radio_active_mushroom.enums.ConstraintTypeEnum;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JS_Constraint {
    private String name;
    private ConstraintTypeEnum type;

    private ConstraintCheckComparatorTypeEnum comparator;
    private String first_simple_value;
    private String second_simple_value;
    private JS_FieldId first_field_value;
    private JS_FieldId second_field_value;

    private List<String> fields_names = new ArrayList<String>();
    private List<JS_FieldId> ref_fields = new ArrayList<JS_FieldId>();
}
