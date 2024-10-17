package com.example.radio_active_mushroom.repo.jpa;

import com.example.radio_active_mushroom.models.jpa.ProjectEntity;
import com.example.radio_active_mushroom.models.jpa.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<ProjectEntity, String> {
    Optional<ProjectEntity> findByOwnerAndName(UserEntity owner, String name);
}