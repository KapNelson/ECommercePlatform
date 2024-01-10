package com.sytoss.ecommerce.platform.fulfillmentservice.feign.client;

import com.sytoss.ecommerce.platform.fulfillmentservice.model.inventory.Inventory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "inventory-service", url = "${gateway.path}")
public interface InventoryFeignClient {

    @GetMapping("/inventory-service/inventories")
    List<Inventory> getInventories(
            @RequestParam("productCode") String productCode,
            @RequestParam("storage.location") String storageLocation
    );

    @PutMapping("/inventory-service/inventories/{uid}")
    void updateInventory(
            @PathVariable("uid") UUID uid, @RequestBody Inventory inventory
    );
}
