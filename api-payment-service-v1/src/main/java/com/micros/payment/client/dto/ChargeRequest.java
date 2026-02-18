package com.micros.payment.client.dto;

import java.math.BigDecimal;

public record ChargeRequest(
                String accountId,
                BigDecimal amount) {

}
