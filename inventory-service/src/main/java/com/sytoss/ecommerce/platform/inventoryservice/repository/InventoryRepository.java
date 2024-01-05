package com.sytoss.ecommerce.platform.inventoryservice.repository;

import com.sytoss.ecommerce.platform.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, UUID> {
}
