package com.example.radio_active_mushroom.db_drawer.js_objects;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JS_FieldSet {
    private String name;
    private String friendly_name;
    private String description;
    private List<JS_Constraint> constraints;
    private List<JS_Field> fields;
}
