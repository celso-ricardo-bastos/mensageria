package com.github.celso_ricardo_bastos.payment_service.adapters.outbound.economia;

import com.github.celso_ricardo_bastos.payment_service.application.ports.outbound.EconomiaOutboundPort;
import com.github.celso_ricardo_bastos.payment_service.dominio.model.Economia;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class EconomiaAdapter implements EconomiaOutboundPort {

    private final EconomiaClient economiaClient;

    public EconomiaAdapter(EconomiaClient economiaClient) {
        this.economiaClient = economiaClient;
    }

    @Override
    public Economia buscaEconomia() {
        EconomiaResponse response = economiaClient.buscarEconomia();

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        USDBRL usdbrl =  new USDBRL(
                response.USDBRL().code(),
                response.USDBRL().codein(),
                response.USDBRL().name(),
                response.USDBRL().high(),
                response.USDBRL().low(),
                response.USDBRL().varBid(),
                response.USDBRL().pctChange(),
                response.USDBRL().bid(),
                response.USDBRL().timestamp(),
                response.USDBRL().create_date()
        );
        return new Economia(
                usdbrl.bid(),
                usdbrl.timestamp(),
                LocalDateTime.parse(usdbrl.create_date(), formatter)
        );
    }
}