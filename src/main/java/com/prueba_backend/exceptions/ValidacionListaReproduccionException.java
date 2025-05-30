package com.prueba_backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidacionListaReproduccionException extends RuntimeException {
    public ValidacionListaReproduccionException(String mensaje) {
        super(mensaje);
    }
} 