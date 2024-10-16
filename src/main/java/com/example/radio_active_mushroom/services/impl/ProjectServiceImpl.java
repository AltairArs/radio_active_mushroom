package com.example.radio_active_mushroom.services.impl;

import com.example.radio_active_mushroom.dto.ProjectCreateDto;
import com.example.radio_active_mushroom.dto.ProjectSettingsDto;
import com.example.radio_active_mushroom.models.ProjectEntity;
import com.example.radio_active_mushroom.models.UserEntity;
import com.example.radio_active_mushroom.repo.ProjectRepository;
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
            newProject.setLast_update(newProject.getCreated_at());
            projectRepository.save(newProject);
            return true;
        }
    }

    @Override
    public ProjectEntity getProject(UserEntity owner, String name) {
        return projectRepository.findByOwnerAndName(owner, name).get();
    }

    @Override
    public void deleteProject(UserEntity owner, String project_name) {
        Optional<ProjectEntity> projectEntity = projectRepository.findByOwnerAndName(owner, project_name);
        if (projectEntity.isPresent()) {
            projectRepository.delete(projectEntity.get());
        }
    }

    @Override
    public ProjectSettingsDto getProjectSettings(UserEntity owner, String project_name) {
        return modelMapper.map(projectRepository.findByOwnerAndName(owner, project_name).get(), ProjectSettingsDto.class);
    }

    @Override
    public void updateProjectSettings(UserEntity owner, String project_name, ProjectSettingsDto projectSettingsDto) {
        ProjectEntity projectEntity = projectRepository.findByOwnerAndName(owner, project_name).get();
        projectEntity.setLast_update(LocalDateTime.now());
        projectEntity.setDescription(projectSettingsDto.getDescription());
        projectEntity.setFriendly_name(projectSettingsDto.getFriendly_name());
        projectEntity.setCan_download(projectSettingsDto.getCan_download());
        projectEntity.setCan_edit(projectSettingsDto.getCan_edit());
        projectEntity.setCan_see(projectSettingsDto.getCan_see());
        projectRepository.save(projectEntity);
    }
}
