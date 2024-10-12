package com.example.radio_active_mushroom.repo;

import com.example.radio_active_mushroom.models.UserEntity;
import com.example.radio_active_mushroom.models.UserVerificationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserVerificationTokenRepository extends JpaRepository<UserVerificationTokenEntity, UserEntity> {
}