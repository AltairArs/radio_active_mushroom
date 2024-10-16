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

    @Embedded
    private FieldSetId field_set_id;

    @Column(name = "position_x", nullable = false)
    private Integer position_x;

    @Column(name = "position_y", nullable = false)
    private Integer position_y;

}