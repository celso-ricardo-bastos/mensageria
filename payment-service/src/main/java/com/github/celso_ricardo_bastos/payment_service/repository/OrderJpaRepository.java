package com.github.celso_ricardo_bastos.payment_service.repository;

import com.github.celso_ricardo_bastos.payment_service.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, String> {
}