package com.caioop.ixtore.controllers;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.caioop.ixtore.exceptions.DuplicatedRegistrationException;
import com.caioop.ixtore.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ApplicationControllerAdvice {


    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProblemDetail handleProductNotFoundException(ProductNotFoundException exception) {

        ProblemDetail errorDetail = null;
        errorDetail = ProblemDetail.forStatusAndDetail(
                HttpStatusCode.valueOf(404),
                exception.getMessage()
        );
        errorDetail.setProperty("reason", "Product key not found");

        return errorDetail;
    }

    @ExceptionHandler(DuplicatedRegistrationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleDuplicatedRegistrationException(DuplicatedRegistrationException exception) {

        ProblemDetail errorDetail = null;
        errorDetail = ProblemDetail.forStatusAndDetail(
                HttpStatusCode.valueOf(400),
                exception.getMessage()
        );
        errorDetail.setProperty("reason", "Duplicated Keys");

        return errorDetail;
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ProblemDetail handleUsernameNotFoundException(UsernameNotFoundException exception) {
        ProblemDetail errorDetail = null;
        errorDetail = ProblemDetail.forStatusAndDetail(
                HttpStatusCode.valueOf(401),
                exception.getMessage()
        );
        errorDetail.setProperty("reason", "Authorization Failure");

        return errorDetail;
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ProblemDetail handleBadCredentialsException(BadCredentialsException exception) {
        ProblemDetail errorDetail = null;
        errorDetail = ProblemDetail.forStatusAndDetail(
                HttpStatusCode.valueOf(401),
                exception.getMessage()
        );
        errorDetail.setProperty("reason", "Authorization Failure");

        return errorDetail;
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ProblemDetail handleAccessDeniedException(AccessDeniedException exception) {
        ProblemDetail errorDetail = null;
        errorDetail = ProblemDetail.forStatusAndDetail(
                HttpStatusCode.valueOf(403),
                exception.getMessage()
        );
        errorDetail.setProperty("reason", "Authorization Failure");

        return errorDetail;
    }

    @ExceptionHandler(JWTVerificationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ProblemDetail handleJWTVerificationException(JWTVerificationException exception) {
        ProblemDetail errorDetail = null;
        errorDetail = ProblemDetail.forStatusAndDetail(
                HttpStatusCode.valueOf(403),
                exception.getMessage()
        );
        errorDetail.setProperty("reason", "Authorization Failure");

        return errorDetail;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handelSecurityException(Exception exception) {
        ProblemDetail errorDetail = null;


        errorDetail = ProblemDetail.forStatusAndDetail(
                HttpStatusCode.valueOf(500), exception.getMessage()
        );
        errorDetail.setProperty("reason", "Server ERROR.");

        return errorDetail;
    }
}
