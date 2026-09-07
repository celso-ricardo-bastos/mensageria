package com.github.celso_ricardo_bastos.payment_service.dominio.model;

import java.time.LocalDateTime;

public record Economia(
   String bid,
   String timestamp,
   LocalDateTime create_date
) {
}
