package com.example.radio_active_mushroom.models.documents;

import lombok.*;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@TypeAlias(value = "field")
@Document(collection = "fieldDocument")
public class FieldDocument {
    @MongoId
    private String id;

    private String name;

    private String description;

    private String friendlyName;

    @DocumentReference(lazy = true)
    @ReadOnlyProperty
    private FieldSetDocument fieldSet;
}
