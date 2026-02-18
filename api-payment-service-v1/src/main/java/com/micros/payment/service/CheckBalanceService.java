package com.micros.payment.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.micros.payment.client.VisaRestTemplateClient;
import com.micros.payment.client.dto.CheckBalanceResponse;
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

    // Nos traemos el cliente
    private final VisaRestTemplateClient visaClient;

    // Método que valida si una cuenta tiene fondos suficientes para un monto
    // determinado
    public boolean checkFunds(CheckBalance checkBalance) {

        // Llamar al servicio externo para verificar el saldo de la cuenta
        CheckBalanceResponse checkBalanceResponse = visaClient.checkBalance(checkBalance.getAccountId(),
                checkBalance.getRequiredAmount());

        // Asignar un identificador único a la solicitud de verificación
        checkBalance.setId(UUID.randomUUID());

        // Guardar el resultado en el repositorio y retornamos si hay fondos suficientes
        return repository.saveCheckResult(checkBalance, checkBalanceResponse.sufficient());
    }

}
