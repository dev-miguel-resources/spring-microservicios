package com.micros.delivery.infraestructure.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.micros.delivery.infraestructure.entity.DeliveryEntity;

public interface DeliveryRepositoryJpa extends JpaRepository<DeliveryEntity, Long> {

    // Si hay una query personalizada a definir se hace acá
    // Vamos a tener un método que busca una entrega usando el orderId
    // hay algo en spring que me haga la query automática de acuerdo a una
    // convención de nombre???
    Optional<DeliveryEntity> findByOrderId(UUID orderId);

    // Buscar una entrega por su ID y traer también la información del repartidor en
    // la misma consulta
    // Pueden definir que si la carga asociada la quieren gestionar particionada o
    // no
    // JOIN: cargas lazy
    // JOIN FETCH: carga inmediata
    @Query("SELECT d FROM DeliveryEntity d JOIN FETCH d.deliveryPerson WHERE d.id = :id")
    Optional<DeliveryEntity> findByIdWithDeliveryPerson(@Param("id") Long id);
}
