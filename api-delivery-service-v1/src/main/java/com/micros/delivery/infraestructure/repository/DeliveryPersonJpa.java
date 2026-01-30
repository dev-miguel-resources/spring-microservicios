package com.micros.delivery.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micros.delivery.infraestructure.entity.DeliveryPersonEntity;

public interface DeliveryPersonJpa extends JpaRepository<DeliveryPersonEntity, Long> {

}
