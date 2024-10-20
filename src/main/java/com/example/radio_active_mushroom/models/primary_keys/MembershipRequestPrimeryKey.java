package com.example.radio_active_mushroom.models.primary_keys;

import com.example.radio_active_mushroom.models.entity.ProjectEntity;
import com.example.radio_active_mushroom.models.entity.UserEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MembershipRequestPrimeryKey {
    @ManyToOne
    @JoinColumn(name = "senderUsername")
    private UserEntity sender;

    @ManyToOne
    @JoinColumn(name = "projectName", nullable = false, referencedColumnName = "name")
    @JoinColumn(name = "projectOwnerUsername", referencedColumnName = "ownerUsername", nullable = false)
    private ProjectEntity project;
}
