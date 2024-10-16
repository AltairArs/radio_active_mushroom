package com.example.radio_active_mushroom.repo;

import com.example.radio_active_mushroom.models.documents.FieldSetDocument;
import com.example.radio_active_mushroom.models.embeddable.FieldSetId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FieldSetDocumentRepository extends MongoRepository<FieldSetDocument, String> {
    Optional<FieldSetDocument> findByField_set_id(FieldSetId fieldSetId);
}