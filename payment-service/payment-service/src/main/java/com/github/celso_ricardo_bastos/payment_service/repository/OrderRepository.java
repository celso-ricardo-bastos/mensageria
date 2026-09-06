package com.github.celso_ricardo_bastos.payment_service.repository;

import com.github.celso_ricardo_bastos.payment_service.model.OrderDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<OrderDocument, String> {
}