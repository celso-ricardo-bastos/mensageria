package com.github.celso_ricardo_bastos.payment_service.application.ports.outbound;

import com.github.celso_ricardo_bastos.payment_service.application.dto.CreatePaymentCommand;
import com.github.celso_ricardo_bastos.payment_service.application.dto.DataApisExternal;
import com.github.celso_ricardo_bastos.payment_service.dominio.model.Payment;
import reactor.core.publisher.Mono;

public interface CreatePaymentOutboundPort {
    Mono<DataApisExternal> execute(String cep);
    Mono<Payment> createPayment(CreatePaymentCommand command);
}
