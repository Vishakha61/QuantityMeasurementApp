package com.app.quantitymeasurement.exception;

import com.app.quantitymeasurement.model.QuantityDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            QuantityMeasurementException.class
    )
    public ResponseEntity<QuantityDTO> handleQuantityException(
            QuantityMeasurementException exception
    ) {

        return new ResponseEntity<>(

                new QuantityDTO(
                        exception.getMessage()
                ),

                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(
            MethodArgumentNotValidException.class
    )
    public ResponseEntity<QuantityDTO> handleValidationException(
            MethodArgumentNotValidException exception
    ) {

        String message =
                exception
                        .getBindingResult()
                        .getFieldError()
                        .getDefaultMessage();

        return new ResponseEntity<>(

                new QuantityDTO(message),

                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(
            Exception.class
    )
    public ResponseEntity<QuantityDTO> handleException(
            Exception exception
    ) {

        return new ResponseEntity<>(

                new QuantityDTO(
                        exception.getMessage()
                ),

                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}