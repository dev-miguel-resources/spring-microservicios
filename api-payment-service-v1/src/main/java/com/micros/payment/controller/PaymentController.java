package com.micros.payment.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micros.payment.controller.dto.ChargeRequest;
import com.micros.payment.domain.Charge;
import com.micros.payment.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    // Servicio encargado de procesos los pagos
    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    // Mapeo de la ruta /payments/charge
    @PostMapping("/charge")
    public void charge(@RequestBody ChargeRequest chargeRequest) {

        // Convertir el DTO de entrada (request) en un objeto de dominio
        Charge charge = new Charge(
                chargeRequest.customerId(),
                chargeRequest.accountId(),
                chargeRequest.amount());

        // llamar al servicio para ejecutar el cobro
        service.charge(charge);
    }

}
