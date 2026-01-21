package com.micros.order.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micros.order.domain.Order;
import com.micros.order.dto.CreateOrderRequest;
import com.micros.order.dto.OrderResponse;
import com.micros.order.dto.UpdateOrderStatusRequest;
import com.micros.order.mapper.DomainToResponseMapper;
import com.micros.order.mapper.RequestToDomainMapper;
import com.micros.order.service.OrderService;

//import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/orders")
// @AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // ENDPOINT: CREATE ORDER: POST /orders
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest request) {

        // Convertir el dto del request en un objeto de dominio
        Order domain = RequestToDomainMapper.toDomain(request);

        // Ejecutar el caso de uso para crear la orden
        Order created = orderService.createOrder(domain);

        return ResponseEntity
                .status(201)
                .body(DomainToResponseMapper.toResponse(created));
    }

    // ENDPOINT: UPDATE ORDER STATUS: PUT /orders/{orderId}/status
    @PutMapping("/{orderId}/status")
    public ResponseEntity<OrderResponse> updateStatus(
            @PathVariable UUID orderId,
            @RequestBody UpdateOrderStatusRequest request) {

        // Ejecutar el caso de uso para actualizar el status
        OrderResponse updated = orderService.updateOrderStatus(orderId, request);

        // Retornamos la respuesta HTTP
        return ResponseEntity.ok(updated);

    }

}
