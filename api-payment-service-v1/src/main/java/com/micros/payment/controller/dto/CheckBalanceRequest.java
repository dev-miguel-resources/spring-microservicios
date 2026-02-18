package com.micros.payment.controller.dto;

import java.math.BigDecimal;

// Representa la solicitud de verificación de saldo (request)
// Incluye el id del cliente, el id de la cuenta y el monto requerido a validar
public record CheckBalanceRequest(
        Long customerId,
        String accountId,
        BigDecimal requiredAmount) {

}
