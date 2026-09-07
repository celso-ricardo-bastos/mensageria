package com.github.celso_ricardo_bastos.payment_service.adapters.outbound.persistence.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentMongoRepository extends MongoRepository<PaymentMongoDocument, String> {
}