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
@Table(name = "user_verification_token_entity")
public class UserVerificationTokenEntity {
    @Id
    private String username;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "username", referencedColumnName = "username")
    private UserEntity user;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at = LocalDateTime.now();

    @Column(name = "verification_token", nullable = false)
    private String verification_token;

}