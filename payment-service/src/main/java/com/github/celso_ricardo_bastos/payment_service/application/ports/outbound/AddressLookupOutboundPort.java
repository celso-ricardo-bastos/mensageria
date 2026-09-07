package com.github.celso_ricardo_bastos.payment_service.application.ports.outbound;

import com.github.celso_ricardo_bastos.payment_service.dominio.model.Address;

public interface AddressLookupOutboundPort {
    Address buscarCep(String cep);
}