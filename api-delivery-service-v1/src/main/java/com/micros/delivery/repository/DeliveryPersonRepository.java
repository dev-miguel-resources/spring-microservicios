package com.micros.delivery.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.micros.delivery.domain.DeliveryPerson;
import com.micros.delivery.infraestructure.repository.DeliveryPersonRepositoryJpa;
import com.micros.delivery.repository.mapper.DeliveryPersonMapper;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class DeliveryPersonRepository {

    private final DeliveryPersonRepositoryJpa deliveryPersonRepository;

    public Optional<DeliveryPerson> getById(Long id) {
        return deliveryPersonRepository.findById(id).map(DeliveryPersonMapper::toDomain);
    }

}
