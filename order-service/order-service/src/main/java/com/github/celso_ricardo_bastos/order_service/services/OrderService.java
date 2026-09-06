package com.github.celso_ricardo_bastos.order_service.services;

import com.github.celso_ricardo_bastos.order_service.controllers.dto.OrderRequest;
import com.github.celso_ricardo_bastos.order_service.controllers.dto.OrderResponse;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OrderService {

    // O Spring gerencia o KafkaTemplate configurado no seu application.yml
    private final KafkaTemplate<String, OrderResponse> kafkaTemplate;

    public OrderService(KafkaTemplate<String, OrderResponse> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public OrderResponse processAndPublishOrder(OrderRequest request) {
        // 1. Lógica de negócio: Cria os dados internos do Pedido
        String orderId = UUID.randomUUID().toString();
        String statusInicial = "PENDING";
        LocalDateTime dataCriacao = LocalDateTime.now();

        // 2. Monta o DTO de Saída com o estado completo do pedido
        OrderResponse response = new OrderResponse(
                orderId,
                request.customerId(),
                request.totalAmount(),
                statusInicial,
                dataCriacao.toString()
        );

        // 3. Publica o objeto direto no tópico do Kafka
        // Usamos o orderId como chave de partição para manter a ordem cronológica do pedido
        kafkaTemplate.send("orders-topic", orderId, response);

        System.out.println("====== [KAFKA PRODUCER] ======");
        System.out.println("Pedido enviado com sucesso para o tópico 'orders-topic'!");
        System.out.println("ID do Pedido: " + orderId);
        System.out.println("==============================");

        // 4. Retorna o objeto processado para que o Controller responda ao cliente
        return response;
    }
}