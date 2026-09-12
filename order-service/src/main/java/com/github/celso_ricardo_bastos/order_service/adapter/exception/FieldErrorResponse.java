package com.github.celso_ricardo_bastos.order_service.adapter.exception;

public record FieldErrorResponse(
        String field,
        String message
) {}