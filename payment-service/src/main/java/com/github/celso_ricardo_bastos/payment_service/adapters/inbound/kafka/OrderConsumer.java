package com.github.celso_ricardo_bastos.payment_service.adapters.inbound.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.celso_ricardo_bastos.payment_service.application.dto.CreatePaymentCommand;
import com.github.celso_ricardo_bastos.payment_service.application.ports.inbound.CreatePaymentInboundPort;
import com.github.celso_ricardo_bastos.payment_service.application.usecase.CreatePaymentUserCase;
import com.github.celso_ricardo_bastos.payment_service.dominio.model.Payment;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;

@Component
public class OrderConsumer{
    private static final Logger log = LoggerFactory.getLogger(CreatePaymentUserCase.class);
    private final CreatePaymentInboundPort paymentInboundPort;
    private final ObjectMapper objectMapper;
    public OrderConsumer(CreatePaymentInboundPort paymentInboundPort, ObjectMapper objectMapper) {
        this.paymentInboundPort = paymentInboundPort;
        this.objectMapper = objectMapper;
    }
    @KafkaListener(
            topics = "orders-topic",
            groupId = "payment-orders"
    )
    public void consumer(String jsonNode) {
        log.info("\n 📥 ====== [KAFKA CONSUMER - Inbound] ======");
        log.info("\n 📥 Novo pedido capturado via JsonNode!");
        log.info("Order listen: {}", jsonNode);
        log.info("==================================\n");
        try {
            CreatePaymentCommand command =
                    objectMapper.readValue(
                            jsonNode,
                            CreatePaymentCommand.class
                    );
            paymentInboundPort.executePayment(command);
        } catch (Exception e) {
            log.info("Erro ao tentar consumir dados da fila do Kafka: {}", e);
            throw new RuntimeException("Erro ao converter o JSON recebido: " + e.getMessage());
        }

    }
}