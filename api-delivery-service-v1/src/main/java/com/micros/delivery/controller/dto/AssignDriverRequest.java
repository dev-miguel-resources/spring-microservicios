package com.micros.delivery.controller.dto;

import java.util.UUID;

// Representa la solicitud para asignar un repartidor a una entrega
public record AssignDriverRequest(

        // Identificador del pedido
        UUID orderId,

        // Datos de la dirección de entrega
        DeliveryAddressRequest deliveryAddress,

        // La referencia del id del repartidor
        DeliveryPersonRequest deliveryPerson) {

}
