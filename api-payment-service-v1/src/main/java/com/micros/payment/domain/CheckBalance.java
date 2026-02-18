package com.micros.payment.domain;

import java.math.BigDecimal;
import java.util.UUID;

import org.hibernate.annotations.Check;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
// Define la clase CheckBalance, que representa una solicitud de validación de
// saldo
public class CheckBalance {

    // Identificador único del chequeo de saldo
    private UUID id;

    // Identificador del cliente que solicita el chequeo
    private Long customerId;

    // Identificador de la cuenta asociada
    private String accountId;

    // Monto requerido que se desea validar contra el saldo disponible
    private BigDecimal requiredAmount;

    // Constructor personalizado
    public static CheckBalance createNew(Long customerId, String accountId, BigDecimal requiredAmount) {
        return new CheckBalance(null, customerId, accountId, requiredAmount);
    }

}
