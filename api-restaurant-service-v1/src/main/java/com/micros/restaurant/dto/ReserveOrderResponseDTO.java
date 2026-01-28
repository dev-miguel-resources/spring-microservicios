package com.micros.restaurant.dto;

import java.util.List;
import java.util.UUID;

public record ReserveOrderResponseDTO(

        UUID orderId,

        Long restaurantId,

        Long customerId,

        List<OrderItemResponseDTO> items) {

}
