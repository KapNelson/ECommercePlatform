package com.sytoss.ecommerce.platform.notificationservice.model.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    private String code;
    private String name;
    private Price price;
}
