package com.github.celso_ricardo_bastos.order_service.adapter.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidation(
            MethodArgumentNotValidException exception) {

        List<FieldErrorResponse> errors =
                exception.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(error -> new FieldErrorResponse(
                                error.getField(),
                                error.getDefaultMessage()
                        ))
                        .toList();

        ValidationErrorResponse response =
                new ValidationErrorResponse(
                        "Erro de validação",
                        errors
                );

        return ResponseEntity
                .badRequest()
                .body(response);
    }
}