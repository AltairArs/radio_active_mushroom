package com.example.radio_active_mushroom.repo;

import com.example.radio_active_mushroom.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}