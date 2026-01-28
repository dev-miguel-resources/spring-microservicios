package com.micros.restaurant.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micros.restaurant.controller.mapper.OrderControllerMapper;
import com.micros.restaurant.domain.Order;
import com.micros.restaurant.dto.ReserveOrderRequestDTO;
import com.micros.restaurant.dto.ReserveOrderResponseDTO;
import com.micros.restaurant.service.OrderService;

@RestController
@RequestMapping("/restaurants")
public class OrderController {

    // Traernos el servicio que maneja la lógica de negocio de las órdenes
    private final OrderService orderService;

    // Traernos el Mapper que convierte entre DTOS y objetos de dominio
    // pendiente
    private OrderControllerMapper orderControllerMapper;

    // Constructor para realizar las inyecciones de dependencia
    public OrderController(OrderService orderService, OrderControllerMapper orderControllerMapper) {
        this.orderService = orderService;
        this.orderControllerMapper = orderControllerMapper;
    }

    @PostMapping("/{restaurantId}/orders/reserve")
    public ResponseEntity<ReserveOrderResponseDTO> reserveOrder(

            // Obtener el restaurant Id de la URL
            @PathVariable Long restaurantId,

            @RequestBody ReserveOrderRequestDTO request) {

        ReserveOrderRequestDTO updatedRequest = new ReserveOrderRequestDTO(
                request.orderId(),
                restaurantId,
                request.customerId(),
                request.items());

        // Convertir el DTO a objeto de dominio
        Order order = orderControllerMapper.toDomain(updatedRequest);

        // Ejecutar el caso de uso para reservar la orden
        Order reservedOrder = orderService.reserveOrder(order);

        // Convertimos el dominio a DTO de respuesta y retornamos un status ok 200
        return ResponseEntity.ok(orderControllerMapper.toResponse(reservedOrder));

    }
}
