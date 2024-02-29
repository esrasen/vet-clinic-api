package com.esrasen.vetclinicapi.core.config;

import com.esrasen.vetclinicapi.core.exception.appointment.DoctorNotAvailableException;
import com.esrasen.vetclinicapi.core.exception.NotFoundException;
import com.esrasen.vetclinicapi.core.exception.appointment.AppointmentSlotNotAvailableException;
import com.esrasen.vetclinicapi.core.exception.appointment.InvalidAppointmentTimeException;
import com.esrasen.vetclinicapi.core.result.Result;
import com.esrasen.vetclinicapi.core.result.ResultData;
import com.esrasen.vetclinicapi.core.utilies.ResultHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Result> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<>(ResultHelper.notFoundError(e.getMessage()), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultData<List<String>>> handleValidationErrors(MethodArgumentNotValidException e) {
        List<String> validationErrorList = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage).collect(Collectors.toList());

        return new ResponseEntity<>(ResultHelper.validateError(validationErrorList), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DoctorNotAvailableException.class)
    public ResponseEntity<Result> handleAppointmentException(DoctorNotAvailableException e) {
        return new ResponseEntity<>(ResultHelper.alreadyExist(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidAppointmentTimeException.class)
    public ResponseEntity<Result> handleInvalidAppointmentTimeException(InvalidAppointmentTimeException e) {
        return new ResponseEntity<>(ResultHelper.badRequest(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AppointmentSlotNotAvailableException.class)
    public ResponseEntity<Result> handleAppointmentSlotNotAvailableException(AppointmentSlotNotAvailableException e) {
        return new ResponseEntity<>(ResultHelper.conflict(e.getMessage()), HttpStatus.CONFLICT);
    }
}
