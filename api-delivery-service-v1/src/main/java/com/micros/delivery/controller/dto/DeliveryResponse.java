package com.micros.delivery.controller.dto;

import java.util.UUID;

public record DeliveryResponse(

                Long id,

                UUID orderId,

                String address,

                String status,

                String deliveryPersonName) {

}
