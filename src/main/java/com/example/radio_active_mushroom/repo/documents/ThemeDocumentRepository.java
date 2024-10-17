package com.example.radio_active_mushroom.repo.documents;

import com.example.radio_active_mushroom.models.documents.ThemeDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Optional;

public interface ThemeDocumentRepository extends MongoRepository<ThemeDocument, String> {
    Optional<ThemeDocument> findByUsername(String username);
}