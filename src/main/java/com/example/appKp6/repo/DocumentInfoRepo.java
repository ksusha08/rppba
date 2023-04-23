package com.example.appKp6.repo;

import com.example.appKp6.entity.Document;
import com.example.appKp6.entity.DocumentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentInfoRepo extends JpaRepository<DocumentInfo,Long> {
    List<DocumentInfo> findByDocumentId(Long documentId);
}
