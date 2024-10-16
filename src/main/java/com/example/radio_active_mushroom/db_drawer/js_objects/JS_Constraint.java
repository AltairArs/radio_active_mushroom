package com.example.radio_active_mushroom.db_drawer.js_objects;

import com.example.radio_active_mushroom.enums.ConstraintCheckComparatorTypeEnum;
import com.example.radio_active_mushroom.enums.ConstraintTypeEnum;
import com.example.radio_active_mushroom.models.embeddable.FieldId;
import lombok.*;

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
    private FieldId first_field_value;
    private FieldId second_field_value;

    private List<String> fields_names;
    private List<JS_FIeldId> ref_fields;
}
