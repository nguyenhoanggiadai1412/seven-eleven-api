package com.example.SevenElevenAPI.Controller;

import com.example.SevenElevenAPI.dto.request.CreateOrderRequest;
import com.example.SevenElevenAPI.dto.request.UpdateOrderStatusRequest;
import com.example.SevenElevenAPI.dto.response.OrderResponse;
import com.example.SevenElevenAPI.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderController {

  private final OrderService orderService;

  @PostMapping("/orders")
  public OrderResponse createOrder(
          @Valid @RequestBody CreateOrderRequest request
  ) {
    return orderService.createOrder(request);
  }

  @GetMapping("/admin/orders")
  public List<OrderResponse> getAllOrders() {
    return orderService.getAllOrders();
  }

  @GetMapping("/admin/orders/{id}")
  public OrderResponse getOrderById(
          @PathVariable Long id
  ) {
    return orderService.getOrderById(id);
  }

  @PutMapping("/admin/orders/{id}/status")
  public OrderResponse updateStatus(
          @PathVariable Long id,
          @Valid @RequestBody UpdateOrderStatusRequest request
  ) {
    return orderService.updateStatus(id, request.getStatus());
  }
}