package com.sytoss.ecommerce.platform.notificationservice.kafka.consumer;

import com.sytoss.ecommerce.platform.notificationservice.model.order.Order;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@KafkaListener(topics = "${spring.kafka.topics.input-library}", groupId = "${spring.kafka.consumer.group-id}")
public class NotificationConsumer {

    @KafkaHandler
    public void receiveOrder(Order order) {
        System.out.println("Thank you, your order has been successfully created! Your order number: " + order.getUid());
    }
}
