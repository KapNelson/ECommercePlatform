package com.sytoss.ecommerce.platform.inventoryservice.controller;

import com.sytoss.ecommerce.platform.inventoryservice.model.Inventory;
import com.sytoss.ecommerce.platform.inventoryservice.service.InventoryService;
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
@RequestMapping("/inventories")
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<Void> createInventory(@RequestBody Inventory inventory) {
        Inventory savedInventory = inventoryService.saveInventory(inventory);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{uid}")
                .buildAndExpand(savedInventory.getUid())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public List<Inventory> getInventories(@RequestParam(required = false) String productCode,
                                          @RequestParam(name = "storage.location", required = false) String storageLocation,
                                          @RequestParam(required = false, defaultValue = "0") Integer offset,
                                          @RequestParam(required = false, defaultValue = "50") Integer limit) {
        return inventoryService.getInventoriesByCodeAndStorageLocation(productCode, storageLocation, offset, limit);
    }

    @GetMapping("/{uid}")
    public ResponseEntity<Inventory> getInventoryByUid(@PathVariable UUID uid) {
        return inventoryService.getInventoryByUid(uid).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{uid}")
    public ResponseEntity<Void> updateInventoryByUid(@PathVariable UUID uid, @RequestBody Inventory inventory) {
        Optional<Inventory> optionalInventory = inventoryService.getInventoryByUid(uid);

        if (optionalInventory.isPresent()) {
            inventoryService.updateInventory(optionalInventory.get(), inventory);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<Void> deleteInventoryByUid(@PathVariable UUID uid) {
        inventoryService.deleteInventoryByUid(uid);
        return ResponseEntity.noContent().build();
    }
}
