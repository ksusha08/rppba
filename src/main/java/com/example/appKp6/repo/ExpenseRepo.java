package com.example.appKp6.repo;

import com.example.appKp6.entity.Expense;
import com.example.appKp6.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepo  extends JpaRepository<Expense,Long> {
}
