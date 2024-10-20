package com.example.radio_active_mushroom.dto.jsObjects;

import com.example.radio_active_mushroom.models.documents.objects.Position;
import lombok.*;
import org.springframework.data.annotation.TypeAlias;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@TypeAlias(value = "table")
public class JS_Table {
    private Position position;

    private JS_FieldSet fieldSet;
}
