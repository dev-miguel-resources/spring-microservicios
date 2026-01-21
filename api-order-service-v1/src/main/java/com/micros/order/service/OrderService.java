package com.micros.order.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.micros.order.domain.Order;
import com.micros.order.dto.OrderResponse;
import com.micros.order.dto.UpdateOrderStatusRequest;
import com.micros.order.mapper.DomainToResponseMapper;
import com.micros.order.repository.OrderRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    // Caso de uso: crear Orden
    @Transactional
    public Order createOrder(Order domain) {

        // Generar un identificador único para las ordenes
        // Decimos con el negocio de como se va a generar ese tipo
        domain.setId(UUID.randomUUID());

        return orderRepository.save(domain);
    }

    @Transactional
    // Caso de uso: Actualizar el estado de una orden
    public OrderResponse updateOrderStatus(UUID orderId, UpdateOrderStatusRequest request) {

        // Buscar la arden por su id
        // Optional: para manipular los resultados gestionando el valor o empty
        Optional<Order> opt = orderRepository.findById(orderId);

        // Si la orden no existe, se lanza una excepción
        if (opt.isEmpty()) {
            throw new IllegalArgumentException("Order not found: " + orderId);
        }

        // Obtenemos la orden resultante
        Order domain = opt.get();

        // Actualizamos el estado de la orden
        domain.setStatus(request.status());

        // Guardar la orden actualizada en la bdd
        Order updated = orderRepository.save(domain);

        // Convertir la orden a un DTO de respuesta para enviarla al cliente
        return DomainToResponseMapper.toResponse(updated);

    }

}
