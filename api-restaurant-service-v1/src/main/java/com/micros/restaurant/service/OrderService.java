package com.micros.restaurant.service;

import org.springframework.stereotype.Service;

import com.micros.restaurant.domain.Order;
import com.micros.restaurant.repository.OrderRepository;

@Service
public class OrderService {

    // Repositorio encargado de la lógica de guardar órdenes en la bdd
    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    // Se encapsula el caso de uso: Reservar una orden
    public Order reserveOrder(Order order) {
        return repository.save(order);
    }

}
