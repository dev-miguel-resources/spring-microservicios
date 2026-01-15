package com.micros.order.domain;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Order {

    private UUID id;
    private Customer customer;
    private Restaurant restaurant;
    private BigDecimal total;
    private OrderStatus status;

    // Factory for creation
    public static Order createNew(Customer customer, Restaurant restaurant, BigDecimal total) {
        return new Order(null, customer, restaurant, total, OrderStatus.CREATED);
    }

    public void changeStatus(OrderStatus newStatus) {
        this.status = Objects.requireNonNull(newStatus);
    }

}
