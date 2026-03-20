package com.micros.order.factory;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.netty.http.client.HttpClient;

@Slf4j
@Configuration
public class WebClientFactory {

    // Definir un bean de Spring que construirá y registrará un Map de WebClients
    // en el contenedor de Spring para poder ser usado por otros componentes
    @Bean
    public Map<String, WebClient> buildWebClients(HttpClientsProperties properties) {

        // Escribir un mensaje en el log indicando que se están creando los clientes
        // HTTP
        log.info("Creating WebClients");

        // Mapa donde se almacenarán los WebClients creados
        // La key será el nombre del cliente (order, payment, restaurant, etc...)
        Map<String, WebClient> clients = new HashMap<>();

        // Recorrer todas las configs. definidas en http-clients.internal del archivo
        // properties
        for (Map.Entry<String, HttpClientConfig> entry : properties.getInternal().entrySet()) {

            // Obtener la configuración del client HTTP actual
            HttpClientConfig config = entry.getValue();

            // Crear un client HTTP reactivo usando reactor Netty
            // y configurar el tiempo máximo de espera de respuesta (timeout)
            HttpClient httpClient = HttpClient.create().responseTimeout(config.getTimeout());

            // Construir el WebClient con la configuración definida
            WebClient webClient = WebClient.builder()

                    // Define la url base del servicio al que se harán las llamadas
                    .baseUrl(config.getBaseUrl())

                    // Configurar el conector HTTP que usará Reactor Netty
                    .clientConnector(new ReactorClientHttpConnector(httpClient))

                    // Construir la instancia final del WebClient
                    .build();

            // Guardar el WebClient creado en el mapa usando como llave
            // el nombre de los clientes
            clients.put(entry.getKey(), webClient);

        }

        // Retornamos el mapa con todos los WebClients creados
        // para que Spring lo registre como Bean en memoria y sea inyectado
        return clients;

    }

}
