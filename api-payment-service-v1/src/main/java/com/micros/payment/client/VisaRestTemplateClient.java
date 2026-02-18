package com.micros.payment.client;

import java.math.BigDecimal;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.micros.payment.client.dto.ChargeRequest;
import com.micros.payment.client.dto.ChargeResponse;
import com.micros.payment.client.dto.CheckBalanceRequest;
import com.micros.payment.client.dto.CheckBalanceResponse;
import com.micros.payment.error.ChargeErrorException;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
// Define el cliente encargado de comunicarse con el servicio externo
// correspondiente
public class VisaRestTemplateClient {

    // la inyección de dependencia recién creada
    private final RestTemplate restTemplate;

    // Formar la URL del servicio externo de Visa
    private final String baseUrl = "http://localhost:52010/api/visa/account/";

    // Método que consulta si una cuenta tiene saldo suficiente
    public CheckBalanceResponse checkBalance(String accountId, BigDecimal requiredAmount) {

        // Construir el request que será enviado al servicio externo
        CheckBalanceRequest request = new CheckBalanceRequest(accountId, requiredAmount);

        // Realizar una petición POST que retorne la respuesta mapeada al dto de
        // response
        return restTemplate.postForObject(baseUrl + "/check-balance", request, CheckBalanceResponse.class);
    }

    // Método que ejecuta un cargo sobre una cuenta
    public ChargeResponse charge(String accountId, BigDecimal amount) {

        // Construimos el request de cargo
        ChargeRequest request = new ChargeRequest(accountId, amount);

        try {
            // Ejecutar una petición POST mediante exchange para captura el cuerpo completo
            // de la respuesta
            ResponseEntity<ChargeResponse> response = restTemplate.exchange(
                    baseUrl + "/charge", // URL del endpoint
                    HttpMethod.POST, // verbo
                    new HttpEntity<>(request), // cuerpo de la petición
                    ChargeResponse.class);

            // Retornar el cuerpo de la respuesta si todo fue exitoso
            return response.getBody();

        } catch (HttpClientErrorException | HttpServerErrorException ex) {

            // Obtener el código de estado HTTP retornado por el servicio externo
            HttpStatusCode status = ex.getStatusCode();

            // Convertir el cuerpo de error a ChargeResponse
            ChargeResponse charResponseError = ex.getResponseBodyAs(ChargeResponse.class);

            // Lanzar una excepcion personalizada con los datos de error recibido desde Visa
            throw new ChargeErrorException(
                    "Error from VISA: " + status,
                    charResponseError.status(),
                    status.value());
        }
    }

}
