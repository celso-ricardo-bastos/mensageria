package com.github.celso_ricardo_bastos.order_service.application.ports.inbound;

import com.github.celso_ricardo_bastos.order_service.adapter.inbound.rest.dto.OrderRequest;
import com.github.celso_ricardo_bastos.order_service.adapter.inbound.rest.dto.OrderResponse;

public interface OrderInboundPort {
    OrderResponse publishOrder(OrderRequest order);
}
