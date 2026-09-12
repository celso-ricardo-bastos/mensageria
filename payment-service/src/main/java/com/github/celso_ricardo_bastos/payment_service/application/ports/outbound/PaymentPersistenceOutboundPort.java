package com.github.celso_ricardo_bastos.payment_service.application.ports.outbound;

import com.github.celso_ricardo_bastos.payment_service.dominio.model.Payment;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

public interface PaymentPersistenceOutboundPort {
    @Transactional
    void save(Mono<Payment> payment);
}
