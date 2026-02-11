package com.micros.payment.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
// Define la clase Charge, que representa un cargo o cobro en el dominio de
// pagos
public class Charge {

    // Identificador del cliente al que pertenece el cargo
    private final Long customerId;

    // Identificador de la cuenta desde donde se realiza el cobro
    private final String accountId;

    // Monto del cargo, usamos BigDecimal para evitar errores de precisión
    private final BigDecimal amount;

}
