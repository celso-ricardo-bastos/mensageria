package com.github.celso_ricardo_bastos.payment_service.application.usecase;

import com.github.celso_ricardo_bastos.payment_service.application.ports.outbound.AddressLookupOutboundPort;
import com.github.celso_ricardo_bastos.payment_service.application.ports.outbound.CreatePaymentOutboundPort;
import com.github.celso_ricardo_bastos.payment_service.application.ports.outbound.EconomiaOutboundPort;
import com.github.celso_ricardo_bastos.payment_service.application.ports.outbound.PaymentPersistenceOutboundPort;
import com.github.celso_ricardo_bastos.payment_service.application.dto.CreatePaymentCommand;
import com.github.celso_ricardo_bastos.payment_service.application.ports.inbound.CreatePaymentInboundPort;

import com.github.celso_ricardo_bastos.payment_service.dominio.model.Payment;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import org.slf4j.Logger;
import reactor.core.publisher.Mono;

@Service
public class CreatePaymentUserCase implements CreatePaymentInboundPort {
    private static final Logger log = LoggerFactory.getLogger(CreatePaymentUserCase.class);
    private final PaymentPersistenceOutboundPort paymentPersistencePort;
    private final AddressLookupOutboundPort addressLookupOutboundPort;
    private final EconomiaOutboundPort economiaOutboundPort;
    private final ExecutorService executor;
    private final CreatePaymentOutboundPort createPaymentOutboundPort;

    public CreatePaymentUserCase(
            PaymentPersistenceOutboundPort paymentPersistenceOutboundPort,
            AddressLookupOutboundPort addressLookupOutboundPort,
            EconomiaOutboundPort economiaOutboundPort,
            ExecutorService executor,
            CreatePaymentOutboundPort createPaymentOutboundPort
            ) {
        this.paymentPersistencePort = paymentPersistenceOutboundPort;
        this.addressLookupOutboundPort = addressLookupOutboundPort;
        this.economiaOutboundPort = economiaOutboundPort;
        this.executor = executor;
        this.createPaymentOutboundPort = createPaymentOutboundPort;
    }

    @Override
    public void executePayment(CreatePaymentCommand command) {
        log.info("Create payment");
        Mono<Payment> payment = this.createPaymentOutboundPort.createPayment(command);

        log.info("Save payment");
        this.paymentPersistencePort.save(payment);
    }
}