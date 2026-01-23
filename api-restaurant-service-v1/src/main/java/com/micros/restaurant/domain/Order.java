package com.micros.restaurant.domain;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Order {

    private UUID orderId;

    private Restaurant restaurant;

    private Customer customer;

    private List<OrderItem> items;

}
