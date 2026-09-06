package com.github.celso_ricardo_bastos.order_service.adapter.outbound;

import com.github.celso_ricardo_bastos.order_service.dominio.event.OrderCreatedEvent;
import com.github.celso_ricardo_bastos.order_service.dominio.Order;
import com.github.celso_ricardo_bastos.order_service.application.ports.outbound.OrderOutboundPort;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class OrderPublishKafka implements OrderOutboundPort {

    // O Spring gerencia o KafkaTemplate configurado no seu application.yml
    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;
    private static final Logger log =
            LoggerFactory.getLogger(OrderPublishKafka.class);

    public OrderPublishKafka(KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publishOrder(OrderCreatedEvent orderEvent) {

        // Usamos o orderId como chave de partição para manter a ordem cronológica do pedido
        kafkaTemplate
                .send("orders-topic", orderEvent.orderId(), orderEvent)
                .whenComplete((result, exception) -> {

                    if (exception != null) {
                        log.error(
                                "Erro ao publicar pedido no Kafka. orderId={}",
                                orderEvent.orderId()
                        );
                        return;
                    }

                    log.info(
                            "Pedido publicado no Kafka. orderId={}, partition={}, offset={}",
                            orderEvent.orderId(),
                            result.getRecordMetadata().partition(),
                            result.getRecordMetadata().offset()
                    );
                });
    }
}
