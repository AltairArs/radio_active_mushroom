package com.example.radio_active_mushroom.models;

import com.example.radio_active_mushroom.enums.UserRoleEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

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

    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at = LocalDateTime.now();

    @Column(name = "last_login")
    private LocalDateTime last_login;

    @Column(name = "verification_token")
    private String verification_token;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false, length = 30)
    private UserRoleEnum role = UserRoleEnum.ROLE_USER;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProjectEntity> projects_as_owner = new LinkedHashSet<>();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MembershipRequestEntity> membership_requests = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "members", cascade = CascadeType.REFRESH)
    private Set<ProjectEntity> projects_as_member = new LinkedHashSet<>();

}