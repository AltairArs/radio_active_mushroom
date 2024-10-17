package com.example.radio_active_mushroom.models.documents;

import com.example.radio_active_mushroom.models.embeddable.FieldSetId;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.awt.geom.Point2D;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Document("table_entity")
public class TableDocument {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String field_set_name;

    @Column(name = "project_owner_username", nullable = false)
    private String project_owner_username;

    @Column(name = "project_name", nullable = false)
    private String project_name;

    @Column(name = "position_x", nullable = false)
    private Integer position_x;

    @Column(name = "position_y", nullable = false)
    private Integer position_y;

}