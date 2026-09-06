package com.github.celso_ricardo_bastos.order_service.application.ports.outbound;

import com.github.celso_ricardo_bastos.order_service.dominio.Order;
import com.github.celso_ricardo_bastos.order_service.dominio.event.OrderCreatedEvent;

public interface OrderOutboundPort {
    void publishOrder(OrderCreatedEvent orderCreatedEvent);
}
