package com.example.SevenElevenAPI.service.impl;

import com.example.SevenElevenAPI.dto.request.CreateOrderRequest;
import com.example.SevenElevenAPI.dto.response.OrderItemResponse;
import com.example.SevenElevenAPI.dto.response.OrderResponse;
import com.example.SevenElevenAPI.entity.Order;
import com.example.SevenElevenAPI.entity.OrderItem;
import com.example.SevenElevenAPI.entity.OrderStatus;
import com.example.SevenElevenAPI.entity.Product;
import com.example.SevenElevenAPI.repository.OrderRepository;
import com.example.SevenElevenAPI.repository.ProductRepository;
import com.example.SevenElevenAPI.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;

  @Override
  public OrderResponse createOrder(CreateOrderRequest request) {

    Order order = Order.builder()
            .customerName(request.getCustomerName())
            .customerPhone(request.getCustomerPhone())
            .customerAddress(request.getCustomerAddress())
            .status(OrderStatus.PENDING)
            .orderItems(new ArrayList<>())
            .build();

    double totalAmount = 0;

    for (var itemRequest : request.getItems()) {
      Product product = productRepository.findById(itemRequest.getProductId())
              .orElseThrow(() -> new RuntimeException("Product not found"));

      if (product.getStock() < itemRequest.getQuantity()) {
        throw new RuntimeException("Product out of stock: " + product.getName());
      }

      double price = product.getPrice();
      double subtotal = price * itemRequest.getQuantity();

      OrderItem orderItem = OrderItem.builder()
              .product(product)
              .order(order)
              .quantity(itemRequest.getQuantity())
              .price(price)
              .subtotal(subtotal)
              .build();

      order.getOrderItems().add(orderItem);

      product.setStock(product.getStock() - itemRequest.getQuantity());
      totalAmount += subtotal;
    }

    order.setTotalAmount(totalAmount);

    Order savedOrder = orderRepository.save(order);

    return mapToResponse(savedOrder);
  }

  @Override
  public List<OrderResponse> getAllOrders() {
    return orderRepository.findAll()
            .stream()
            .map(this::mapToResponse)
            .toList();
  }

  @Override
  public OrderResponse getOrderById(Long id) {
    Order order = orderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found"));

    return mapToResponse(order);
  }

  @Override
  public OrderResponse updateStatus(Long id, OrderStatus status) {
    Order order = orderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found"));

    order.setStatus(status);

    return mapToResponse(orderRepository.save(order));
  }

  private OrderResponse mapToResponse(Order order) {
    List<OrderItemResponse> items = order.getOrderItems()
            .stream()
            .map(item -> OrderItemResponse.builder()
                    .id(item.getId())
                    .productId(item.getProduct().getId())
                    .productName(item.getProduct().getName())
                    .quantity(item.getQuantity())
                    .price(item.getPrice())
                    .subtotal(item.getSubtotal())
                    .build())
            .toList();

    return OrderResponse.builder()
            .id(order.getId())
            .customerName(order.getCustomerName())
            .customerPhone(order.getCustomerPhone())
            .customerAddress(order.getCustomerAddress())
            .totalAmount(order.getTotalAmount())
            .status(order.getStatus())
            .createdAt(order.getCreatedAt())
            .items(items)
            .build();
  }
}