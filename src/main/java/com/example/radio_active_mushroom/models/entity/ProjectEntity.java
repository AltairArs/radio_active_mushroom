package com.example.radio_active_mushroom.models.entity;

import com.example.radio_active_mushroom.enums.ProjectPermissionsEnum;
import com.example.radio_active_mushroom.models.primary_keys.ProjectPrimaryKey;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "project_entity")
@IdClass(ProjectPrimaryKey.class)
public class ProjectEntity {
    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @Id
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "owner_username")
    private UserEntity owner;

    @Column(name = "friendly_name")
    private String friendly_name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at = LocalDateTime.now();

    @Column(name = "last_update", nullable = false)
    private LocalDateTime last_update;

    @Enumerated(EnumType.STRING)
    @Column(name = "can_see", nullable = false, length = 12)
    private ProjectPermissionsEnum can_see = ProjectPermissionsEnum.ONLY_OWNER;

    @Enumerated(EnumType.STRING)
    @Column(name = "can_edit", nullable = false, length = 12)
    private ProjectPermissionsEnum can_edit = ProjectPermissionsEnum.ONLY_OWNER;

    @Enumerated(EnumType.STRING)
    @Column(name = "can_download", nullable = false, length = 12)
    private ProjectPermissionsEnum can_download = ProjectPermissionsEnum.ONLY_OWNER;

    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "project_members",
            joinColumns = {
                    @JoinColumn(name = "projects_as_member_name", referencedColumnName = "name"),
                    @JoinColumn(name = "projects_as_member_owner_username", referencedColumnName = "owner_username")
            },
            inverseJoinColumns = {@JoinColumn(name = "member_username", referencedColumnName = "username")}
    )
    private Set<UserEntity> members = new LinkedHashSet<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MembershipRequestEntity> membership_requests = new LinkedHashSet<>();

    public String getProjectName() {
        if (this.friendly_name != null && !this.friendly_name.isEmpty()) {
            return this.friendly_name;
        } else {
            return this.name;
        }
    }
}