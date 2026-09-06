package com.github.celso_ricardo_bastos.order_service.application.mapper;

import com.github.celso_ricardo_bastos.order_service.adapter.inbound.rest.dto.OrderResponse;
import com.github.celso_ricardo_bastos.order_service.dominio.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "createdAt", expression = "java(order.getCreatedAt() != null ? order.getCreatedAt().toString() : null)")
    OrderResponse toResponse(Order order);
}