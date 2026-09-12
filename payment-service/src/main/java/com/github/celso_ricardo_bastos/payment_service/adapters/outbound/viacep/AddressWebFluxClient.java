package com.github.celso_ricardo_bastos.payment_service.adapters.outbound.viacep;

import com.github.celso_ricardo_bastos.payment_service.adapters.outbound.config.ExternalApiProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
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
public class AddressWebFluxClient {

    private final WebClient webClient;

    private final ExternalApiProperties properties;

    public Mono<ViaCepResponse> findByCep(String cep) {

        return webClient
                .get()

                /*
                 * Utilizamos a URL configurada no application.yml.
                 *
                 * properties.viacep().baseUrl()
                 *
                 * retorna:
                 *
                 * https://viacep.com.br
                 */
                .uri(
                        properties.viacep().baseUrl()
                                + "/ws/{cep}/json/",
                        cep
                )

                /*
                 * Executa a requisição.
                 */
                .retrieve()

                /*
                 * Converte o JSON para nosso DTO.
                 */
                .bodyToMono(ViaCepResponse.class);
    }
}