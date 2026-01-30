package com.micros.delivery.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.micros.delivery.domain.Delivery;
import com.micros.delivery.infraestructure.repository.DeliveryRepositoryJpa;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class DeliveryRepository {

    private final DeliveryRepositoryJpa deliveryRepository;

    // Próxima clase EntityManager
    private final EntityManager entityManager;

    /*
     * public Optional<Delivery> findByIdWithDeliveryPerson(Long id) {
     * }
     * 
     * public Optional<Delivery> getByOrderId(UUID orderId) {
     * }
     * 
     * public Delivery save(Delivery delivery) {
     * }
     * 
     * public Delivery update(Delivery delivery) {
     * }
     */

}
