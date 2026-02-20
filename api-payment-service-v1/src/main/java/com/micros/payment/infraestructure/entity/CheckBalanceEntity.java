package com.micros.payment.infraestructure.entity;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
@Table(name = "check_balance")
public class CheckBalanceEntity {

    @Id
    private UUID id;

    private Long customerId;

    private String accountId;

    private BigDecimal requiredAmount;

    // Indica si la cuenta tiene fondos suficientes para cubrir el monto requerido
    private boolean hasSufficientFunds;

}
