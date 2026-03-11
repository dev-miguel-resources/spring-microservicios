package com.micros.visa.controller;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micros.visa.controller.dto.ChargeRequest;
import com.micros.visa.controller.dto.ChargeResponse;
import com.micros.visa.controller.dto.CheckBalanceRequest;
import com.micros.visa.controller.dto.CheckBalanceResponse;

@RestController
@RequestMapping("/account")
public class VisaController {

    // Map concurrente que se usa para contar cuantas veces ha fallado
    // un intento de cobro para un accountId específico
    // Para hacer esto se ocupa una clase llamada ConcurrentHashMap
    private final Map<String, Integer> failCounter = new ConcurrentHashMap<>();

    @PostMapping("/check-balance")

    public ResponseEntity<CheckBalanceResponse> checkBalance(@RequestBody CheckBalanceRequest request)
            throws InterruptedException {

        // Obtener el accountId enviado en el request
        String accountId = request.accountId();

        // Caso de error simulado
        // Si el accountId por ej termina en 999 se lanza un error inesperado
        // Simular una caida o bug en el sistema externo
        if (accountId.endsWith("999")) {
            throw new RuntimeException("Error inesperado en el servidor para accountId " + accountId);
        }

        // Caso de timeout
        // Si el accountId termina en 888 se simula una demora de 5 seg.
        // Esto nos permite probar mecanismos de timeout o retry en microservicios.
        if (accountId.endsWith("888")) {
            Thread.sleep(5000);
        }

        // Simulación del manejo de saldo
        // Se define un saldo fijo de 500 USD para simplificación del ejercicio
        BigDecimal balance = BigDecimal.valueOf(500);

        // Se verifica si el saldo es suficiente para cubrir el monto requerido
        // compareTo devuelve:
        // -1 -> menor
        // 0 -> igual
        // 1 -> mayor
        boolean sufficient = balance.compareTo(request.requiredAmount()) >= 0;

        // Retornar respuesta http 200 con el saldo y si es suficiente
        return ResponseEntity.ok(new CheckBalanceResponse(accountId, balance, sufficient));
    }

    @PostMapping("/charge")
    public ResponseEntity<ChargeResponse> charge(@RequestBody ChargeRequest request) throws InterruptedException {

        // Obtener el accountId enviado en el request
        String accountId = request.accountId();

        // Caso de error inesperado
        // Si termina en 999 se simula un error interno del servidor
        if (accountId.endsWith("999")) {

            return new ResponseEntity<>(
                    new ChargeResponse(accountId, request.amount(), "ERROR"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Caso de timeout
        // Simula un servicio lento que tarda 5 seg. en responder
        if (accountId.endsWith("888")) {
            Thread.sleep(5000);
        }

        // Caso de Retry
        // Ej de simulación: si el accountId termina en 777 se simula qu el servicio
        // falla
        // Las primeras 3 veces antes de funcionar correctamente
        if (accountId.endsWith("777")) {

            // Obtener cuantos intentos fallidos se han registrado
            // si no existe el valor a retornar es 0
            int count = failCounter.getOrDefault(accountId, 0);

            // i ha fallado menos de 3 veces
            if (count < 3) {

                // Se incrementa el contador
                failCounter.put(accountId, count + 1);

                // Se retorna error 500 indicando que debe reitentarse
                return new ResponseEntity<>(
                        new ChargeResponse(accountId, request.amount(), "RETRY_ERROR"),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                // Si ya falló 3 veces se elimina el contador
                // permitiendo que la operación continúe normalmente
                failCounter.remove(accountId);
            }
        }

        // Caso de saldo insuficiente
        // Si el monto solicitado es mayor a 500
        // Se simula una falla por saldo insuficiente
        if (request.amount().compareTo(BigDecimal.valueOf(500)) > 0) {

            return new ResponseEntity<>(
                    new ChargeResponse(accountId, request.amount(), "INSUFFICIENT_FUNDS"),
                    HttpStatus.BAD_REQUEST);
        }

        // Caso normal (Éxito)
        // Si ninguna condición anterior se cumple
        // el cargo se procesa exitosamente
        return ResponseEntity.ok(
                new ChargeResponse(accountId, request.amount(), "SUCCESS"));
    }

}
