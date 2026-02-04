package com.micros.delivery.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.micros.delivery.domain.Delivery;
import com.micros.delivery.infraestructure.entity.DeliveryEntity;
import com.micros.delivery.infraestructure.repository.DeliveryRepositoryJpa;
import com.micros.delivery.repository.mapper.DeliveryMapper;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class DeliveryRepository {

    private final DeliveryRepositoryJpa deliveryRepository;

    private final EntityManager entityManager;

    public Optional<Delivery> findByIdWithDeliveryPerson(Long id) {
        return deliveryRepository.findByIdWithDeliveryPerson(id).map(DeliveryMapper::toDomain);
    }

    public Optional<Delivery> getByOrderId(UUID orderId) {
        return deliveryRepository.findByOrderId(orderId).map(DeliveryMapper::toDomain);
    }

    public Delivery save(Delivery delivery) {

        DeliveryEntity saved = deliveryRepository.save(DeliveryMapper.toEntity(delivery));

        // escritura rápida
        entityManager.flush();

        // preparación óptima a la referencia actual de ese elemento en bdd
        entityManager.refresh(saved);

        return DeliveryMapper.toDomain(saved);

    }

    public Delivery update(Delivery delivery) {

        DeliveryEntity updated = deliveryRepository.save(DeliveryMapper.toEntity(delivery));

        return DeliveryMapper.toDomain(updated);
    }

}
