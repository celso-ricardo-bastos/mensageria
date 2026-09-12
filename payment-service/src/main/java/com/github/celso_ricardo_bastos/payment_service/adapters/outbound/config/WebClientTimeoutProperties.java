package com.github.celso_ricardo_bastos.payment_service.adapters.outbound.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * Centraliza as configurações de timeout das chamadas externas.
 *
 * Os valores são obtidos do application.yml:
 *
 * external.api.timeout.connection
 * external.api.timeout.response
 */
@ConfigurationProperties(prefix = "external.api.timeout")
public record WebClientTimeoutProperties(

        /*
         * Tempo máximo para estabelecer uma conexão
         * com o servidor externo.
         */
        Duration connection,

        /*
         * Tempo máximo aguardando a resposta do servidor.
         */
        Duration response

) {
}
