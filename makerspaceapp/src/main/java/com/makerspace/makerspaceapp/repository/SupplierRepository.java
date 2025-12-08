package com.makerspace.makerspaceapp.repository;

import com.makerspace.makerspaceapp.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
// Find suppliers where name contains (case-insensitive)
    List<Supplier> findByNameContainingIgnoreCase(String name);
}