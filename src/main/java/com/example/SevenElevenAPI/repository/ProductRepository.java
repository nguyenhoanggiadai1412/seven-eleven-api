package com.example.SevenElevenAPI.repository;

import com.example.SevenElevenAPI.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository
        extends JpaRepository<Product, Long> {
}
