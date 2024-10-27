package com.example.radio_active_mushroom.services;

import com.example.radio_active_mushroom.dto.entity.EditMembershipRequestDto;
import com.example.radio_active_mushroom.dto.entity.SendMembershipRequestDto;
import com.example.radio_active_mushroom.models.entity.MembershipRequestEntity;
import com.example.radio_active_mushroom.models.entity.UserEntity;

public interface MembershipRequestsService {
    public abstract boolean createMembershipRequest(SendMembershipRequestDto sendMembershipRequestDto, UserEntity sender);
    public abstract MembershipRequestEntity getMembershipRequest(String projectOwnerUsername, String projectName, String senderUsername);
    public abstract void acceptMembershipRequest(MembershipRequestEntity membershipRequestEntity);
    public abstract void rejectMembershipRequest(MembershipRequestEntity membershipRequestEntity);
    public abstract void deleteMembershipRequest(MembershipRequestEntity membershipRequestEntity);
    public abstract EditMembershipRequestDto getEditMembershipRequest(MembershipRequestEntity membershipRequest);
    public abstract void saveMembershipRequest(EditMembershipRequestDto editMembershipRequestDto, MembershipRequestEntity membershipRequest);
}
