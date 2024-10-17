package com.example.radio_active_mushroom.db_drawer.js_objects.js_constraint;

import com.example.radio_active_mushroom.enums.constraint.ConstraintTypeEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JS_Constraint {
    private String name;
    private ConstraintTypeEnum type;
}
