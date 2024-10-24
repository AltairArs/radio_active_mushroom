package com.example.radio_active_mushroom.repo.documents;

import com.example.radio_active_mushroom.models.documents.FieldSetDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldSetDocumentRepository extends MongoRepository<FieldSetDocument, String> {
    List<FieldSetDocument> findAllByProjectNameAndProjectOwnerUsername(String projectName, String projectOwnerUsername);
    boolean existsByProjectNameAndProjectOwnerUsernameAndName(String projectName, String projectOwnerUsername, String name);
    FieldSetDocument getById(String id);
}
