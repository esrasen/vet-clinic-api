package com.esrasen.vetclinicapi.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AppointmentException extends RuntimeException{

    public AppointmentException(String message) {

        super(message);
    }
}
