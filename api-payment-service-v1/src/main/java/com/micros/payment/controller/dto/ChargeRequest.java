package com.micros.payment.controller.dto;

import java.math.BigDecimal;

// Define lo que representa a la solicitud de un cargo (request) enviado al sistema
// solicita el id del cliente, el id de la cuenta y el monto a cobrar
public record ChargeRequest(
        Long customerId,
        String accountId,
        BigDecimal amount) {

}
