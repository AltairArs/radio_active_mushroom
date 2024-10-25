package com.example.radio_active_mushroom.dto.document;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EditTableDto {
    private String tableName;
    private String friendlyName;
    private String description;
}
