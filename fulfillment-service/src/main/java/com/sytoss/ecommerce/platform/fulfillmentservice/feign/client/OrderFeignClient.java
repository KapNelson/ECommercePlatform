package com.sytoss.ecommerce.platform.fulfillmentservice.feign.client;

import com.sytoss.ecommerce.platform.fulfillmentservice.model.order.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "order-service", url = "http://localhost:8765")
public interface OrderFeignClient {

    @PutMapping("/order-service/orders/{uid}")
    void updateOrder(
            @PathVariable("uid") UUID uid, @RequestBody Order order
    );
}
