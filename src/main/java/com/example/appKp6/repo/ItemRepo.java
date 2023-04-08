package com.example.appKp6.repo;

import com.example.appKp6.entity.Item;
import com.example.appKp6.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo  extends JpaRepository<Item,Long> {
}
