package com.micros.restaurant.dto;

import java.util.List;
import java.util.UUID;

public record ReserveOrderRequestDTO(

        UUID orderId,

        Long restaurantId,

        Long customerId,

        List<OrderItemRequestDTO> items) {

}
