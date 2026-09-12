package com.github.celso_ricardo_bastos.payment_service.adapters.outbound.config;

import io.netty.channel.ChannelOption;
import reactor.netty.http.client.HttpClient;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableConfigurationProperties({
        WebClientTimeoutProperties.class,
        ExternalApiProperties.class
})
public class WebClientConfig {

    /**
     * Cria o WebClient que será compartilhado
     * pelos adapters da aplicação.
     */
    @Bean
    public WebClient webClient(
            WebClientTimeoutProperties properties
    ) {

        /*
         * HttpClient fornecido pelo Reactor Netty.
         *
         * É aqui que configuramos características
         * relacionadas à comunicação HTTP.
         */
        HttpClient httpClient = HttpClient.create()

                /*
                 * Tempo máximo para estabelecer
                 * uma conexão com o servidor.
                 */
                .option(
                        ChannelOption.CONNECT_TIMEOUT_MILLIS,
                        (int) properties.connection().toMillis()
                )

                /*
                 * Tempo máximo aguardando a resposta.
                 */
                .responseTimeout(properties.response());

        /*
         * O WebClient utilizará o HttpClient acima.
         */
        return WebClient.builder()
                .clientConnector(
                        new ReactorClientHttpConnector(httpClient)
                )
                .build();
    }
}