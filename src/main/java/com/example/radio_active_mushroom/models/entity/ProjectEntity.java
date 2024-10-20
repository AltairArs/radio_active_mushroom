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
@Table(name = "projectEntity")
@IdClass(ProjectPrimaryKey.class)
public class ProjectEntity {
    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @Id
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "ownerUsername")
    private UserEntity owner;

    @Column(name = "friendlyName")
    private String friendlyName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "lastUpdate", nullable = false)
    private LocalDateTime lastUpdate;

    @Enumerated(EnumType.STRING)
    @Column(name = "canSee", nullable = false, length = 12)
    private ProjectPermissionsEnum canSee = ProjectPermissionsEnum.ONLY_OWNER;

    @Enumerated(EnumType.STRING)
    @Column(name = "canEdit", nullable = false, length = 12)
    private ProjectPermissionsEnum canEdit = ProjectPermissionsEnum.ONLY_OWNER;

    @Enumerated(EnumType.STRING)
    @Column(name = "canDownload", nullable = false, length = 12)
    private ProjectPermissionsEnum canDownload = ProjectPermissionsEnum.ONLY_OWNER;

    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "projectMembersEntity",
            joinColumns = {
                    @JoinColumn(name = "projectsAsMemberName", referencedColumnName = "name"),
                    @JoinColumn(name = "projectsAsMemberOwnerUsername", referencedColumnName = "ownerUsername")
            },
            inverseJoinColumns = {@JoinColumn(name = "memberUsername", referencedColumnName = "username")}
    )
    private Set<UserEntity> members = new LinkedHashSet<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MembershipRequestEntity> membershipRequests = new LinkedHashSet<>();

    public String getProjectName() {
        if (this.friendlyName != null && !this.friendlyName.isEmpty()) {
            return this.friendlyName;
        } else {
            return this.name;
        }
    }
}