package com.github.celso_ricardo_bastos.payment_service.adapters.inbound.controllers;

import com.github.celso_ricardo_bastos.payment_service.orderservice.OrderConsumerService;
import com.github.celso_ricardo_bastos.payment_service.orderservice.dto.OrderProcessedEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/consumed-orders")
public class OrderConsumerController {

    private final OrderConsumerService consumerService;
    public OrderConsumerController(OrderConsumerService consumerService) {
        this.consumerService = consumerService;
    }
    @GetMapping
    public ResponseEntity<List<OrderProcessedEvent>> getOrders() {
        List<OrderProcessedEvent> orders = consumerService.getAllConsumedOrders();
        System.out.println("\n 📥 ====== [KAFKA CONSUMER -TESTE] ======");
        return ResponseEntity.ok(orders);
    }
}