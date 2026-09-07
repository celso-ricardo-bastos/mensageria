package com.github.celso_ricardo_bastos.payment_service.adapters.outbound.economia;

public record USDBRL(
        String code,
        String codein,
        String name,
        String high,
        String low,
        String varBid,
        String pctChange,
        String bid,
        String timestamp,
        String create_date
) {
}
