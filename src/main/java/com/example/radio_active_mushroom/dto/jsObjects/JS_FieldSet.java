package com.example.radio_active_mushroom.dto.jsObjects;

import lombok.*;
import org.springframework.data.annotation.TypeAlias;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@TypeAlias(value = "fieldSet")
public class JS_FieldSet {
    private String name;

    private String description;

    private String friendlyName;
}
