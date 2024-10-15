package com.example.radio_active_mushroom.models;

import com.example.radio_active_mushroom.enums.ProjectPermissionsEnum;
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
@Table(name = "project_entity")
public class ProjectEntity {
    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "friendly_name")
    private String friendly_name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "owner_username", nullable = false)
    private UserEntity userEntity;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at = LocalDateTime.now();

    @Column(name = "last_update", nullable = false)
    private LocalDateTime last_update;

    @Enumerated(EnumType.STRING)
    @Column(name = "can_see", nullable = false, length = 12)
    private ProjectPermissionsEnum can_see;

    @Enumerated(EnumType.STRING)
    @Column(name = "can_edit", nullable = false, length = 12)
    private ProjectPermissionsEnum can_edit;

    @Enumerated(EnumType.STRING)
    @Column(name = "can_download", nullable = false, length = 12)
    private ProjectPermissionsEnum can_download;

    @Column(name = "members_can_add_others", nullable = false)
    private Boolean members_can_add_others = false;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "project_members",
            joinColumns = @JoinColumn(name = "project_name"),
            inverseJoinColumns = @JoinColumn(name = "username"))
    private Set<UserEntity> members = new LinkedHashSet<>();

}