package com.makerspace.makerspaceapp.controller;

import com.makerspace.makerspaceapp.dto.ApiResponse;
import com.makerspace.makerspaceapp.model.Inventory;
import com.makerspace.makerspaceapp.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "*")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Inventory>> createInventoryItem(@RequestBody Inventory inventory) {
        Inventory created = inventoryService.createInventoryItem(inventory);
        ApiResponse<Inventory> response = new ApiResponse<>(true, "Inventory item created successfully", created);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Inventory>>> getAllInventory() {
        List<Inventory> items = inventoryService.getAllInventory();
        ApiResponse<List<Inventory>> response = new ApiResponse<>(true, "Inventory items retrieved successfully", items);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Inventory>> getInventoryById(@PathVariable Long id) {
        Inventory item = inventoryService.getInventoryById(id);
        ApiResponse<Inventory> response = new ApiResponse<>(true, "Inventory item retrieved successfully", item);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/makerspace/{makerspaceId}")
    public ResponseEntity<ApiResponse<List<Inventory>>> getInventoryByMakerspace(@PathVariable Long makerspaceId) {
        List<Inventory> items = inventoryService.getInventoryByMakerspace(makerspaceId);
        ApiResponse<List<Inventory>> response = new ApiResponse<>(true, "Inventory items retrieved successfully", items);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/restock-needed")
    public ResponseEntity<ApiResponse<List<Inventory>>> getItemsNeedingRestock() {
        List<Inventory> items = inventoryService.getItemsNeedingRestock();
        ApiResponse<List<Inventory>> response = new ApiResponse<>(true, "Items needing restock retrieved successfully", items);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Inventory>> updateInventory(
            @PathVariable Long id,
            @RequestBody Inventory inventory) {
        Inventory updated = inventoryService.updateInventory(id, inventory);
        ApiResponse<Inventory> response = new ApiResponse<>(true, "Inventory item updated successfully", updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
        ApiResponse<Void> response = new ApiResponse<>(true, "Inventory item deleted successfully", null);
        return ResponseEntity.ok(response);
    }
}