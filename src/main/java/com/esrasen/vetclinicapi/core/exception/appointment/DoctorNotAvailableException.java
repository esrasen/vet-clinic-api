package com.esrasen.vetclinicapi.core.exception.appointment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DoctorNotAvailableException extends RuntimeException{

    public DoctorNotAvailableException(String message) {

        super(message);
    }
}
