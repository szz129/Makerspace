package com.makerspace.makerspaceapp.service;

import com.makerspace.makerspaceapp.exception.ResourceNotFoundException;
import com.makerspace.makerspaceapp.model.Inventory;
import com.makerspace.makerspaceapp.model.Makerspace;
import com.makerspace.makerspaceapp.model.Supplier;
import com.makerspace.makerspaceapp.repository.InventoryRepository;
import com.makerspace.makerspaceapp.repository.MakerspaceRepository;
import com.makerspace.makerspaceapp.repository.SupplierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final MakerspaceRepository makerspaceRepository;
    private final SupplierRepository supplierRepository;

    public InventoryService(InventoryRepository inventoryRepository,
                            MakerspaceRepository makerspaceRepository,
                            SupplierRepository supplierRepository) {
        this.inventoryRepository = inventoryRepository;
        this.makerspaceRepository = makerspaceRepository;
        this.supplierRepository = supplierRepository;
    }

    @Transactional
    public Inventory createInventoryItem(Inventory inventory) {

        if (inventory.getMakerspace() == null || inventory.getMakerspace().getMakerspaceId() == null) {
            throw new IllegalArgumentException("Makerspace ID cannot be null");
        }

        Makerspace makerspace = makerspaceRepository.findById(inventory.getMakerspace().getMakerspaceId())
                .orElseThrow(() -> new ResourceNotFoundException("Makerspace not found"));

        inventory.setMakerspace(makerspace);

        if (inventory.getSupplier() != null && inventory.getSupplier().getSupplierId() != null) {
            Supplier supplier = supplierRepository.findById(inventory.getSupplier().getSupplierId())
                    .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));
            inventory.setSupplier(supplier);
        }

        return inventoryRepository.save(inventory);
    }

    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    public Inventory getInventoryById(Long id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory item not found"));
    }

    public List<Inventory> getInventoryByMakerspace(Long makerspaceId) {
        return inventoryRepository.findByMakerspace_MakerspaceId(makerspaceId);
    }

    public List<Inventory> getItemsNeedingRestock() {
        return inventoryRepository.findItemsNeedingRestock();
    }

    @Transactional
    public Inventory updateInventory(Long id, Inventory updatedInventory) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory item not found"));

        if (updatedInventory.getItemName() != null) {
            inventory.setItemName(updatedInventory.getItemName());
        }
        if (updatedInventory.getQuantity() != null) {
            inventory.setQuantity(updatedInventory.getQuantity());
        }
        if (updatedInventory.getUnit() != null) {
            inventory.setUnit(updatedInventory.getUnit());
        }
        if (updatedInventory.getRestockLevel() != null) {
            inventory.setRestockLevel(updatedInventory.getRestockLevel());
        }

        return inventoryRepository.save(inventory);
    }

    @Transactional
    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }
}
