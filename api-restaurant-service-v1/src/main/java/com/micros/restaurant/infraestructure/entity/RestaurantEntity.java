package com.micros.restaurant.infraestructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "restaurants")
public class RestaurantEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
