package com.micros.delivery.controller.mapper;

import com.micros.delivery.controller.dto.AssignDriverRequest;
import com.micros.delivery.domain.Delivery;
import com.micros.delivery.domain.DeliveryPerson;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DeliveryMapper {

    public static Delivery toDomain(AssignDriverRequest request) {

        Delivery domain = new Delivery();

        // asignamos el orderId desde el request
        domain.setOrderId(request.orderId());

        // asignamos la dirección desde el objeto deliveryAddress del request
        domain.setAddress(request.deliveryAddress().address());

        // asignar la latitud desde el request
        domain.setLatitude(request.deliveryAddress().latitude());

        // asignamos la longitud desde el request
        domain.setLongitude(request.deliveryAddress().longitude());

        // asignamos la referencia desde el request
        domain.setReference(request.deliveryAddress().reference());

        // Asignar el delivery person usando el id recibido y lo asignamos a la entrega
        domain.setDeliveryPerson(new DeliveryPerson(request.deliveryPerson().id()));

        return domain;
    }

    // public static DeliveryResponse toResponse()

}
