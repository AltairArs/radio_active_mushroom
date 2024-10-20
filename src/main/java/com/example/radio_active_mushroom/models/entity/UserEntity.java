package com.example.radio_active_mushroom.models.entity;

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
@Table(name = "userEntity")
public class UserEntity {
    @Id
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false, length = 256)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "isActive", nullable = false)
    private Boolean isActive = false;

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "lastLogin")
    private LocalDateTime lastLogin;

    @Column(name = "verificationToken")
    private String verificationToken;

    @Enumerated(EnumType.STRING)
    @Column(name = "userRole", nullable = false, length = 30)
    private UserRoleEnum role = UserRoleEnum.ROLE_USER;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProjectEntity> projectsAsOwner = new LinkedHashSet<>();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MembershipRequestEntity> membershipRequests = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "members", cascade = CascadeType.REFRESH)
    private Set<ProjectEntity> projectsAsMember = new LinkedHashSet<>();

}