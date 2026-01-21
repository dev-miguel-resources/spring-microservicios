package com.micros.order.mapper;

import com.micros.order.domain.Customer;
import com.micros.order.domain.Order;
import com.micros.order.domain.Restaurant;
import com.micros.order.dto.CreateOrderRequest;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RequestToDomainMapper {

    public static Order toDomain(CreateOrderRequest req) {

        return Order.createNew(Customer.of(req.customer().id(), req.customer().name()),
                Restaurant.of(req.restaurant().id(), req.restaurant().name()),
                req.total());
    }

}
