package com.github.celso_ricardo_bastos.payment_service.adapters.outbound.persistence.postgres;

import com.github.celso_ricardo_bastos.payment_service.dominio.model.Payment;
import com.github.celso_ricardo_bastos.payment_service.application.ports.outbound.PaymentPersistencePostGresOutboundPort;
import org.springframework.stereotype.Component;

@Component
public class PaymentPostgresAdapter implements PaymentPersistencePostGresOutboundPort {
    private String jsonNode;
    private final OrderJpaRepository orderJpaRepository;

    public PaymentPostgresAdapter(OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;

    }

    @Override
    public void save(Payment payment) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(payment.getOrderId());
        orderEntity.setCustomerId(payment.getCustomerId());
        orderEntity.setTotalAmount(payment.getTotalAmount());
        orderEntity.setStatus(String.valueOf(payment.getStatus()));
        orderEntity.setCreatedAt(payment.getCreatedAt());
        orderJpaRepository.save(orderEntity);
    }

}

