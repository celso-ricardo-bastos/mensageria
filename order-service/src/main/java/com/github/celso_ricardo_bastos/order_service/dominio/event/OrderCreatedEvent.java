package com.github.celso_ricardo_bastos.order_service.dominio.event;

import com.github.celso_ricardo_bastos.order_service.dominio.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderCreatedEvent(
        String orderId,
        String customerId,
        BigDecimal totalAmount,
        OrderStatus status,
        String createdAt
) {}