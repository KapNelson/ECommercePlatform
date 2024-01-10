package com.sytoss.ecommerce.platform.fulfillmentservice.service;

import com.sytoss.ecommerce.platform.fulfillmentservice.model.inventory.Inventory;
import com.sytoss.ecommerce.platform.fulfillmentservice.model.order.Order;
import com.sytoss.ecommerce.platform.fulfillmentservice.model.order.OrderItem;
import com.sytoss.ecommerce.platform.fulfillmentservice.model.order.OrderStatus;
import com.sytoss.ecommerce.platform.fulfillmentservice.statemachine.event.OrderEvent;
import com.sytoss.ecommerce.platform.fulfillmentservice.statemachine.state.OrderState;
import org.springframework.statemachine.StateMachine;

import java.util.List;

/**
 * Service responsible for handling fulfillment processes for orders.
 */
public interface FulfillmentService {

    void processFulfillment(StateMachine<OrderState, OrderEvent> stateMachine, Order order);
    void updateOrderStatus(Order order, OrderStatus orderStatus, String statusReason);
    boolean allocateInventoryForOrderItem(OrderItem orderItem);
}
