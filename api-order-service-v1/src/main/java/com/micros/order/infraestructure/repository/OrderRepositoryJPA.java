package com.micros.order.infraestructure.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.micros.order.infraestructure.entity.OrderEntity;

@Repository
public interface OrderRepositoryJPA extends JpaRepository<OrderEntity, UUID> {

}
