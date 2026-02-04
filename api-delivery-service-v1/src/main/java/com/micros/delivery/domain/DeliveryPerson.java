package com.micros.delivery.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeliveryPerson {

    private Long id;

    private String name;

    private String phoneNumber;

    // Tipos tales como: moto, bici, auto, etc...
    private String vehicleType;

    // Placa del vehiculo del repartidor
    private String licensePlate;

    public DeliveryPerson(Long id) {
        this.id = id;
    }

}
