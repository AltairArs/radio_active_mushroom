package com.example.radio_active_mushroom.db_drawer.field_type;

import com.example.radio_active_mushroom.enums.field.FieldSimpleTypeEnum;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FieldTypeSimple extends FieldType{
    private FieldSimpleTypeEnum field_simple_type;
    private Map<String, String> parameters = new HashMap<String, String>();
}
