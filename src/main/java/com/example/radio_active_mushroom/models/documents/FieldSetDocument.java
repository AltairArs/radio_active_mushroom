package com.example.radio_active_mushroom.models.documents;

import lombok.*;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@TypeAlias(value = "field_set")
@Document(collection = "field_set_document")
public class FieldSetDocument {
    @MongoId
    private String id;

    private String name;

    private String description;

    private String friendly_name;

    private String project_name;

    private String project_owner_username;

    @DocumentReference(lazy = true)
    private List<FieldDocument> fields;
}
