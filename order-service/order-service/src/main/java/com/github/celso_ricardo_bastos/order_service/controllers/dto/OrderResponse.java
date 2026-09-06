package com.github.celso_ricardo_bastos.order_service.controllers.dto;

import java.time.LocalDateTime;

public record OrderResponse(
        String orderId,
        String customerId,
        Double totalAmount,
        String status,
        String createdAt
) {}