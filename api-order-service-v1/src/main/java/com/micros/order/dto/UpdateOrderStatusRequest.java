package com.micros.order.dto;

import com.micros.order.domain.OrderStatus;

public record UpdateOrderStatusRequest(OrderStatus status) {

}
