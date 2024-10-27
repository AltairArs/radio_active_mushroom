package com.example.radio_active_mushroom.services;

import com.example.radio_active_mushroom.dto.entity.ProjectCreateDto;
import com.example.radio_active_mushroom.dto.entity.ProjectSettingsDto;
import com.example.radio_active_mushroom.enums.ProjectKindPermissionsEnum;
import com.example.radio_active_mushroom.enums.ProjectPermissionsEnum;
import com.example.radio_active_mushroom.models.entity.ProjectEntity;
import com.example.radio_active_mushroom.models.entity.UserEntity;

import java.util.Set;

public interface ProjectService {
    public abstract boolean createNewProject(ProjectCreateDto projectCreateDto, UserEntity owner);
    public abstract ProjectEntity getProject(UserEntity owner, String name);
    public abstract void deleteProject(UserEntity owner, String projectName);
    public abstract ProjectSettingsDto getProjectSettings(UserEntity owner, String projectName);
    public abstract void updateProjectSettings(UserEntity owner, String projectName, ProjectSettingsDto projectSettingsDto);
    public abstract boolean determineCanActionBeTaken(UserEntity owner, String projectName, UserEntity asker, ProjectKindPermissionsEnum action);
    public abstract Set<ProjectKindPermissionsEnum> getAskerPermissions(UserEntity owner, String projectName, UserEntity asker);
    public abstract boolean isEqualAskerMembership(UserEntity asker, ProjectEntity project, ProjectPermissionsEnum permission);
}
