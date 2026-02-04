package com.micros.delivery.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.micros.delivery.domain.Delivery;
import com.micros.delivery.domain.DeliveryStatus;
import com.micros.delivery.repository.DeliveryPersonRepository;
import com.micros.delivery.repository.DeliveryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    private final DeliveryPersonRepository deliveryPersonRepository;

    // Se asegura que o se completa toda la transacción o se revierte y no se guarda
    // nada
    @Transactional
    // Asignar un repartidor a una entrega
    public Delivery assignDriver(Delivery delivery) {

        deliveryPersonRepository.getById(delivery.getDeliveryPerson().getId())
                .orElseThrow(() -> new RuntimeException("Delivery person not found"));

        // Cambiar el estado de la entrega a ASSIGNED
        delivery.setStatus(DeliveryStatus.ASSIGNED);

        // Guardar la entrega en la bdd y retornarla
        return deliveryRepository.save(delivery);

    }

    @Transactional
    // Iniciar una entrega usando el id de la orden
    public Delivery startDelivery(UUID orderId) {

        // Buscar la entrega asociada al pedido o lanzar un error si no existe
        Delivery delivery = deliveryRepository.getByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));

        delivery.setStatus(DeliveryStatus.STARTED);

        return deliveryRepository.save(delivery);
    }

    @Transactional
    // Marca una entrega como completada de acuerdo al id de la orden
    public Delivery completeDelivery(UUID orderId) {

        // Buscar la entrega asociada al pedido o lanzar error si no existe
        Delivery delivery = deliveryRepository.getByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));

        delivery.setStatus(DeliveryStatus.COMPLETED);

        // Guardamos y retornamos la entrega actualizada
        return deliveryRepository.save(delivery);

    }

}
