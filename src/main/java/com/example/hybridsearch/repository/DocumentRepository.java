package com.example.hybridsearch.repository;

import com.example.hybridsearch.model.DocumentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DocumentRepository
        extends MongoRepository<DocumentEntity, String> {
}
