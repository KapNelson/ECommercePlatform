package com.sytoss.ecommerce.platform.orderservice.service.impl;

import com.sytoss.ecommerce.platform.orderservice.kafka.producer.OrderProducer;
import com.sytoss.ecommerce.platform.orderservice.model.Order;
import com.sytoss.ecommerce.platform.orderservice.model.OrderItem;
import com.sytoss.ecommerce.platform.orderservice.model.OrderStatus;
import com.sytoss.ecommerce.platform.orderservice.repository.OrderRepository;
import com.sytoss.ecommerce.platform.orderservice.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class DefaultOrderService implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderProducer orderProducer;

    @Override
    public Order createAndSaveOrder(Order order) {
        if (order.getUid() == null) {
            order.setUid(UUID.randomUUID());
        }

        order.setTotalPrice(calculateTotalPrice(order));
        order.setStatus(OrderStatus.NEW);

        return saveOrder(order);
    }

    @Override
    public List<Order> getOrdersByUserEmail(String userEmail) {
        return orderRepository.findByUserEmail(userEmail);
    }

    @Override
    public Optional<Order> getOrderByUid(UUID uid) {
        return orderRepository.findById(uid);
    }

    @Override
    public void updateOrder(Order existingOrder, Order newOrder) {
        if (existingOrder == null || newOrder == null) {
            throw new IllegalArgumentException("The existingOrder or newOrder is null");
        }

        existingOrder.setComment(newOrder.getComment());
        existingOrder.setStatus(newOrder.getStatus());
        existingOrder.setStatusReason(newOrder.getStatusReason());
        existingOrder.setTotalPrice(calculateTotalPrice(newOrder));
        existingOrder.setUser(newOrder.getUser());
        existingOrder.setOrderItems(newOrder.getOrderItems());

        saveOrder(existingOrder);
    }

    @Override
    public void sendOrderToStartFulfillment(Order order) {
        orderProducer.sendOrder(order);
    }

    private Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    private Double calculateTotalPrice(Order order) {
        double totalPrice = 0.0;

        if (order.getOrderItems() == null || order.getOrderItems().isEmpty()) {
            return totalPrice;
        }

        for (OrderItem orderItem : order.getOrderItems()) {
            if (isCalculationPossible(orderItem)) {
                totalPrice += orderItem.getProduct().getPrice().getAmount() * orderItem.getQuantity();
            }
        }

        return totalPrice;
    }

    private boolean isCalculationPossible(OrderItem orderItem) {
        return orderItem != null
                && orderItem.getQuantity() != null
                && orderItem.getProduct() != null
                && orderItem.getProduct().getPrice() != null
                && orderItem.getProduct().getPrice().getAmount() != null;
    }
}
