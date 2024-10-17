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
}
