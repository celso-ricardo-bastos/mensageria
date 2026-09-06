package com.github.celso_ricardo_bastos.order_service.application.mapper;

import com.github.celso_ricardo_bastos.order_service.adapter.inbound.rest.dto.OrderResponse;
import com.github.celso_ricardo_bastos.order_service.dominio.Order;
import com.github.celso_ricardo_bastos.order_service.dominio.OrderStatus;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-06T05:19:06-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.9 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderResponse toResponse(Order order) {
        if ( order == null ) {
            return null;
        }

        String orderId = null;
        String customerId = null;
        BigDecimal totalAmount = null;
        OrderStatus status = null;

        orderId = order.getOrderId();
        customerId = order.getCustomerId();
        totalAmount = order.getTotalAmount();
        status = order.getStatus();

        String createdAt = order.getCreatedAt() != null ? order.getCreatedAt().toString() : null;

        OrderResponse orderResponse = new OrderResponse( orderId, customerId, totalAmount, status, createdAt );

        return orderResponse;
    }
}
