package com.micros.visa.controller.dto;

import java.math.BigDecimal;

public record CheckBalanceRequest(

                // Identificador de la cuenta o tarjeta que se quiere consultar
                String accountId,

                // Monto requerido para realizar una operación (pago)
                BigDecimal requiredAmount) {

}
