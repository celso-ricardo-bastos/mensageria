package com.github.celso_ricardo_bastos.payment_service.adapters.outbound.persistence;

import com.github.celso_ricardo_bastos.payment_service.application.ports.outbound.PaymentPersistenceMongoOutboundPort;
import com.github.celso_ricardo_bastos.payment_service.application.ports.outbound.PaymentPersistenceOutboundPort;
import com.github.celso_ricardo_bastos.payment_service.application.ports.outbound.PaymentPersistencePostGresOutboundPort;
import com.github.celso_ricardo_bastos.payment_service.application.usecase.CreatePaymentUserCase;
import com.github.celso_ricardo_bastos.payment_service.dominio.model.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Component
public class PaymentPersistenceOutboundAdapter implements PaymentPersistenceOutboundPort {

    private final PaymentPersistencePostGresOutboundPort paymentPersistencePostGresOutboundPort;
    private final PaymentPersistenceMongoOutboundPort paymentPersistenceMongoOutboundPort;
    private static final Logger log = LoggerFactory.getLogger(CreatePaymentUserCase.class);

    public PaymentPersistenceOutboundAdapter(
            PaymentPersistencePostGresOutboundPort paymentPersistencePostGresOutboundPort,
            PaymentPersistenceMongoOutboundPort paymentPersistenceMongoOutboundPort) {

        this.paymentPersistencePostGresOutboundPort = paymentPersistencePostGresOutboundPort;
        this.paymentPersistenceMongoOutboundPort = paymentPersistenceMongoOutboundPort;
    }

    @Transactional
    @Override
    public void save(Mono<Payment> payment) {
        log.info("Save data [PostGres] [MongoDB]'");
        // PostgreSQL
        this.paymentPersistencePostGresOutboundPort.save(payment.block());
        // Mongo
        this.paymentPersistenceMongoOutboundPort.save(payment.block());
    }
}
