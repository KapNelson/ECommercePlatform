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
public class Storage {
    private UUID uid;
    private String location;
    private String name;
}
