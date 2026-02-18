package com.micros.payment.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    // Indica que este método expone un bean administrado por Spring en memoria
    @Bean
    public RestTemplate restTemplate() {

        // Crear y retornar una instancia de Rest Template para hacer inyección de
        // dependencias
        // para el manejo de llamadas con client
        return new RestTemplate();

    }

}
