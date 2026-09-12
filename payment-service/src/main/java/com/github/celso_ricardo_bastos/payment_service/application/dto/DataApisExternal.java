package com.github.celso_ricardo_bastos.payment_service.application.dto;

import com.github.celso_ricardo_bastos.payment_service.dominio.model.Address;
import com.github.celso_ricardo_bastos.payment_service.dominio.model.Economia;

public record DataApisExternal(
        Address address,
        Economia economia
) {
}
