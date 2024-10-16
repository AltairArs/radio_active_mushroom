package com.example.radio_active_mushroom.db_drawer.constraint;

import com.example.radio_active_mushroom.models.embeddable.FieldId;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//Can be only: PRIMARY_KEY, UNIQUE
public class ConstraintOnFields extends Constraint {
    private List<FieldId> fields;
}
