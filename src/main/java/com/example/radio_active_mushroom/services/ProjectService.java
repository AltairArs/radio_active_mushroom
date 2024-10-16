package com.example.radio_active_mushroom.services;

import com.example.radio_active_mushroom.dto.ProjectCreateDto;
import com.example.radio_active_mushroom.models.ProjectEntity;
import com.example.radio_active_mushroom.models.UserEntity;

public interface ProjectService {
    public abstract boolean createNewProject(ProjectCreateDto projectCreateDto, UserEntity owner);
    public abstract ProjectEntity getProject(UserEntity owner, String name);
    public abstract void deleteProject(UserEntity owner, String project_name);
}
