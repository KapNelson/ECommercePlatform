package com.sytoss.ecommerce.platform.fulfillmentservice.model.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Inventory {
    private UUID uid;
    private String productCode;
    private Integer quantity;
    private Integer reserved;
    private Storage storage;
}
