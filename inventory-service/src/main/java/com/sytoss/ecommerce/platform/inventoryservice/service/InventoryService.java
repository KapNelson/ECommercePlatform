package com.sytoss.ecommerce.platform.inventoryservice.service;

import com.sytoss.ecommerce.platform.inventoryservice.model.Inventory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service interface for managing inventories.
 */
public interface InventoryService {
    /**
     * Saves a new inventory or updates an existing one.
     *
     * @param inventory The inventory to be saved or updated.
     * @return The saved or updated inventory.
     */
    Inventory saveInventory(Inventory inventory);

    /**
     * Retrieves a list of inventories with a specific code, storageLocation and pagination.
     *
     * @param productCode     The product code to filter inventories by.
     * @param storageLocation The inventory storage location to filter inventories by.
     * @param offset          The starting index of the result set (pagination offset).
     * @param limit           The maximum number of inventories to retrieve (pagination limit).
     * @return The list of inventories with the specified code and storage location within the specified range.
     */
    List<Inventory> getInventoriesByCodeAndStorageLocation(String productCode, String storageLocation, Integer offset, Integer limit);

    /**
     * Retrieves a inventory by its unique identifier (UID).
     *
     * @param uid The unique identifier of the inventory.
     * @return An {@link Optional} containing the inventory with the specified UID, or empty if not found.
     */
    Optional<Inventory> getInventoryByUid(UUID uid);

    /**
     * Updates an existing inventory with new information.
     *
     * @param existingInventory The existing inventory to be updated.
     * @param newInventory      The new inventory information to be applied.
     * @throws IllegalArgumentException If the existingInventory or newInventory is null.
     */
    void updateInventory(Inventory existingInventory, Inventory newInventory);

    /**
     * Deletes a inventory by its unique identifier (UID).
     *
     * @param uid The unique identifier of the inventory to be deleted.
     */
    void deleteInventoryByUid(UUID uid);
}
