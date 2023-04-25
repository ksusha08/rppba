package com.example.appKp6.repo;

import com.example.appKp6.entity.Income;
import com.example.appKp6.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncomeRepo extends JpaRepository<Income,Long> {

    List<Income> findByDocumentId(Long documentId);
}
