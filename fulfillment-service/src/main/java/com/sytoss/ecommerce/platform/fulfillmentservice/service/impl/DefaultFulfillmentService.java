package com.sytoss.ecommerce.platform.fulfillmentservice.service.impl;

import com.sytoss.ecommerce.platform.fulfillmentservice.feign.client.InventoryFeignClient;
import com.sytoss.ecommerce.platform.fulfillmentservice.feign.client.OrderFeignClient;
import com.sytoss.ecommerce.platform.fulfillmentservice.model.inventory.Inventory;
import com.sytoss.ecommerce.platform.fulfillmentservice.model.order.Order;
import com.sytoss.ecommerce.platform.fulfillmentservice.model.order.OrderItem;
import com.sytoss.ecommerce.platform.fulfillmentservice.model.order.OrderStatus;
import com.sytoss.ecommerce.platform.fulfillmentservice.service.FulfillmentService;
import com.sytoss.ecommerce.platform.fulfillmentservice.statemachine.event.OrderEvent;
import com.sytoss.ecommerce.platform.fulfillmentservice.statemachine.state.OrderState;
import com.sytoss.ecommerce.platform.fulfillmentservice.statemachine.util.ContextConstant;
import lombok.AllArgsConstructor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DefaultFulfillmentService implements FulfillmentService {
    private final InventoryFeignClient inventoryFeignClient;
    private final OrderFeignClient orderFeignClient;

    @Override
    public void processFulfillment(StateMachine<OrderState, OrderEvent> stateMachine, Order order) {
        if (order == null) {
            return;
        }

        while (!order.getStatus().equals(OrderStatus.DONE)) {
            sendEventForOrder(stateMachine, order, getOrderEvent(order.getStatus()));

            if (order.getStatus().equals(OrderStatus.FAILED)) {
                sendEventForOrder(stateMachine, order, OrderEvent.PROCESSING_FAILED);
                return;
            }
        }
    }

    @Override
    public void updateOrderStatus(Order order, OrderStatus orderStatus, String statusReason) {
        order.setStatus(orderStatus);
        order.setStatusReason(statusReason);
        orderFeignClient.updateOrder(order.getUid(), order);
    }

    @Override
    public boolean allocateInventoryForOrderItem(OrderItem orderItem) {
        if(orderItem.getProduct() == null || orderItem.getStorage() == null) {
            return false;
        }

        List<Inventory> inventories = inventoryFeignClient.getInventories(orderItem.getProduct().getCode(), orderItem.getStorage().getLocation());

        Optional<Inventory> inventory = getAvailableInventoryForOrderItem(orderItem, inventories);

        if (inventory.isEmpty()) {
            return false;
        }

        inventory.get().setQuantity(inventory.get().getQuantity() - orderItem.getQuantity());
        inventoryFeignClient.updateInventory(inventory.get().getUid(), inventory.get());

        return true;
    }

    private void sendEventForOrder(StateMachine<OrderState, OrderEvent> stateMachine, Order order, OrderEvent event) {
        stateMachine.sendEvent(
                MessageBuilder
                        .withPayload(event)
                        .setHeader(ContextConstant.ORDER_CONTEXT_HEADER, order)
                        .build());
        System.out.println("Current state: " + stateMachine.getState().getId());
    }

    private OrderEvent getOrderEvent(OrderStatus status) {
        switch (status) {
            case NEW -> {
                return OrderEvent.ALLOCATE_INVENTORY;
            }
            case INVENTORY_ALLOCATION -> {
                return OrderEvent.CREATE_SHIPPING;
            }
            case SHIPPING_CREATION -> {
                return OrderEvent.SHIPPING_SUCCEED;
            }
            default -> {
                return OrderEvent.PROCESSING_FAILED;
            }
        }
    }

    private Optional<Inventory> getAvailableInventoryForOrderItem(OrderItem orderItem, List<Inventory> inventories) {
        if (inventories.isEmpty()) {
            return Optional.empty();
        }

        return inventories.stream()
                .filter(inventory -> inventory.getQuantity() > inventory.getReserved())
                .filter(inventory -> (inventory.getQuantity() - inventory.getReserved()) >= orderItem.getQuantity())
                .findFirst();
    }
}
