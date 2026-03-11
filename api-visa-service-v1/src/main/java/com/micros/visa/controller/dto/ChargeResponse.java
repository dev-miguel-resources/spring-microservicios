package com.micros.visa.controller.dto;

import java.math.BigDecimal;

public record ChargeResponse(

                // Identificador de la cuenta a la cual se le intentó realizar el cobro
                String accountId,

                // Monto que se intentó cobrar o que fue cobrado a la cuenta
                BigDecimal amount,

                // Estado final de la operación de cobro
                // Ej: SUCCESS, ERROR, RETRY_ERROR
                String status) {

}
