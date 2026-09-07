package com.github.celso_ricardo_bastos.payment_service.dominio.exception;

public class InvalidPaymentException extends RuntimeException {
    public InvalidPaymentException(String message) {
        super(message);
    }
}
