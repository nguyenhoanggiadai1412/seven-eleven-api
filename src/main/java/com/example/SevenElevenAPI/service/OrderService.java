package com.example.SevenElevenAPI.service;

import com.example.SevenElevenAPI.dto.request.CreateOrderRequest;
import com.example.SevenElevenAPI.dto.response.OrderResponse;
import com.example.SevenElevenAPI.entity.OrderStatus;

import java.util.List;

public interface OrderService {

  OrderResponse createOrder(CreateOrderRequest request);

  List<OrderResponse> getAllOrders();

  OrderResponse getOrderById(Long id);

  OrderResponse updateStatus(Long id, OrderStatus status);
}
