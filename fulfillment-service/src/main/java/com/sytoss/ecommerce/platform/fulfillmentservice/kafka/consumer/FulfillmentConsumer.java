package com.sytoss.ecommerce.platform.fulfillmentservice.kafka.consumer;

import com.sytoss.ecommerce.platform.fulfillmentservice.model.order.Order;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@KafkaListener(topics = "${spring.kafka.topics.input-library}", groupId = "${spring.kafka.consumer.group-id}")
public class FulfillmentConsumer {
    @KafkaHandler
    public void listenGroupFulfillment(Order order) {
        System.out.println("Received message: " + order.toString());
    }
}
