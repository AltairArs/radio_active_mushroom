package com.example.radio_active_mushroom.services.impl;

import com.example.radio_active_mushroom.dto.entity.ProjectCreateDto;
import com.example.radio_active_mushroom.dto.entity.ProjectSettingsDto;
import com.example.radio_active_mushroom.models.entity.ProjectEntity;
import com.example.radio_active_mushroom.models.entity.UserEntity;
import com.example.radio_active_mushroom.repo.entity.ProjectRepository;
import com.example.radio_active_mushroom.services.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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
        projectEntity.setCanDownload(projectSettingsDto.getCanDownload());
        projectEntity.setCanEdit(projectSettingsDto.getCanEdit());
        projectEntity.setCanSee(projectSettingsDto.getCanSee());
        projectRepository.save(projectEntity);
    }
}
