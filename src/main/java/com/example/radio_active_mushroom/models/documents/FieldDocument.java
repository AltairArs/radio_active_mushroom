package com.example.radio_active_mushroom.models.documents;

import com.example.radio_active_mushroom.db_drawer.constraint.Constraint;
import com.example.radio_active_mushroom.db_drawer.field_type.FieldType;
import com.example.radio_active_mushroom.models.embeddable.FieldId;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Document("field_entity")
public class FieldDocument {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Embedded
    private FieldId field_id;

    @Column(name = "friendly_name")
    private String friendly_name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "constraints")
    private List<Constraint> constraints;

    @Column(name = "type")
    private FieldType type;

}