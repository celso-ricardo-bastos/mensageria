package com.github.celso_ricardo_bastos.payment_service.adapters.outbound.persistence;

import com.github.celso_ricardo_bastos.payment_service.application.ports.outbound.PaymentPersistenceMongoOutboundPort;
import com.github.celso_ricardo_bastos.payment_service.application.ports.outbound.PaymentPersistenceOutboundPort;
import com.github.celso_ricardo_bastos.payment_service.application.ports.outbound.PaymentPersistencePostGresOutboundPort;
import com.github.celso_ricardo_bastos.payment_service.dominio.model.Payment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PaymentPersistenceOutboundAdapter implements PaymentPersistenceOutboundPort {

    private final PaymentPersistencePostGresOutboundPort paymentPersistencePostGresOutboundPort;
    private final PaymentPersistenceMongoOutboundPort paymentPersistenceMongoOutboundPort;

    public PaymentPersistenceOutboundAdapter(
            PaymentPersistencePostGresOutboundPort paymentPersistencePostGresOutboundPort,
            PaymentPersistenceMongoOutboundPort paymentPersistenceMongoOutboundPort) {

        this.paymentPersistencePostGresOutboundPort = paymentPersistencePostGresOutboundPort;
        this.paymentPersistenceMongoOutboundPort = paymentPersistenceMongoOutboundPort;
    }

    @Transactional
    @Override
    public void save(Payment payment) {
        // PostgreSQL
//        this.paymentPersistencePostGresOutboundPort.save(payment);
        // Mongo
        this.paymentPersistenceMongoOutboundPort.save(payment);
    }
}
