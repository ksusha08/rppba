package com.example.appKp6.repo;

import com.example.appKp6.entity.Category;
import com.example.appKp6.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Long> {
}
