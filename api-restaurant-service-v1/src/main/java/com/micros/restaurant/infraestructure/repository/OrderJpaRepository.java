package com.micros.restaurant.infraestructure.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micros.restaurant.infraestructure.entity.OrderEntity;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, UUID> {

}
