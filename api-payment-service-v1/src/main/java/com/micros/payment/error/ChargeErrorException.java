package com.micros.payment.error;

import lombok.Getter;

@Getter
// Define una excepción personalizada para errores en procesos de cobro
public class ChargeErrorException extends RuntimeException {

    // Código de estado HTTP asociado al error
    private final int httpStatusCode;

    // Estado o tipo de error (por ej: "FAILED", "DECLINED", etc...)
    private final String status;

    // Armar un constructor personalizado que inicializa el mensaje de error, status
    // y el código HTTP
    public ChargeErrorException(String message, String status, int httpStatusCode) {

        // Llamada al constructor padre para establecer el mensaje base
        super(message);

        this.status = status;

        this.httpStatusCode = httpStatusCode;
    }

}
