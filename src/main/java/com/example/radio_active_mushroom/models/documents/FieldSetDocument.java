package com.example.radio_active_mushroom.models.documents;

import lombok.*;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@TypeAlias(value = "fieldSet")
@Document(collection = "fieldSetDocument")
public class FieldSetDocument {
    @MongoId(targetType = FieldType.OBJECT_ID)
    private String id;

    private String name;

    private String description;

    private String friendlyName;

    private String projectName;

    private String projectOwnerUsername;

    @DocumentReference
    private List<FieldDocument> fields;

    @DocumentReference
    private TableDocument table;
}
