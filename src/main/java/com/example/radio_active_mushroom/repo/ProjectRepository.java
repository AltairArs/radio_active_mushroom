package com.example.radio_active_mushroom.repo;

import com.example.radio_active_mushroom.models.ProjectEntity;
import com.example.radio_active_mushroom.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<ProjectEntity, String> {
    Optional<ProjectEntity> findByOwnerAndName(UserEntity owner, String name);
}