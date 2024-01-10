package com.sytoss.ecommerce.platform.notificationservice.model.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
    private UUID uid;
    private String comment;
    private OrderStatus status;
    private String statusReason;
    private Double totalPrice;
    private User user;
    private List<OrderItem> orderItems;
}
