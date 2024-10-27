package com.example.radio_active_mushroom.services.impl;

import com.example.radio_active_mushroom.dto.entity.EditMembershipRequestDto;
import com.example.radio_active_mushroom.dto.entity.SendMembershipRequestDto;
import com.example.radio_active_mushroom.models.entity.MembershipRequestEntity;
import com.example.radio_active_mushroom.models.entity.ProjectEntity;
import com.example.radio_active_mushroom.models.entity.UserEntity;
import com.example.radio_active_mushroom.repo.entity.MembershipRequestRepository;
import com.example.radio_active_mushroom.repo.entity.ProjectRepository;
import com.example.radio_active_mushroom.repo.entity.UserRepository;
import com.example.radio_active_mushroom.services.MembershipRequestsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MembershipRequestsServiceImpl implements MembershipRequestsService {

    @Autowired
    private MembershipRequestRepository membershipRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public boolean createMembershipRequest(SendMembershipRequestDto sendMembershipRequestDto, UserEntity sender) {
        Optional<UserEntity> owner = userRepository.findByUsername(sendMembershipRequestDto.getUsername());
        if (owner.isPresent()) {
            Optional<ProjectEntity> project = projectRepository.findByOwnerAndName(owner.get(), sendMembershipRequestDto.getProjectName());
            if (project.isPresent()) {
                if (!membershipRequestRepository.existsBySenderAndProject(sender, project.get()) && !project.get().getMembers().contains(sender) && owner.get() != sender) {
                    MembershipRequestEntity membershipRequestEntity = new MembershipRequestEntity();
                    membershipRequestEntity.setSender(sender);
                    membershipRequestEntity.setProject(project.get());
                    membershipRequestEntity.setMessage(sendMembershipRequestDto.getMessage());
                    membershipRequestRepository.save(membershipRequestEntity);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public MembershipRequestEntity getMembershipRequest(String projectOwnerUsername, String projectName, String senderUsername) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(projectOwnerUsername);
        Optional<UserEntity> sender = userRepository.findByUsername(senderUsername);
        if (optionalUserEntity.isPresent() && sender.isPresent()) {
            Optional<ProjectEntity> optionalProject = projectRepository.findByOwnerAndName(optionalUserEntity.get(), projectName);
            if (optionalProject.isPresent()) {
                Optional<MembershipRequestEntity> membershipRequestEntity = membershipRequestRepository.findBySenderAndProject(sender.get(), optionalProject.get());
                if (membershipRequestEntity.isPresent()) {
                    return membershipRequestEntity.get();
                }
            }
        }
        return null;
    }

    @Override
    public void acceptMembershipRequest(MembershipRequestEntity membershipRequestEntity) {
        if (membershipRequestEntity != null){
            ProjectEntity project = membershipRequestEntity.getProject();
            project.getMembers().add(membershipRequestEntity.getSender());
            projectRepository.save(project);
            membershipRequestRepository.delete(membershipRequestEntity);
        }
    }

    @Override
    public void rejectMembershipRequest(MembershipRequestEntity membershipRequestEntity) {
        deleteMembershipRequest(membershipRequestEntity);
    }

    @Override
    public void deleteMembershipRequest(MembershipRequestEntity membershipRequestEntity) {
        if (membershipRequestEntity != null){
            membershipRequestRepository.delete(membershipRequestEntity);
        }
    }

    @Override
    public EditMembershipRequestDto getEditMembershipRequest(MembershipRequestEntity membershipRequest) {
        return modelMapper.map(membershipRequest, EditMembershipRequestDto.class);
    }

    @Override
    public void saveMembershipRequest(EditMembershipRequestDto editMembershipRequestDto, MembershipRequestEntity membershipRequest) {
        membershipRequest.setMessage(editMembershipRequestDto.getMessage());
        membershipRequestRepository.save(membershipRequest);
    }
}
