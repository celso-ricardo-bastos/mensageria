package com.github.celso_ricardo_bastos.payment_service.application.ports.outbound;

import com.github.celso_ricardo_bastos.payment_service.dominio.model.Payment;

public interface PaymentPersistenceOutboundPort {
    void save(Payment payment);
}
