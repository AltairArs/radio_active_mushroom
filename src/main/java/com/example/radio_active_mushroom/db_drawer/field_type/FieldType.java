package com.example.radio_active_mushroom.db_drawer.field_type;

import com.example.radio_active_mushroom.enums.FIeldElementTypeEnum;
import com.example.radio_active_mushroom.enums.FieldContainerTypeEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public abstract class FieldType {
    private FieldContainerTypeEnum container_type;
    private FIeldElementTypeEnum element_type;
}
