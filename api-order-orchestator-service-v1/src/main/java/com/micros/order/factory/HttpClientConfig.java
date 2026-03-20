package com.micros.order.factory;

import java.time.Duration;

import lombok.Data;

@Data
// Declaración de la clase que representa la configuración de un cliente HTTP
// para el orchestator
public class HttpClientConfig {

    // Variable que almacenará la URL base el servicio al que se realizarán las
    // llamadas HTTP
    private String baseUrl;

    // Variable que define el tiempo máximo que el cliente HTTP esperará por una
    // respuesta
    // antes de lanzar un timeout
    private Duration timeout;

}
