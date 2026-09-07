package com.github.celso_ricardo_bastos.payment_service.application.ports.outbound;

import com.github.celso_ricardo_bastos.payment_service.dominio.model.Economia;

public interface EconomiaOutboundPort {
    Economia buscaEconomia();
}
