package com.makerspace.makerspaceapp.service;

import com.makerspace.makerspaceapp.exception.ResourceNotFoundException;
import com.makerspace.makerspaceapp.model.Makerspace;
import com.makerspace.makerspaceapp.repository.MakerspaceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MakerspaceService {
    private final MakerspaceRepository makerspaceRepository;
    
    public MakerspaceService(MakerspaceRepository makerspaceRepository) {
        this.makerspaceRepository = makerspaceRepository;
    }
    
    public Makerspace create(Makerspace makerspace) {
        return makerspaceRepository.save(makerspace);
    }
    
    public List<Makerspace> getAll() {
        return makerspaceRepository.findAll();
    }
    
    public Makerspace getById(Long id) {
        return makerspaceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Makerspace", "id", id));
    }
    
    public List<Makerspace> getByLocation(String location) {
        return makerspaceRepository.findByLocation(location);
    }
    
    @Transactional
    public Makerspace update(Long id, Makerspace makerspace) {
        Makerspace existing = getById(id);
        if (makerspace.getName() != null) existing.setName(makerspace.getName());
        if (makerspace.getLocation() != null) existing.setLocation(makerspace.getLocation());
        if (makerspace.getDescription() != null) existing.setDescription(makerspace.getDescription());
        if (makerspace.getCapacity() != null) existing.setCapacity(makerspace.getCapacity());
        if (makerspace.getContactEmail() != null) existing.setContactEmail(makerspace.getContactEmail());
        return makerspaceRepository.save(existing);
    }
    
    public void delete(Long id) {
        if (!makerspaceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Makerspace", "id", id);
        }
        makerspaceRepository.deleteById(id);
    }
}