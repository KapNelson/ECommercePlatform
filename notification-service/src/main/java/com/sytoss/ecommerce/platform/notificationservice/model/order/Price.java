package com.sytoss.ecommerce.platform.notificationservice.model.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Price {
    private Double amount;
    private Currency currency;
}
