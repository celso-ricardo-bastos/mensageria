package com.github.celso_ricardo_bastos.payment_service.adapters.outbound.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Centraliza as URLs das APIs externas utilizadas pela aplicação.
 *
 * Os valores são carregados do application.yml.
 *
 * Isso evita deixar URLs externas espalhadas pelos adapters.
 */
@ConfigurationProperties(prefix = "external.api")
public record ExternalApiProperties(

        /**
         * Configurações da API ViaCEP.
         */
        ApiProperties viacep,

        /**
         * Configurações da API de cotação.
         */
        ApiProperties exchange

) {

    /**
     * Representa as configurações comuns de uma API externa.
     */
    public record ApiProperties(
            String baseUrl
    ) {
    }
}