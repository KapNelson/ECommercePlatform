package com.sytoss.ecommerce.platform.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "orders")
public class Order {
    @Id
    private UUID uid;
    private String comment;
    private String status;
    private String statusReason;
    private Double totalPrice;
    private User user;
    private List<OrderItem> orderItems;
}
