package com.micros.order.dto;

import java.util.UUID;

import com.micros.order.domain.OrderStatus;

public record OrderResponse(UUID id, OrderStatus status) {

}
