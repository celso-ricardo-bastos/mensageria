package com.github.celso_ricardo_bastos.payment_service.orderservice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.celso_ricardo_bastos.payment_service.orderservice.dto.OrderProcessedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderConsumerService {

    private final List<OrderProcessedEvent> consumedOrders = new ArrayList<>();

    @KafkaListener(
            topics = "orders-topic",
            groupId = "payment-orders"
    )
    public void consume(String jsonNode) {
        try {

            ObjectMapper mapper = new ObjectMapper();
            OrderProcessedEvent event =
                    mapper.readValue(jsonNode, OrderProcessedEvent.class);

            System.out.println("\n 📥 ====== [KAFKA CONSUMER] ======");
            System.out.println("Novo pedido capturado via JsonNode!");
            System.out.println("ID do Pedido: " + event.orderId());
            System.out.println("Pedido: " + event);
            System.out.println("==================================\n");

            consumedOrders.add(event);
        } catch (Exception e) {
            System.out.println("Erro ao converter o JSON recebido: " + e.getMessage());
        }
    }

    public List<OrderProcessedEvent> getAllConsumedOrders() {
        return this.consumedOrders;
    }

//    @KafkaListener(
//            topics = "orders-topic",
//            groupId = "payment-orders"
//    )
//    public void consume(String json) {
//        System.out.println("\n🔥 ===== MENSAGEM RECEBIDA =====");
//        System.out.println("🔥 " + json);
//        System.out.println("================================\n");
//    }
}