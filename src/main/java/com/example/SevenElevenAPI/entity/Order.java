package com.example.SevenElevenAPI.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String customerName;

  private String phone;

  private String address;

  @Column(columnDefinition = "TEXT")
  private String note;

  private Double totalAmount;

  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  private LocalDateTime createdAt;

  @OneToMany(
          mappedBy = "order",
          cascade = CascadeType.ALL,
          orphanRemoval = true
  )
  private List<OrderItem> orderItems = new ArrayList<>();

  @PrePersist
  public void prePersist() {
    this.createdAt = LocalDateTime.now();

    if (this.status == null) {
      this.status = OrderStatus.PENDING;
    }
  }
}
