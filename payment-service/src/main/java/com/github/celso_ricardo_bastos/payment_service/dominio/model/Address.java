package com.github.celso_ricardo_bastos.payment_service.dominio.model;

public record Address(
        String cep,
        String street,
        String neighborhood,
        String city,
        String state
) {
}