package com.micros.payment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micros.payment.controller.dto.CheckBalanceRequest;
import com.micros.payment.domain.CheckBalance;
import com.micros.payment.service.CheckBalanceService;

@RestController
@RequestMapping("/payments")
public class CheckBalanceController {

    // Servicio encargado para validar fondos
    private final CheckBalanceService service;

    public CheckBalanceController(CheckBalanceService service) {
        this.service = service;
    }

    // Mapear la ruta: /payments/check-balance
    @PostMapping("/check-balance")
    public ResponseEntity<Void> checkBalance(@RequestBody CheckBalanceRequest request) {

        CheckBalance checkBalance = CheckBalance.createNew(request.customerId(), request.accountId(),
                request.requiredAmount());

        // Llamar al servicio para verificar si hay fondos suficientes
        if (service.checkFunds(checkBalance)) {
            // Retornar HTTP 200 que si hay fondos suficientes
            return ResponseEntity.ok().build();
        }

        // Retornar HTTP 400 Bad Request si no hay fondos suficientes
        return ResponseEntity.badRequest().build();
    }

}
