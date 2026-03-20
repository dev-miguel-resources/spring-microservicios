package com.micros.order.factory;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
// Indica que las propiedades del application.yml que comiencen
// "http-clients" serán mapeadas automáticamente a esta clase
@ConfigurationProperties("http-clients")
public class HttpClientsProperties {

    // Mapa (Map) almacenará las configs. de clientes HTTP internos
    // La clave (key) representa el nombre del cliente (por ej: order, payment,
    // etc...)
    // El valor (HttpClientConfig) contiene la configuración del cliente (baseUrl y
    // timeout)
    // Este mapa se llenará automáticamente con los datos del application.yml
    // específicamente desde la sección: http-clients.internal
    private Map<String, HttpClientConfig> internal = new HashMap<>();

}
