package com.example.radio_active_mushroom.repo;

import com.example.radio_active_mushroom.models.ThemeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ThemeRepository extends MongoRepository<ThemeEntity, String> {
    Optional<ThemeEntity> findByUsername(String username);
}