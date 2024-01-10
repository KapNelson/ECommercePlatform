package com.sytoss.ecommerce.platform.fulfillmentservice.kafka.consumer;

import com.sytoss.ecommerce.platform.fulfillmentservice.model.order.Order;
import com.sytoss.ecommerce.platform.fulfillmentservice.service.FulfillmentService;
import com.sytoss.ecommerce.platform.fulfillmentservice.statemachine.event.OrderEvent;
import com.sytoss.ecommerce.platform.fulfillmentservice.statemachine.state.OrderState;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@KafkaListener(topics = "${spring.kafka.topics.input-library}", groupId = "${spring.kafka.consumer.group-id}")
public class FulfillmentConsumer {

    private final StateMachineFactory<OrderState, OrderEvent> stateMachineFactory;
    private final FulfillmentService fulfillmentService;

    @KafkaHandler
    public void receiveOrder(Order order) {
        System.out.println("Received message: " + order.toString());
        StateMachine<OrderState, OrderEvent> stateMachine = stateMachineFactory.getStateMachine();
        fulfillmentService.processFulfillment(stateMachine, order);
    }
}
