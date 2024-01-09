package com.sytoss.ecommerce.platform.fulfillmentservice.statemachine.action;

import com.sytoss.ecommerce.platform.fulfillmentservice.model.order.Order;
import com.sytoss.ecommerce.platform.fulfillmentservice.statemachine.event.OrderEvent;
import com.sytoss.ecommerce.platform.fulfillmentservice.statemachine.state.OrderState;
import com.sytoss.ecommerce.platform.fulfillmentservice.statemachine.util.ContextConstant;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

public class ProcessingFailedAction implements Action<OrderState, OrderEvent> {

    @Override
    public void execute(StateContext<OrderState, OrderEvent> context) {
        Order order = (Order) context.getMessage().getHeaders().get(ContextConstant.ORDER_CONTEXT_HEADER);

        if (order != null) {
            System.out.println("Processing failed for order " + order.getUid());
        }
    }
}
