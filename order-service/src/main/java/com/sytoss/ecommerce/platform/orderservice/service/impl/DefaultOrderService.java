package com.sytoss.ecommerce.platform.orderservice.service.impl;

import com.sytoss.ecommerce.platform.orderservice.model.Order;
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

    @Override
    public Order saveOrder(Order order) {
        if (order.getUid() == null) {
            order.setUid(UUID.randomUUID());
        }
        return orderRepository.save(order);
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
        existingOrder.setTotalPrice(newOrder.getTotalPrice());
        existingOrder.setUser(newOrder.getUser());
        existingOrder.setOrderItems(newOrder.getOrderItems());

        saveOrder(existingOrder);
    }
}
