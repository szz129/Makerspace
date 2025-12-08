package com.makerspace.makerspaceapp.repository;

import com.makerspace.makerspaceapp.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    // items belonging to a makerspace
    List<Inventory> findByMakerspace_MakerspaceId(Long makerspaceId);
    
    // Custom query: find items where quantity <= restock_level
    @Query("SELECT i FROM Inventory i WHERE i.quantity <= i.restockLevel")
    List<Inventory> findItemsNeedingRestock();
}