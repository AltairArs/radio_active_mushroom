package com.example.radio_active_mushroom.repo.jpa;

import com.example.radio_active_mushroom.models.jpa.MembershipRequestEntity;
import com.example.radio_active_mushroom.models.jpa.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipRequestRepository extends JpaRepository<MembershipRequestEntity, UserEntity> {
}