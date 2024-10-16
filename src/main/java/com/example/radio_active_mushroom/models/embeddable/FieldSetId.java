package com.example.radio_active_mushroom.models.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class FieldSetId {
    @Column(name = "name", nullable = false)
    private String field_set_name;

    @Column(name = "project_owner_username", nullable = false)
    private String project_owner_username;

    @Column(name = "project_name", nullable = false)
    private String project_name;
}