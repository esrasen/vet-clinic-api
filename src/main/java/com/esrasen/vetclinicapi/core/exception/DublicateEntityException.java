package com.esrasen.vetclinicapi.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DublicateEntityException extends RuntimeException{

    public DublicateEntityException(String message) {
        super(message);
    }
}
