package com.example.radio_active_mushroom.repo.documents;

import com.example.radio_active_mushroom.models.documents.FieldSetDocument;
import com.example.radio_active_mushroom.models.embeddable.FieldSetId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface FieldSetDocumentRepository extends MongoRepository<FieldSetDocument, String> {
    Optional<FieldSetDocument> findByField_set_nameAndProject_owner_usernameAndProject_name(String fieldSetName, String project_owner_username, String project_name);
    List<FieldSetDocument> findByProject_nameAndProject_owner_username(String project_name, String project_owner_username);
}