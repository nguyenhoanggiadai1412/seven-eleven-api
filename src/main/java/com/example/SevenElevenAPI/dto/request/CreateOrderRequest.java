package com.example.SevenElevenAPI.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateOrderRequest {

  @NotBlank
  private String customerName;

  // đổi customerPhone -> phone
  @NotBlank
  private String phone;

  // đổi customerAddress -> address
  @NotBlank
  private String address;

  // thêm note
  private String note;

  @NotEmpty
  @Valid
  private List<CreateOrderItemRequest> items;
}
