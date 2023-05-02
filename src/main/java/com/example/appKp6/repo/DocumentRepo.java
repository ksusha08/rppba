package com.example.appKp6.repo;

import com.example.appKp6.entity.Document;
import com.example.appKp6.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface DocumentRepo extends JpaRepository<Document,Long> {

    List<Document> findByNumberContainingIgnoreCase(String number);

    List<Document> findByDateGreaterThanEqualAndDateLessThanEqual(Date startDate, Date endDate);
}
