package com.example.radio_active_mushroom.repo;

import com.example.radio_active_mushroom.models.documents.TableDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TableDocumentRepository extends MongoRepository<TableDocument, String> {
}