package com.example.radio_active_mushroom.db_drawer.js_objects.js_field;

import com.example.radio_active_mushroom.db_drawer.js_objects.js_constraint.JS_Constraint;
import com.example.radio_active_mushroom.enums.field.FIeldElementTypeEnum;
import com.example.radio_active_mushroom.enums.field.FieldContainerTypeEnum;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public abstract class JS_Field {
    private String name;
    private String friendly_name;
    private String description;
    private List<JS_Constraint> constraints;

    private FieldContainerTypeEnum container_type;
    private FIeldElementTypeEnum element_type;
}
