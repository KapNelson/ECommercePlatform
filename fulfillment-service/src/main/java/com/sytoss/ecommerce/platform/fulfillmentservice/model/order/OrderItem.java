package com.sytoss.ecommerce.platform.fulfillmentservice.model.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItem {
    private Integer quantity;
    private Product product;
    private Storage storage;
}
