package com.micros.order.dto;

import java.math.BigDecimal;

public record CreateOrderRequest(CustomerRequest customer, RestaurantRequest restaurant, BigDecimal total) {

}
