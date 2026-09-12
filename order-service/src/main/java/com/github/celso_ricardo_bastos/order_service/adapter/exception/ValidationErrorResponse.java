package com.github.celso_ricardo_bastos.order_service.adapter.exception;

import java.util.List;

public record ValidationErrorResponse(
        String message,
        List<FieldErrorResponse> errors
) {}