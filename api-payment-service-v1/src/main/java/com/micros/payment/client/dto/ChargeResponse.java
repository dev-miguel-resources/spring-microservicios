package com.micros.payment.client.dto;

import java.math.BigDecimal;

public record ChargeResponse(
        String accountId,
        BigDecimal amount,
        String status) {

}
