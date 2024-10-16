package com.example.radio_active_mushroom.db_drawer.constraint;

import com.example.radio_active_mushroom.models.embeddable.FieldId;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConstraintForeignKey extends Constraint{
    private List<FieldId> fields;
    private List<FieldId> ref_fields;
}
