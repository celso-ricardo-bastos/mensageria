package com.github.celso_ricardo_bastos.payment_service.adapters.outbound.persistence.mongo;

import com.github.celso_ricardo_bastos.payment_service.application.ports.outbound.PaymentPersistenceMongoOutboundPort;
import com.github.celso_ricardo_bastos.payment_service.dominio.model.Payment;
import com.github.celso_ricardo_bastos.payment_service.dominio.model.PaymentStatus;
import org.springframework.stereotype.Component;

@Component
public class PaymentMongoAdapter implements PaymentPersistenceMongoOutboundPort {
    private String jsonNode;
    private final PaymentMongoRepository orderRepository;

    public PaymentMongoAdapter(PaymentMongoRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void save(Payment payment) {
        PaymentMongoDocument order = PaymentMongoDocument.builder()
                .orderId(payment.getOrderId())
                .customerId(payment.getCustomerId())
                .totalAmount(payment.getTotalAmount())
                .status(PaymentStatus.valueOf(String.valueOf(payment.getStatus())))
                .createdAt(payment.getCreatedAt())
                .address(payment.getAddress())
                .economia(payment.getEconomia())
                .build();
        this.orderRepository.save(order);
    }
}

