package com.github.celso_ricardo_bastos.payment_service.application.ports.inbound;

import com.github.celso_ricardo_bastos.payment_service.application.dto.CreatePaymentCommand;

public interface CreatePaymentInboundPort {
    void createPayment(CreatePaymentCommand command);
}
