package com.micros.restaurant.infraestructure.entity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    private UUID id;

    @ManyToOne
    private RestaurantEntity restaurant;

    private Long customerId;

    // Relación
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItemEntity> items;

    private String status;

}
