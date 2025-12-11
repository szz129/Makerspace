package com.makerspace.makerspaceapp.controller;

import com.makerspace.makerspaceapp.dto.ApiResponse;
import com.makerspace.makerspaceapp.model.Supplier;
import com.makerspace.makerspaceapp.service.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@CrossOrigin(origins = "*")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Supplier>> createSupplier(@RequestBody Supplier supplier) {
        Supplier created = supplierService.createSupplier(supplier);
        ApiResponse<Supplier> response = new ApiResponse<>(true, "Supplier created successfully", created);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Supplier>>> getAllSuppliers() {
        List<Supplier> suppliers = supplierService.getAllSuppliers();
        ApiResponse<List<Supplier>> response = new ApiResponse<>(true, "Suppliers retrieved successfully", suppliers);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Supplier>> getSupplierById(@PathVariable Long id) {
        Supplier supplier = supplierService.getSupplierById(id);
        ApiResponse<Supplier> response = new ApiResponse<>(true, "Supplier retrieved successfully", supplier);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Supplier>>> searchSuppliers(@RequestParam String name) {
        List<Supplier> suppliers = supplierService.searchSuppliersByName(name);
        ApiResponse<List<Supplier>> response = new ApiResponse<>(true, "Suppliers found", suppliers);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Supplier>> updateSupplier(
            @PathVariable Long id,
            @RequestBody Supplier supplier) {
        Supplier updated = supplierService.updateSupplier(id, supplier);
        ApiResponse<Supplier> response = new ApiResponse<>(true, "Supplier updated successfully", updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        ApiResponse<Void> response = new ApiResponse<>(true, "Supplier deleted successfully", null);
        return ResponseEntity.ok(response);
    }
}