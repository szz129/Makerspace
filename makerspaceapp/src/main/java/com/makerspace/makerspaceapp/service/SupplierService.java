package com.makerspace.makerspaceapp.service;

import com.makerspace.makerspaceapp.exception.ResourceNotFoundException;
import com.makerspace.makerspaceapp.model.Supplier;
import com.makerspace.makerspaceapp.repository.SupplierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Transactional
    public Supplier createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplierById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Supplier ID cannot be null");
        }
        return supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + id));
    }

    public List<Supplier> searchSuppliersByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        return supplierRepository.findByNameContainingIgnoreCase(name);
    }

    @Transactional
    public Supplier updateSupplier(Long id, Supplier updatedSupplier) {
        if (id == null) {
            throw new IllegalArgumentException("Supplier ID cannot be null");
        }

        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + id));

        if (updatedSupplier.getName() != null) {
            supplier.setName(updatedSupplier.getName());
        }
        if (updatedSupplier.getContact() != null) {
            supplier.setContact(updatedSupplier.getContact());
        }
        if (updatedSupplier.getEmail() != null) {
            supplier.setEmail(updatedSupplier.getEmail());
        }
        if (updatedSupplier.getAddress() != null) {
            supplier.setAddress(updatedSupplier.getAddress());
        }
        if (updatedSupplier.getRating() != null) {
            supplier.setRating(updatedSupplier.getRating());
        }

        return supplierRepository.save(supplier);
    }

    @Transactional
    public void deleteSupplier(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Supplier ID cannot be null");
        }
        if (!supplierRepository.existsById(id)) {
            throw new ResourceNotFoundException("Supplier not found with id: " + id);
        }
        supplierRepository.deleteById(id);
    }
}