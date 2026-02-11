package com.micros.payment.service;

import org.springframework.stereotype.Service;

import com.micros.payment.domain.CheckBalance;
import com.micros.payment.repository.CheckBalanceRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
// Servicio encargado de manejar el caso de uso de validar fondos disponibles
public class CheckBalanceService {

    // Necesitamos traernos el repositorio que persiste los resultados de la
    // verificación de saldo
    private final CheckBalanceRepository repository;

    // Cliente encargado de la comunicación con un sistema externo (VISA)
    public boolean checkFunds(CheckBalance checkBalance) {
        return true;
    }

}
