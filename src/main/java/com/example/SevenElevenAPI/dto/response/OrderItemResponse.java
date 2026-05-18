package com.example.SevenElevenAPI.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderItemResponse {

  private Long id;

  private Long productId;

  private String productName;

  private Integer quantity;

  private Double price;

  private Double subtotal;
}