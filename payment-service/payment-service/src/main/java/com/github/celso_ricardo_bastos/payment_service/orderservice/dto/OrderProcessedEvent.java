package com.github.celso_ricardo_bastos.payment_service.orderservice.dto;

import java.time.LocalDateTime;

public record OrderProcessedEvent(
        String orderId,
        String customerId,
        Double totalAmount,
        String status,
        String createdAt
) {}