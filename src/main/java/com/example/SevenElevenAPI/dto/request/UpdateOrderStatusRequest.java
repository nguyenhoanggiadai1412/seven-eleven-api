package com.example.SevenElevenAPI.dto.request;

import com.example.SevenElevenAPI.entity.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderStatusRequest {

  @NotNull
  private OrderStatus status;
}