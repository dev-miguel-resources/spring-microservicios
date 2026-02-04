package com.micros.delivery.controller.dto;

public record DeliveryAddressRequest(

        // Dirección de entrega
        String address,

        // Latitud del punto de entrega
        Double latitude,

        // Longitud del punto de entrega
        Double longitude,

        // Referencia adicional para ubicar mejor la ubicación
        String reference) {

}
