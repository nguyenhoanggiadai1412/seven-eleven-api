package com.example.SevenElevenAPI.repository;

import com.example.SevenElevenAPI.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
