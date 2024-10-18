package com.example.radio_active_mushroom.repo.entity;

import com.example.radio_active_mushroom.models.entity.MembershipRequestEntity;
import com.example.radio_active_mushroom.models.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRequestRepository extends JpaRepository<MembershipRequestEntity, UserEntity> {
}