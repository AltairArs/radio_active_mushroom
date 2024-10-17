package com.example.radio_active_mushroom.db_drawer.js_objects;

import com.example.radio_active_mushroom.db_drawer.js_objects.js_field.JS_Field;
import com.example.radio_active_mushroom.enums.field.FieldSimpleTypeEnum;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JS_FieldSimple extends JS_Field {
    private FieldSimpleTypeEnum field_simple_type;
    private Map<String, String> parameters = new HashMap<String, String>();
}
