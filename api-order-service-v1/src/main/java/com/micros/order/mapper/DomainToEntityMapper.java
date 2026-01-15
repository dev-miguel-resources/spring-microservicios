package com.micros.order.mapper;

import com.micros.order.domain.Order;
import com.micros.order.infraestructure.entity.OrderEntity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DomainToEntityMapper {

    public static OrderEntity toEntity(Order domain) {

        OrderEntity entity = new OrderEntity();

        entity.setId(domain.getId());
        entity.setCustomerId(domain.getCustomer().getId());
        entity.setCustomerName(domain.getCustomer().getName());
        entity.setRestaurantId(domain.getRestaurant().getId());
        entity.setRestaurantName(domain.getRestaurant().getName());
        entity.setTotal(domain.getTotal());
        entity.setStatus(domain.getStatus());
        return entity;
    }

}
