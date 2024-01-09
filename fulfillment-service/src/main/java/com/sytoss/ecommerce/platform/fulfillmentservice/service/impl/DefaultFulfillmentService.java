package com.sytoss.ecommerce.platform.fulfillmentservice.service.impl;

import com.sytoss.ecommerce.platform.fulfillmentservice.model.order.Order;
import com.sytoss.ecommerce.platform.fulfillmentservice.service.FulfillmentService;
import com.sytoss.ecommerce.platform.fulfillmentservice.statemachine.event.OrderEvent;
import com.sytoss.ecommerce.platform.fulfillmentservice.statemachine.state.OrderState;
import com.sytoss.ecommerce.platform.fulfillmentservice.statemachine.util.ContextConstant;
import lombok.AllArgsConstructor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DefaultFulfillmentService implements FulfillmentService {
    private final StateMachineFactory<OrderState, OrderEvent> stateMachineFactory;

    @Override
    public void startFulfillment(Order order) {
        StateMachine<OrderState, OrderEvent> stateMachine = stateMachineFactory.getStateMachine();

        stateMachine.sendEvent(
                MessageBuilder
                        .withPayload(OrderEvent.ALLOCATE_INVENTORY)
                        .setHeader(ContextConstant.ORDER_CONTEXT_HEADER, order)
                        .build());
        System.out.println("Current state: " + stateMachine.getState().getId());

        stateMachine.sendEvent(
                MessageBuilder
                        .withPayload(OrderEvent.CREATE_SHIPPING)
                        .setHeader(ContextConstant.ORDER_CONTEXT_HEADER, order)
                        .build());
        System.out.println("Current state: " + stateMachine.getState().getId());

        stateMachine.sendEvent(
                MessageBuilder
                        .withPayload(OrderEvent.SHIPPING_SUCCEED)
                        .setHeader(ContextConstant.ORDER_CONTEXT_HEADER, order)
                        .build());
        System.out.println("Current state: " + stateMachine.getState().getId());
    }
}
