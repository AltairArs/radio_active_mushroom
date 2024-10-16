package com.example.radio_active_mushroom.models.documents;

import com.example.radio_active_mushroom.db_drawer.constraint.Constraint;
import com.example.radio_active_mushroom.models.embeddable.FieldSetId;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Document("field_set_entity")
public class FieldSetDocument {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Embedded
    private FieldSetId field_set_id;

    @Column(name = "friendly_name")
    private String friendly_name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "constraints")
    private List<Constraint> constraints;
}