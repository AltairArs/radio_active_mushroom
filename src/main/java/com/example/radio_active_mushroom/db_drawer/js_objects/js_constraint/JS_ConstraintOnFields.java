package com.example.radio_active_mushroom.db_drawer.js_objects.js_constraint;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JS_ConstraintOnFields extends JS_Constraint{
    private List<String> fields_names = new ArrayList<String>();
}
