package com.sytoss.ecommerce.platform.inventoryservice.service.impl;

import com.sytoss.ecommerce.platform.inventoryservice.model.Inventory;
import com.sytoss.ecommerce.platform.inventoryservice.model.Storage;
import com.sytoss.ecommerce.platform.inventoryservice.repository.InventoryRepository;
import com.sytoss.ecommerce.platform.inventoryservice.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class DefaultInventoryService implements InventoryService {
    private final InventoryRepository inventoryRepository;

    @Override
    public Inventory saveInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public List<Inventory> getInventoriesByCodeAndStorageLocation(String productCode, String storageLocation, Integer offset, Integer limit) {
        Inventory exampleInventory = new Inventory();
        exampleInventory.setProductCode(productCode);
        Storage exampleStorage = new Storage();
        exampleStorage.setLocation(storageLocation);
        exampleInventory.setStorage(exampleStorage);

        return inventoryRepository.findAll(Example.of(exampleInventory), PageRequest.of(offset, limit)).getContent();
    }

    @Override
    public Optional<Inventory> getInventoryByUid(UUID uid) {
        return inventoryRepository.findById(uid);
    }

    @Override
    public void updateInventory(Inventory existingInventory, Inventory newInventory) {
        if (existingInventory == null || newInventory == null) {
            throw new IllegalArgumentException("The existingInventory or newInventory is null");
        }

        existingInventory.setProductCode(newInventory.getProductCode());
        existingInventory.setQuantity(newInventory.getQuantity());
        existingInventory.setReserved(newInventory.getReserved());
        existingInventory.setStorage(newInventory.getStorage());

        saveInventory(existingInventory);
    }

    @Override
    public void deleteInventoryByUid(UUID uid) {
        inventoryRepository.deleteById(uid);
    }
}
