package com.github.celso_ricardo_bastos.payment_service.adapters.outbound.economia;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "EconomiaClient",
        url = "https://economia.awesomeapi.com.br"
)
public interface EconomiaClient {
    @GetMapping("/json/last/USD-BRL")
    EconomiaResponse buscarEconomia();
}