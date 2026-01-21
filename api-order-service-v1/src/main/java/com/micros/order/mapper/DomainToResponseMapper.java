package com.micros.order.mapper;

import com.micros.order.domain.Order;
import com.micros.order.dto.OrderResponse;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DomainToResponseMapper {

    public static OrderResponse toResponse(Order domain) {

        return new OrderResponse(
                domain.getId(),
                domain.getStatus());

    }

}
