package com.sytoss.ecommerce.platform.orderservice.kafka.producer;

import com.sytoss.ecommerce.platform.orderservice.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderProducer {

    @Value(value = "${spring.kafka.topics.input-library}")
    private String topic;

    private final KafkaTemplate<String, Order> kafkaTemplate;

    public void sendOrder(Order order) {
        kafkaTemplate.send(topic, order);
    }
}
