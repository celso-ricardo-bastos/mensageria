package com.github.celso_ricardo_bastos.payment_service.adapters.outbound.persistence.mongo;

import com.github.celso_ricardo_bastos.payment_service.dominio.model.Address;
import com.github.celso_ricardo_bastos.payment_service.dominio.model.Economia;
import com.github.celso_ricardo_bastos.payment_service.dominio.model.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "payments")
public class PaymentMongoDocument {

    @Id
    private String orderId;

    private String customerId;

    private BigDecimal totalAmount;

    private PaymentStatus status;

    private LocalDateTime createdAt;

    private Address address;

    private Economia economia;

}