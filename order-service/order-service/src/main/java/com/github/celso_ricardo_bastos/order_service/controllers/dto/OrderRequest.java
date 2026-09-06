package com.github.celso_ricardo_bastos.order_service.controllers.dto;
public record OrderRequest(
        String customerId,
        Double totalAmount
) {}