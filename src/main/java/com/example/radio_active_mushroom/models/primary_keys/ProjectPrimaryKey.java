package com.example.radio_active_mushroom.models.primary_keys;

import com.example.radio_active_mushroom.models.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
public class ProjectPrimaryKey implements Serializable {
    @Column(name = "name", nullable = false)
    private String name;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "ownerUsername")
    private UserEntity owner;
}