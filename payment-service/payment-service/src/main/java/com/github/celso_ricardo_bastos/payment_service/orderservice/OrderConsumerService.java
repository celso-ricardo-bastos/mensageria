package com.github.celso_ricardo_bastos.payment_service.orderservice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.celso_ricardo_bastos.payment_service.model.OrderDocument;
import com.github.celso_ricardo_bastos.payment_service.model.OrderEntity;
import com.github.celso_ricardo_bastos.payment_service.orderservice.dto.OrderProcessedEvent;
import com.github.celso_ricardo_bastos.payment_service.repository.OrderJpaRepository;
import com.github.celso_ricardo_bastos.payment_service.repository.OrderRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderConsumerService {

    private final List<OrderProcessedEvent> consumedOrders = new ArrayList<>();
    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper;
    private final OrderJpaRepository orderJpaRepository;


    public OrderConsumerService(OrderRepository orderRepository, ObjectMapper objectMapper, OrderJpaRepository orderJpaRepository) {
        this.orderRepository = orderRepository;
        this.objectMapper = objectMapper;
        this.orderJpaRepository = orderJpaRepository;
    }

    @KafkaListener(
            topics = "orders-topic",
            groupId = "payment-orders"
    )
    public void consumer(String jsonNode) {
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

            BigDecimal totalAmount = BigDecimal.valueOf(event.totalAmount());

            OrderDocument order = OrderDocument.builder()
                    .orderId(event.orderId())
                    .customerId(event.customerId())
                    .totalAmount(totalAmount)
                    .status(event.status())
                    .createdAt(LocalDateTime.parse(event.createdAt()))
                    .build();

            orderRepository.save(order);

            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setOrderId(event.orderId());
            orderEntity.setCustomerId(event.customerId());
            orderEntity.setTotalAmount(totalAmount);
            orderEntity.setStatus(event.status());
            orderEntity.setCreatedAt(
                    LocalDateTime.parse(event.createdAt())
            );

            orderJpaRepository.save(orderEntity);

        } catch (Exception e) {
            System.out.println("Erro ao converter o JSON recebido: " + e.getMessage());
        }
    }

    public List<OrderProcessedEvent> getAllConsumedOrders() {
        return this.consumedOrders;
    }
}