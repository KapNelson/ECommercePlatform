package com.sytoss.ecommerce.platform.fulfillmentservice.statemachine.action;

import com.sytoss.ecommerce.platform.fulfillmentservice.model.order.Order;
import com.sytoss.ecommerce.platform.fulfillmentservice.model.order.OrderStatus;
import com.sytoss.ecommerce.platform.fulfillmentservice.service.FulfillmentService;
import com.sytoss.ecommerce.platform.fulfillmentservice.statemachine.event.OrderEvent;
import com.sytoss.ecommerce.platform.fulfillmentservice.statemachine.state.OrderState;
import com.sytoss.ecommerce.platform.fulfillmentservice.statemachine.util.ContextConstant;
import lombok.AllArgsConstructor;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ShippingSucceedAction implements Action<OrderState, OrderEvent> {
    private final FulfillmentService fulfillmentService;

    @Override
    public void execute(StateContext<OrderState, OrderEvent> context) {
        Order order = (Order) context.getMessage().getHeaders().get(ContextConstant.ORDER_CONTEXT_HEADER);

        if (order != null) {
            System.out.println("Shipping succeed for order " + order.getUid());
        }

        fulfillmentService.updateOrderStatus(order, OrderStatus.DONE, "The order has been successfully delivered");
    }
}
