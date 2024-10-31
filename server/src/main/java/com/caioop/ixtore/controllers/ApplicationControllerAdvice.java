package com.caioop.ixtore.controllers;

import com.caioop.ixtore.exceptions.DuplicatedRegistrationException;
import com.caioop.ixtore.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ProblemDetail handelSecurityException(Exception exception){
        ProblemDetail errorDetail = null;
        if(exception instanceof BadCredentialsException){
            errorDetail = ProblemDetail.forStatusAndDetail(
                    HttpStatusCode.valueOf(401), exception.getMessage()
            );
            errorDetail.setProperty("reason", "Authentication Failure");
        }
        if(exception instanceof AccessDeniedException){
            errorDetail = ProblemDetail.forStatusAndDetail(
                    HttpStatusCode.valueOf(403), exception.getMessage()
            );
            errorDetail.setProperty("reason", "Authorization Failure");
        }

        return errorDetail;
    }

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
