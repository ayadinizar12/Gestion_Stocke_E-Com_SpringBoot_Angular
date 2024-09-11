package com.ayed.gestionstock.handlers;

import com.ayed.gestionstock.exception.entityNotFoundException;
import com.ayed.gestionstock.exception.errorCodes;
import com.ayed.gestionstock.exception.invalidEntityException;
import jakarta.persistence.EntityNotFoundException;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class restExceptionHandlers extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<errorDTO> handleExeption(
            entityNotFoundException entitynotFoundException,
            WebRequest webRequest){

        final HttpStatus httpStatus= HttpStatus.NOT_FOUND;
        final errorDTO errorDTo=errorDTO.builder()
                .httpCode(httpStatus.value())
                .code(entitynotFoundException.getErrorCode())
                .message(entitynotFoundException.getMessage())
                .build();

        return new ResponseEntity<>(errorDTo,httpStatus);
    }

    @ExceptionHandler(InvalidClassException.class)
    public ResponseEntity<errorDTO> handleExeption(
            invalidEntityException invalidEntityException,
            WebRequest webRequest){
        final HttpStatus httpStatus= HttpStatus.BAD_REQUEST;
        final errorDTO errorDTo=errorDTO.builder()
                .httpCode(httpStatus.value())
                .code(invalidEntityException.getErrorCode())
                .message(invalidEntityException.getMessage())
                .build();
        return new ResponseEntity<>(errorDTo,httpStatus);
    }




}
