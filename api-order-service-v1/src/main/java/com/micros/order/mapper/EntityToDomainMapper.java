package com.micros.order.mapper;

import com.micros.order.domain.Customer;
import com.micros.order.domain.Order;
import com.micros.order.domain.Restaurant;
import com.micros.order.infraestructure.entity.OrderEntity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EntityToDomainMapper {

    public static Order toDomain(OrderEntity entity) {

        return new Order(entity.getId(),
                Customer.of(entity.getCustomerId(), entity.getCustomerName()),
                Restaurant.of(entity.getRestaurantId(), entity.getRestaurantName()),
                entity.getTotal(),
                entity.getStatus());
    }

}
