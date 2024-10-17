package com.example.radio_active_mushroom.db_drawer.constraint;

import com.example.radio_active_mushroom.enums.constraint.ConstraintCheckComparatorTypeEnum;
import com.example.radio_active_mushroom.models.embeddable.FieldId;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConstraintCheck extends Constraint{
    private ConstraintCheckComparatorTypeEnum comparator;
    private String first_simple_value;
    private String second_simple_value;
    private FieldId first_field_value;
    private FieldId second_field_value;
}
