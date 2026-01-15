package com.micros.order.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.micros.order.domain.Order;
import com.micros.order.infraestructure.entity.OrderEntity;
import com.micros.order.infraestructure.repository.OrderRepositoryJPA;
import com.micros.order.mapper.DomainToEntityMapper;
import com.micros.order.mapper.EntityToDomainMapper;

@Repository
public class OrderRepository {

    private final OrderRepositoryJPA orderRepository;

    public OrderRepository(OrderRepositoryJPA orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Optional<Order> findById(UUID id) {
        return orderRepository.findById(id).map(EntityToDomainMapper::toDomain);
    }

    public Order save(Order order) {
        OrderEntity entity = DomainToEntityMapper.toEntity(order);
        entity = orderRepository.save(entity);
        return EntityToDomainMapper.toDomain(entity);
    }

}
