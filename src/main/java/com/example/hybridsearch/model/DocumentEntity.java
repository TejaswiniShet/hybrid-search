package com.example.hybridsearch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "documents")
public class DocumentEntity {

    @Id
    private String id;
    private String text;

    public String getText() {
        return text;
    }
}
