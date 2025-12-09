package com.makerspace.makerspaceapp.service;

import com.makerspace.makerspaceapp.dto.MakerspaceRequest;
import com.makerspace.makerspaceapp.dto.MakerspaceResponse;
import com.makerspace.makerspaceapp.exception.ResourceNotFoundException;
import com.makerspace.makerspaceapp.model.Makerspace;
import com.makerspace.makerspaceapp.repository.MakerspaceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MakerspaceService {

    private final MakerspaceRepository makerspaceRepository;

    public MakerspaceService(MakerspaceRepository makerspaceRepository) {
        this.makerspaceRepository = makerspaceRepository;
    }

    // ----------------------------------------------------
    // CREATE
    // ----------------------------------------------------
    public MakerspaceResponse create(MakerspaceRequest request) {
        Makerspace m = new Makerspace();
        mapRequestToEntity(request, m);

        Makerspace saved = makerspaceRepository.save(m);
        return toResponse(saved);
    }

    // ----------------------------------------------------
    // GET ALL
    // ----------------------------------------------------
    public List<MakerspaceResponse> getAll() {
        return makerspaceRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // ----------------------------------------------------
    // GET BY ID
    // ----------------------------------------------------
    public MakerspaceResponse getById(Long id) {
        Makerspace m = findByIdOrThrow(id);
        return toResponse(m);
    }

    // ----------------------------------------------------
    // GET BY LOCATION
    // ----------------------------------------------------
    public List<MakerspaceResponse> getByLocation(String location) {
        return makerspaceRepository.findByLocation(location)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // ----------------------------------------------------
    // UPDATE
    // ----------------------------------------------------
    @Transactional
    public MakerspaceResponse update(Long id, MakerspaceRequest request) {
        Makerspace existing = findByIdOrThrow(id);

        // Update only provided fields (PATCH-like)
        if (request.getName() != null) existing.setName(request.getName());
        if (request.getLocation() != null) existing.setLocation(request.getLocation());
        if (request.getDescription() != null) existing.setDescription(request.getDescription());
        if (request.getCapacity() != null) existing.setCapacity(request.getCapacity());
        if (request.getContactEmail() != null) existing.setContactEmail(request.getContactEmail());

        Makerspace updated = makerspaceRepository.save(existing);

        return toResponse(updated);
    }

    // ----------------------------------------------------
    // DELETE
    // ----------------------------------------------------
    public void delete(Long id) {
        if (!makerspaceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Makerspace", "id", id);
        }
        makerspaceRepository.deleteById(id);
    }

    // ----------------------------------------------------
    // HELPER METHODS
    // ----------------------------------------------------

    private Makerspace findByIdOrThrow(Long id) {
        return makerspaceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Makerspace", "id", id));
    }

    // Convert Entity → Response DTO
    private MakerspaceResponse toResponse(Makerspace m) {
        return new MakerspaceResponse(
                m.getMakerspaceId(),
                m.getName(),
                m.getLocation(),
                m.getDescription(),
                m.getCapacity(),
                m.getContactEmail()
        );
    }

    // Convert Request DTO → Entity (for create only)
    private void mapRequestToEntity(MakerspaceRequest request, Makerspace m) {
        m.setName(request.getName());
        m.setLocation(request.getLocation());
        m.setDescription(request.getDescription());
        m.setCapacity(request.getCapacity());
        m.setContactEmail(request.getContactEmail());
    }
}
