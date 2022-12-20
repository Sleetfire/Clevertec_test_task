package com.barkovsky.check_runner.controller.advice;

import com.barkovsky.check_runner.exception.ConversionException;
import com.barkovsky.check_runner.exception.DeserializationException;
import com.barkovsky.check_runner.exception.EssenceNotFoundException;
import com.barkovsky.check_runner.exception.SerializationException;
import com.barkovsky.check_runner.model.error.SingleResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(ConversionException.class)
    public ResponseEntity<SingleResponseError> handleConversionException(ConversionException exception) {
        return new ResponseEntity<>(new SingleResponseError(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DeserializationException.class)
    public ResponseEntity<SingleResponseError> handleDeserializationException(DeserializationException exception) {
        return new ResponseEntity<>(new SingleResponseError(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EssenceNotFoundException.class)
    public ResponseEntity<SingleResponseError> handleEssenceNotFoundException(EssenceNotFoundException exception) {
        return new ResponseEntity<>(new SingleResponseError(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SerializationException.class)
    public ResponseEntity<SingleResponseError> handleSerializationException(SerializationException exception) {
        return new ResponseEntity<>(new SingleResponseError(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
