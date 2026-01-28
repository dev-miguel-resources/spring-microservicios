package com.micros.restaurant.dto;

public record OrderItemRequestDTO(

        Long productId,

        String productName,

        int quantity,

        String description) {

}
