package com.example.radio_active_mushroom.repo.entity;

import com.example.radio_active_mushroom.models.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    @Query("select u from UserEntity u where u.verification_token = ?1")
    Optional<UserEntity> findByVerification_token(String verification_token);

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);
}