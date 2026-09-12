package com.github.celso_ricardo_bastos.payment_service.adapters.outbound.economia;

import com.github.celso_ricardo_bastos.payment_service.adapters.outbound.viacep.AddressWebFluxClient;
import com.github.celso_ricardo_bastos.payment_service.adapters.outbound.viacep.ViaCepResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Adapter responsável pela comunicação com o ViaCEP.
 *
 * Ele implementará futuramente o nosso Outbound Port.
 *
 * Neste momento estamos focando apenas na comunicação HTTP.
 */
@Component
@RequiredArgsConstructor
public class EconomiaWebFluxClientAdapter {
    private final EconomiaWebFluxClient client;
    public Mono<EconomiaResponse> getData(String cep) {
        return client.findEconomia();
    }
}