package com.micros.payment.service;

import org.springframework.stereotype.Service;

import com.micros.payment.domain.Charge;
import com.micros.payment.repository.ChargeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
// Define el caso de uso encargado de procesar pagos
public class PaymentService {

    // Repositorio que guarda los cargos en la base de datos
    private final ChargeRepository chargeRepository;

    // Cliente implicado

    // Método que ejecuta un cargo a una cuenta
    public void charge(Charge charge) {

    }

}
