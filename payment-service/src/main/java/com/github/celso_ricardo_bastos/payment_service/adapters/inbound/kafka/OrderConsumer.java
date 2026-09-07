package com.github.celso_ricardo_bastos.payment_service.adapters.inbound.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.celso_ricardo_bastos.payment_service.application.dto.CreatePaymentCommand;
import com.github.celso_ricardo_bastos.payment_service.application.ports.inbound.CreatePaymentInboundPort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer{
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
        System.out.println("\n 📥 ====== [KAFKA CONSUMER - Inbound] ======");
        System.out.println("Novo pedido capturado via JsonNode!");
        System.out.println("Pedido: " + jsonNode);
        System.out.println("==================================\n");
        try {
            CreatePaymentCommand command =
                    objectMapper.readValue(
                            jsonNode,
                            CreatePaymentCommand.class
                    );
            paymentInboundPort.createPayment(command);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter o JSON recebido: " + e.getMessage());
        }

    }
}