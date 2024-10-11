package com.example.radio_active_mushroom.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_entity")
public class UserEntity {
    @Id
    @Column(name = "username", nullable = false)
    private String username;

}