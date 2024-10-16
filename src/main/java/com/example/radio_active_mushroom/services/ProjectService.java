package com.example.radio_active_mushroom.services;

import com.example.radio_active_mushroom.dto.ProjectCreateDto;
import com.example.radio_active_mushroom.dto.ProjectSettingsDto;
import com.example.radio_active_mushroom.models.jpa.ProjectEntity;
import com.example.radio_active_mushroom.models.jpa.UserEntity;

public interface ProjectService {
    public abstract boolean createNewProject(ProjectCreateDto projectCreateDto, UserEntity owner);
    public abstract ProjectEntity getProject(UserEntity owner, String name);
    public abstract void deleteProject(UserEntity owner, String project_name);
    public abstract ProjectSettingsDto getProjectSettings(UserEntity owner, String project_name);
    public abstract void updateProjectSettings(UserEntity owner, String project_name, ProjectSettingsDto projectSettingsDto);
}
