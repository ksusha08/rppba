package com.example.appKp6.repo;

import com.example.appKp6.entity.Document;
import com.example.appKp6.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepo extends JpaRepository<Document,Long> {
}
