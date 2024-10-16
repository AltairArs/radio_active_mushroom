package com.example.radio_active_mushroom.db_drawer.constraint;

import com.example.radio_active_mushroom.enums.ConstraintTypeEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Constraint {
    private String name;
    private ConstraintTypeEnum type;
}
