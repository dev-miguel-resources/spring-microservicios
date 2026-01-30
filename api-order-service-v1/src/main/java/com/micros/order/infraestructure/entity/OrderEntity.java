package com.micros.order.infraestructure.entity;

import java.math.BigDecimal;
import java.util.UUID;

import com.micros.order.domain.OrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    private UUID id;

    // Cliente

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    // Restaurant
    @Column(name = "restaurant_id", nullable = false)
    private Long restaurantId;

    @Column(name = "restaurant_name", nullable = false)
    private String restaurantName;

    // OrderStatus
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    private BigDecimal total;

}
