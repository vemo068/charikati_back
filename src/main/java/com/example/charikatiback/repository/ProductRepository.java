package com.example.charikatiback.repository;

import com.example.charikatiback.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
