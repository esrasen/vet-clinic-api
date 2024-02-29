package com.esrasen.vetclinicapi.core.exception.appointment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class AppointmentSlotNotAvailableException extends RuntimeException{

    public AppointmentSlotNotAvailableException(String message) {

        super(message);
    }
}
