package com.example.radio_active_mushroom.repo.documents;

import com.example.radio_active_mushroom.models.documents.FieldDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldDocumentRepository extends MongoRepository<FieldDocument, String> {
}
