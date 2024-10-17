package com.example.radio_active_mushroom.repo.documents;

import com.example.radio_active_mushroom.models.documents.TableDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TableDocumentRepository extends MongoRepository<TableDocument, String> {
    List<TableDocument> findByProject_nameAndProject_owner_username(String project_name, String owner_username);
    Optional<TableDocument> findByProject_nameAndProject_ownerAndField_set_name(String username, String project_name, String field_set_name);
}