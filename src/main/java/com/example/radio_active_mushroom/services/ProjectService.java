package com.example.radio_active_mushroom.services;

import com.example.radio_active_mushroom.dto.entity.ProjectCreateDto;
import com.example.radio_active_mushroom.dto.entity.ProjectSettingsDto;
import com.example.radio_active_mushroom.models.entity.ProjectEntity;
import com.example.radio_active_mushroom.models.entity.UserEntity;

public interface ProjectService {
    public abstract boolean createNewProject(ProjectCreateDto projectCreateDto, UserEntity owner);
    public abstract ProjectEntity getProject(UserEntity owner, String name);
    public abstract void deleteProject(UserEntity owner, String projectName);
    public abstract ProjectSettingsDto getProjectSettings(UserEntity owner, String projectName);
    public abstract void updateProjectSettings(UserEntity owner, String projectName, ProjectSettingsDto projectSettingsDto);
}
