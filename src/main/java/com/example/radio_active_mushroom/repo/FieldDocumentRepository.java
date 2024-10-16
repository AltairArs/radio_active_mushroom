package com.example.radio_active_mushroom.repo;

import com.example.radio_active_mushroom.models.documents.FieldDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FieldDocumentRepository extends MongoRepository<FieldDocument, String> {
}