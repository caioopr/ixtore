package com.caioop.ixtore.controllers;

import com.caioop.ixtore.exceptions.DuplicatedRegistrationException;
import com.caioop.ixtore.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleProductNotFoundException(ProductNotFoundException exception){
        return new ApiErrors(exception.getMessage());
    }

    @ExceptionHandler(DuplicatedRegistrationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleDuplicatedRegistrationException(DuplicatedRegistrationException exception){
        return new ApiErrors(exception.getMessage());
    }
}
