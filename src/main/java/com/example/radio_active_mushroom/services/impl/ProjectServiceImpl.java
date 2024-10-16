package com.example.radio_active_mushroom.services.impl;

import com.example.radio_active_mushroom.dto.ProjectCreateDto;
import com.example.radio_active_mushroom.models.ProjectEntity;
import com.example.radio_active_mushroom.models.UserEntity;
import com.example.radio_active_mushroom.repo.ProjectRepository;
import com.example.radio_active_mushroom.services.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
