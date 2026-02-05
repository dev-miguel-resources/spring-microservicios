package com.micros.delivery.infraestructure.entity;

import java.util.UUID;

import com.micros.delivery.domain.DeliveryStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "deliveries")
public class DeliveryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID orderId;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private String reference;

    // Relación con el repartidor
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "delivery_person_id", nullable = false)
    private DeliveryPersonEntity deliveryPerson;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

}
