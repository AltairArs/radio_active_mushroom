package com.example.radio_active_mushroom.repo.entity;

import com.example.radio_active_mushroom.models.entity.MembershipRequestEntity;
import com.example.radio_active_mushroom.models.entity.ProjectEntity;
import com.example.radio_active_mushroom.models.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembershipRequestRepository extends JpaRepository<MembershipRequestEntity, UserEntity> {
    boolean existsBySenderAndProject(UserEntity sender, ProjectEntity project);
    Optional<MembershipRequestEntity> findBySenderAndProject(UserEntity sender, ProjectEntity project);
}