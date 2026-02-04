package com.micros.delivery.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micros.delivery.domain.Delivery;
import com.micros.delivery.repository.mapper.DeliveryMapper;
import com.micros.delivery.service.DeliveryService;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping("/assign-driver")
    public ResponseEntity<DeliveryResponse> assignDriver(@RequestBody AssignDriverRequest request) {

        // Convertir el request en un objeto de dominio y llamar al service
        Delivery delivery = deliveryService.assignDriver(DeliveryMapper.toDomain(request));

        // Convertir el resultado a DTO de respuesta y retornar un HTTP 200 ok
        return ResponseEntity.ok(DeliveryMapper.toResponse(delivery));

    }

}
