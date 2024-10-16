package com.example.radio_active_mushroom.repo;

import com.example.radio_active_mushroom.models.documents.FieldDocument;
import com.example.radio_active_mushroom.models.embeddable.FieldSetId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FieldDocumentRepository extends MongoRepository<FieldDocument, String> {
    List<FieldDocument> findAllByField_set_id(FieldSetId field_set_id);
}