package com.github.celso_ricardo_bastos.payment_service.dominio.model;

import com.github.celso_ricardo_bastos.payment_service.dominio.exception.InvalidPaymentException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {
    private String orderId;
    private String customerId;
    private BigDecimal totalAmount;
    private PaymentStatus status;
    private LocalDateTime createdAt;
    private Address address;
    private Economia economia;

    private Payment(
            String orderId,
            String customerId,
            BigDecimal totalAmount,
            PaymentStatus status,
            LocalDateTime createdAt,
            Address address,
            Economia economia
    ) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createdAt = createdAt;
        this.address = address;
        this.economia = economia;
    }

    public static Payment create(
            String orderId,
            String customerId,
            BigDecimal totalAmount,
            PaymentStatus status,
            LocalDateTime createdAt,
            Address address,
            Economia economia) {

        if (totalAmount == null ||
                totalAmount.compareTo(BigDecimal.ZERO) <= 0) {

            throw new InvalidPaymentException(
                    "Valor do pagamento deve ser maior que zero"
            );
        }

        return new Payment(
                orderId,
                customerId,
                totalAmount,
                status,
                createdAt,
                address,
                economia
        );
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Address getAddress() {
        return address;
    }

    public Economia getEconomia() {
        return economia;
    }
}