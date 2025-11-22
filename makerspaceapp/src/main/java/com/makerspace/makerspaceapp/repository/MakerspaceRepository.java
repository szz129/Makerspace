package com.makerspace.makerspaceapp.repository;

import com.makerspace.makerspaceapp.model.Makerspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MakerspaceRepository extends JpaRepository<Makerspace, Long> {
    List<Makerspace> findByLocation(String location);
    List<Makerspace> findByNameContainingIgnoreCase(String name);
}
