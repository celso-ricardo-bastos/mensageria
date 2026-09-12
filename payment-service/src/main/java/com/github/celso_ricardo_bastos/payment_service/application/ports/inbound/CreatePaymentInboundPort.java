package com.github.celso_ricardo_bastos.payment_service.application.ports.inbound;

import com.github.celso_ricardo_bastos.payment_service.application.dto.CreatePaymentCommand;
import com.github.celso_ricardo_bastos.payment_service.dominio.model.Payment;
import reactor.core.publisher.Mono;

public interface CreatePaymentInboundPort {
    void executePayment(CreatePaymentCommand command);
}
