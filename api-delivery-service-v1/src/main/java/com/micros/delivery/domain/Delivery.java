package com.micros.delivery.domain;

import java.util.UUID;

import lombok.Data;

@Data
public class Delivery {

    // Id único de la entrega
    private Long id;

    // id único asociado del pedido a esta entrega
    private UUID orderId;

    private String address;

    // puntos geográficos
    private Double latitude;

    // puntos geográficos
    private Double longitude;

    // Referencia adicional para ubicar mejor la dirección
    private String reference;

    // Repartidor asignado a esta entrega
    private DeliveryPerson deliveryPerson;

    // Estado actual de la entrega (pendiente, en camino, entregado, etc...)
    private DeliveryStatus status;

}
