package com.example.radio_active_mushroom.repo;

import com.example.radio_active_mushroom.models.documents.FieldSetDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FieldSetDocumentRepository extends MongoRepository<FieldSetDocument, String> {
}