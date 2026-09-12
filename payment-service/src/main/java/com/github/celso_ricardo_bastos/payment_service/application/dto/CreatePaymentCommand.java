package com.github.celso_ricardo_bastos.payment_service.application.dto;

import com.github.celso_ricardo_bastos.payment_service.dominio.model.PaymentStatus;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreatePaymentCommand(
        String orderId,
        String customerId,
        BigDecimal totalAmount,
        PaymentStatus status,
        LocalDateTime createdAt,
        String cep
) {}