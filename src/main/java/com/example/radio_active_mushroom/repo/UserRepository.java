package com.example.radio_active_mushroom.repo;

import com.example.radio_active_mushroom.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByVerification_token(String verification_token);
}