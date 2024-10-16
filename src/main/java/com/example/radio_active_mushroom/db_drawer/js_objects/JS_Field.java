package com.example.radio_active_mushroom.db_drawer.js_objects;

import com.example.radio_active_mushroom.enums.FIeldElementTypeEnum;
import com.example.radio_active_mushroom.enums.FieldContainerTypeEnum;
import com.example.radio_active_mushroom.enums.FieldSimpleTypeEnum;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JS_Field {
    private String name;
    private String friendly_name;
    private String description;
    private List<JS_Constraint> constraints;

    private FieldContainerTypeEnum container_type;
    private FIeldElementTypeEnum element_type;

    private FieldSimpleTypeEnum field_simple_type;
    private Map<String, String> parameters;

    private JS_FieldSet field_set_type;
}
