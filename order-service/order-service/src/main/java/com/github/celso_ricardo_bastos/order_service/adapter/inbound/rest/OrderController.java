package com.github.celso_ricardo_bastos.order_service.adapter.inbound.rest;

import com.github.celso_ricardo_bastos.order_service.adapter.inbound.rest.dto.OrderRequest;
import com.github.celso_ricardo_bastos.order_service.adapter.inbound.rest.dto.OrderResponse;
import com.github.celso_ricardo_bastos.order_service.application.ports.inbound.OrderInboundPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderInboundPort orderInboundPort;

    public OrderController(OrderInboundPort orderInboundPort) {
        this.orderInboundPort = orderInboundPort;
    }
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @Valid @RequestBody OrderRequest request) {
        // Delega toda a lógica e publicação para a camada de Service
        OrderResponse response = orderInboundPort.publishOrder(request);

        // Retorna o JSON completo do pedido criado com o Status 201 (Created)
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
