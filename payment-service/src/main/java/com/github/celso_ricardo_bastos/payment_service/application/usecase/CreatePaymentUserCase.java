package com.github.celso_ricardo_bastos.payment_service.application.usecase;

import com.github.celso_ricardo_bastos.payment_service.application.ports.outbound.AddressLookupOutboundPort;
import com.github.celso_ricardo_bastos.payment_service.application.ports.outbound.EconomiaOutboundPort;
import com.github.celso_ricardo_bastos.payment_service.application.ports.outbound.PaymentPersistenceOutboundPort;
import com.github.celso_ricardo_bastos.payment_service.dominio.model.Address;
import com.github.celso_ricardo_bastos.payment_service.dominio.model.Economia;
import com.github.celso_ricardo_bastos.payment_service.dominio.model.Payment;
import com.github.celso_ricardo_bastos.payment_service.application.dto.CreatePaymentCommand;
import com.github.celso_ricardo_bastos.payment_service.application.ports.inbound.CreatePaymentInboundPort;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Service
public class CreatePaymentUserCase implements CreatePaymentInboundPort {
    private final PaymentPersistenceOutboundPort paymentPersistencePort;
    private final AddressLookupOutboundPort addressLookupOutboundPort;
    private final EconomiaOutboundPort economiaOutboundPort;
    private final ExecutorService executor;

    public CreatePaymentUserCase(
            PaymentPersistenceOutboundPort paymentPersistenceOutboundPort,
            AddressLookupOutboundPort addressLookupOutboundPort,
            EconomiaOutboundPort economiaOutboundPort,
            ExecutorService executor
            ) {
        this.paymentPersistencePort = paymentPersistenceOutboundPort;
        this.addressLookupOutboundPort = addressLookupOutboundPort;
        this.economiaOutboundPort = economiaOutboundPort;
        this.executor = executor;
    }

    @Override
    public void createPayment(CreatePaymentCommand command) {
        CompletableFuture<Address> addressFuture =
                CompletableFuture.supplyAsync(
                        // API https://viacep.com.br/ws/04849270/json/
                        () -> this.addressLookupOutboundPort.buscarCep(command.cep()),
                        executor
                );

        CompletableFuture<Economia> economiaFuture =
                CompletableFuture.supplyAsync(
                        // API https://economia.awesomeapi.com.br/json/last/USD-BRL
                        () -> this.economiaOutboundPort.buscaEconomia(),
                        executor
                );

        CompletableFuture.allOf(
                addressFuture,
                economiaFuture
        ).join();

        Address address = addressFuture.join();
        Economia economia = economiaFuture.join();

        // Object to save
        Payment payment = Payment.create(
                command.orderId(),
                command.customerId(),
                command.totalAmount(),
                command.status(),
                command.createdAt(),
                address,
                economia
        );

        // Save in Mongo and Postgres
        this.paymentPersistencePort.save(payment);
    }
}