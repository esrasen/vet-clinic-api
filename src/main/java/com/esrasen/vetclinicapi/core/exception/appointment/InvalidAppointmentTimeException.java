package com.esrasen.vetclinicapi.core.exception.appointment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidAppointmentTimeException extends RuntimeException{

    public InvalidAppointmentTimeException(String message) {

        super(message);
    }
}
