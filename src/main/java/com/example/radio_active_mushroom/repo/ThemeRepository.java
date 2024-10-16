package com.example.radio_active_mushroom.repo;

import com.example.radio_active_mushroom.models.documents.ThemeDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ThemeRepository extends MongoRepository<ThemeDocument, String> {
    Optional<ThemeDocument> findByUsername(String username);
}