package com.sytoss.ecommerce.platform.orderservice.controller;

import com.sytoss.ecommerce.platform.orderservice.model.Order;
import com.sytoss.ecommerce.platform.orderservice.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody Order order) {
        Order savedOrder = orderService.createAndSaveOrder(order);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{uid}")
                .buildAndExpand(savedOrder.getUid())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public List<Order> getOrders(@RequestParam String userEmail) {
        return orderService.getOrdersByUserEmail(userEmail);
    }

    @GetMapping("/{uid}")
    public ResponseEntity<Order> getOrderByUid(@PathVariable UUID uid) {
        return orderService.getOrderByUid(uid).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{uid}")
    public ResponseEntity<Void> updateOrderByUid(@PathVariable UUID uid, @RequestBody Order order) {
        Optional<Order> optionalOrder = orderService.getOrderByUid(uid);

        if (optionalOrder.isPresent()) {
            orderService.updateOrder(optionalOrder.get(), order);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
