package com.micros.visa.controller.dto;

import java.math.BigDecimal;

public record CheckBalanceResponse(

                // Identificador de la cuenta consultada
                String accountId,

                // Saldo actual disponible en la cuenta
                // En el control vamos a hacer ciertas simulaciones
                BigDecimal balance,

                // true -> hay saldo suficiente
                // false -> no hay saldo suficiente
                boolean sufficient) {

}
