package com.example.radio_active_mushroom.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user_entity")
public class UserEntity {
    @Id
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false, length = 256)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "first_name", nullable = false)
    private String first_name;

    @Column(name = "last_name", nullable = false)
    private String last_name;

    @Column(name = "is_active", nullable = false)
    private Boolean is_active = false;

    @Column(name = "is_staff", nullable = false)
    private Boolean is_staff = false;

    @Column(name = "is_superuser", nullable = false)
    private Boolean is_superuser = false;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at = LocalDateTime.now();

    @Column(name = "last_login")
    private LocalDateTime last_login;

}