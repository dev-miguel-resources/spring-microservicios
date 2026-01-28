package com.micros.restaurant.dto;

public record OrderItemResponseDTO(

        Long productId,

        String productName,

        int quantity,

        String description) {

}
