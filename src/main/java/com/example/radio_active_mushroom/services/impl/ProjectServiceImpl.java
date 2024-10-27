package com.example.radio_active_mushroom.services.impl;

import com.example.radio_active_mushroom.dto.entity.ProjectCreateDto;
import com.example.radio_active_mushroom.dto.entity.ProjectSettingsDto;
import com.example.radio_active_mushroom.enums.ProjectKindPermissionsEnum;
import com.example.radio_active_mushroom.enums.ProjectPermissionsEnum;
import com.example.radio_active_mushroom.models.entity.ProjectEntity;
import com.example.radio_active_mushroom.models.entity.UserEntity;
import com.example.radio_active_mushroom.repo.entity.ProjectRepository;
import com.example.radio_active_mushroom.services.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public boolean createNewProject(ProjectCreateDto projectCreateDto, UserEntity owner) {
        Optional<ProjectEntity> projectEntity = projectRepository.findByOwnerAndName(owner, projectCreateDto.getName());
        if (projectEntity.isPresent()) {
            return false;
        } else {
            ProjectEntity newProject = modelMapper.map(projectCreateDto, ProjectEntity.class);
            newProject.setOwner(owner);
            newProject.setLastUpdate(newProject.getCreatedAt());
            projectRepository.save(newProject);
            return true;
        }
    }

    @Override
    public ProjectEntity getProject(UserEntity owner, String name) {
        return projectRepository.findByOwnerAndName(owner, name).get();
    }

    @Override
    public void deleteProject(UserEntity owner, String projectName) {
        Optional<ProjectEntity> projectEntity = projectRepository.findByOwnerAndName(owner, projectName);
        projectEntity.ifPresent(entity -> projectRepository.delete(entity));
    }

    @Override
    public ProjectSettingsDto getProjectSettings(UserEntity owner, String projectName) {
        ProjectEntity projectEntity = projectRepository.findByOwnerAndName(owner, projectName).get();
        return modelMapper.map(projectRepository.findByOwnerAndName(owner, projectName).get(), ProjectSettingsDto.class);
    }

    @Override
    public void updateProjectSettings(UserEntity owner, String projectName, ProjectSettingsDto projectSettingsDto) {
        ProjectEntity projectEntity = projectRepository.findByOwnerAndName(owner, projectName).get();
        projectEntity.setLastUpdate(LocalDateTime.now());
        projectEntity.setDescription(projectSettingsDto.getDescription());
        projectEntity.setFriendlyName(projectSettingsDto.getFriendlyName());
        projectEntity.setCanConvert(projectSettingsDto.getCanConvert());
        projectEntity.setCanExport(projectSettingsDto.getCanExport());
        projectEntity.setCanGenerate(projectSettingsDto.getCanGenerate());
        projectEntity.setCanSeeWorkspace(projectSettingsDto.getCanSeeWorkspace());
        projectEntity.setCanEditWorkspace(projectSettingsDto.getCanEditWorkspace());
        projectEntity.setCanSee(projectSettingsDto.getCanSee());
        projectRepository.save(projectEntity);
    }

    @Override
    public boolean determineCanActionBeTaken(UserEntity owner, String projectName, UserEntity asker, ProjectKindPermissionsEnum action) {
        return getAskerPermissions(owner, projectName, asker).contains(action);
    }

    @Override
    public Set<ProjectKindPermissionsEnum> getAskerPermissions(UserEntity owner, String projectName, UserEntity asker) {
        Set<ProjectKindPermissionsEnum> permissions = new HashSet<ProjectKindPermissionsEnum>();
        Optional<ProjectEntity> optionalProjectEntity = projectRepository.findByOwnerAndName(owner, projectName);
        if (optionalProjectEntity.isPresent() && owner != null) {
            ProjectEntity projectEntity = optionalProjectEntity.get();
            if (isEqualAskerMembership(asker, projectEntity, projectEntity.getCanSee())){
                permissions.add(ProjectKindPermissionsEnum.CAN_SEE);
                if (isEqualAskerMembership(asker, projectEntity, projectEntity.getCanSeeWorkspace())){
                    permissions.add(ProjectKindPermissionsEnum.CAN_SEE_WORKSPACE);
                    if (isEqualAskerMembership(asker, projectEntity, projectEntity.getCanGenerate())){
                        permissions.add(ProjectKindPermissionsEnum.CAN_GENERATE);
                    }
                    if (isEqualAskerMembership(asker, projectEntity, projectEntity.getCanConvert())){
                        permissions.add(ProjectKindPermissionsEnum.CAN_CONVERT);
                    }
                    if (isEqualAskerMembership(asker, projectEntity, projectEntity.getCanExport())){
                        permissions.add(ProjectKindPermissionsEnum.CAN_EXPORT);
                    }
                    if (isEqualAskerMembership(asker, projectEntity, projectEntity.getCanEditWorkspace())){
                        permissions.add(ProjectKindPermissionsEnum.CAN_EDIT_WORKSPACE);
                    }
                }
            }
        }
        return permissions;
    }

    @Override
    public boolean isEqualAskerMembership(UserEntity asker, ProjectEntity project, ProjectPermissionsEnum permission) {
        return switch (permission){
            case ALL -> true;
            case ONLY_MEMBERS -> project.getMembers().contains(asker) || project.getOwner().equals(asker);
            case ONLY_OWNER -> project.getOwner().equals(asker);
        };
    }
}
