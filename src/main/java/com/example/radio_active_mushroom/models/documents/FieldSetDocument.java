package com.example.radio_active_mushroom.models.documents;

import com.example.radio_active_mushroom.db_drawer.constraint.Constraint;
import com.example.radio_active_mushroom.models.embeddable.FieldSetId;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
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

    @Column(name = "name", nullable = false)
    private String field_set_name;

    @Column(name = "project_owner_username", nullable = false)
    private String project_owner_username;

    @Column(name = "project_name", nullable = false)
    private String project_name;

    @Column(name = "friendly_name")
    private String friendly_name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "constraints")
    private List<Constraint> constraints = new ArrayList<Constraint>();
}