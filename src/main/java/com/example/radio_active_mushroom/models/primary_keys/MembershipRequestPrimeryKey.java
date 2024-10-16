package com.example.radio_active_mushroom.models.primary_keys;

import com.example.radio_active_mushroom.models.jpa.ProjectEntity;
import com.example.radio_active_mushroom.models.jpa.UserEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MembershipRequestPrimeryKey {
    @ManyToOne
    @JoinColumn(name = "sender_username")
    private UserEntity sender;

    @ManyToOne
    @JoinColumn(name = "project_name", nullable = false, referencedColumnName = "name")
    @JoinColumn(name = "project_owner_username", referencedColumnName = "owner_username", nullable = false)
    private ProjectEntity project;
}
