package com.micros.restaurant.repository;

import org.springframework.stereotype.Repository;

import com.micros.restaurant.domain.Order;
import com.micros.restaurant.infraestructure.entity.OrderEntity;
import com.micros.restaurant.infraestructure.repository.OrderJpaRepository;
import com.micros.restaurant.repository.mapper.OrderRepositoryMapper;

@Repository
public class OrderRepository {

    // Nos traemos el repositorio de JPA que ejecuta las operaciones en la BDD
    private final OrderJpaRepository jpaRepository;

    // Necesitams un mapper que transforme la data entre objetos de dominio y
    // entidades
    private final OrderRepositoryMapper mapper;

    public OrderRepository(OrderJpaRepository jpaRepository, OrderRepositoryMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;

    }

    // Guardar una orden del dominio en la bdd
    public Order save(Order order) {

        // Convertir el objeto de dominio a la entidad
        OrderEntity entity = mapper.toEntity(order);

        // Persistir la entidad en la bdd
        OrderEntity saved = jpaRepository.save(entity);

        // Se puede retornar el resultado
        return mapper.toDomain(saved);
    }

}