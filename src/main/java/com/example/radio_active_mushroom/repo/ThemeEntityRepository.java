package com.example.radio_active_mushroom.repo;

import com.example.radio_active_mushroom.models.ThemeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ThemeEntityRepository extends MongoRepository<ThemeEntity, String> {
}