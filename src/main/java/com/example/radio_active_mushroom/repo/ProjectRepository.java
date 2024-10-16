package com.example.radio_active_mushroom.repo;

import com.example.radio_active_mushroom.models.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<ProjectEntity, String> {
}