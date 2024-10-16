package com.example.radio_active_mushroom.models.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class FieldId {
    @Embedded
    private FieldSetId field_set_id;

    @Column(name = "name", nullable = false)
    private String name;
}