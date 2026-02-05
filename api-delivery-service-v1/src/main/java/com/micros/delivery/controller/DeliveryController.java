package com.micros.delivery.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micros.delivery.controller.dto.AssignDriverRequest;
import com.micros.delivery.controller.dto.DeliveryResponse;
import com.micros.delivery.domain.Delivery;
import com.micros.delivery.service.DeliveryService;
import com.micros.delivery.controller.mapper.DeliveryMapper;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    // Para asignar un repartidor a una entrega
    @PostMapping("/assign-driver")
    public ResponseEntity<DeliveryResponse> assignDriver(@RequestBody AssignDriverRequest request) {

        // Convertir el request en un objeto de dominio y llamar al service
        Delivery delivery = deliveryService.assignDriver(DeliveryMapper.toDomain(request));

        // Convertir el resultado a DTO de respuesta y retornar un HTTP 200 ok
        return ResponseEntity.ok(DeliveryMapper.toResponse(delivery));

    }

    // Para iniciar una entrega
    @PostMapping("/{orderId}/start")
    public ResponseEntity<DeliveryResponse> startDelivery(@PathVariable UUID orderId) {

        Delivery delivery = deliveryService.startDelivery(orderId);

        return ResponseEntity.ok(DeliveryMapper.toResponse(delivery));
    }

    // Para completar la entrega
    @PostMapping("/{orderId}/complete")
    public ResponseEntity<DeliveryResponse> completeDelivery(@PathVariable UUID orderId) {

        Delivery delivery = deliveryService.completeDelivery(orderId);

        return ResponseEntity.ok(DeliveryMapper.toResponse(delivery));
    }

}
