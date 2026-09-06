package com.github.celso_ricardo_bastos.order_service.controllers;

import com.github.celso_ricardo_bastos.order_service.controllers.dto.OrderRequest;
import com.github.celso_ricardo_bastos.order_service.controllers.dto.OrderResponse;
import com.github.celso_ricardo_bastos.order_service.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class Order {

    private final OrderService orderService;

    // Injeção de dependência via construtor (Boa prática)
    public Order(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request) {
        // Delega toda a lógica e publicação para a camada de Service
        OrderResponse response = orderService.processAndPublishOrder(request);

        // Retorna o JSON completo do pedido criado com o Status 201 (Created)
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
