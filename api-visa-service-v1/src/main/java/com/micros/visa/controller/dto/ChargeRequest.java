package com.micros.visa.controller.dto;

import java.math.BigDecimal;

public record ChargeRequest(

        // Identificador de la cuenta
        String accountId,

        // Monto que se desea cobrar a la cuenta
        BigDecimal amount) {

}
