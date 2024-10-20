package com.example.radio_active_mushroom.models.documents;

import com.example.radio_active_mushroom.models.documents.objects.Position;
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
@TypeAlias(value = "table")
@Document(collection = "tableDocument")
public class TableDocument {
    @MongoId
    private String id;

    private Position position;

    @DocumentReference(lazy = true)
    @ReadOnlyProperty
    private FieldSetDocument fieldSet;
}
