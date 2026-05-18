package com.example.SevenElevenAPI.dto.response;

import com.example.SevenElevenAPI.entity.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class OrderResponse {

  private Long id;

  private String customerName;

  private String phone;

  private String address;

  private String note;

  private Double totalAmount;

  private OrderStatus status;

  private LocalDateTime createdAt;

  private List<OrderItemResponse> items;
}
