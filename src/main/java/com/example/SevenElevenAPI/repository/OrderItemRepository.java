package com.example.SevenElevenAPI.repository;

import com.example.SevenElevenAPI.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}