package com.github.celso_ricardo_bastos.payment_service.adapters.outbound.persistence.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, String> {
}