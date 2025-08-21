package com.example.food.service;


import com.example.food.repository.PlaceRepository;
import com.example.food.repository.RegionRepository;
import com.example.food.domain.Place;
import com.example.food.domain.Region;
import com.example.food.dto.PlaceRequest;
import com.example.food.dto.PlaceResponse;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PlaceService {
    private static final Logger log = LoggerFactory.getLogger(PlaceService.class);
    private final PlaceRepository placeRepository;
    private final RegionRepository regionRepository;


    public PlaceService(PlaceRepository placeRepository, RegionRepository regionRepository) {
        this.placeRepository = placeRepository;
        this.regionRepository = regionRepository;
    }
    // 생성
    @Transactional
    public PlaceResponse create(PlaceRequest req) {
        log.info("PlaceService.create() called, regionSlug={}", req.getRegionSlug());
        Region region = regionRepository.findBySlug(req.getRegionSlug()).orElseThrow(() -> new EntityNotFoundException("Region not found: " + req.getRegionSlug()));
        Place saved = placeRepository.save(Place.of(region, req.getName(), req.getCategory(), req.getAddress(), req.getPhone(), req.getDescription()));
        return PlaceResponse.from(saved);
    }

    // 부분 수정 (보낸 값만 반영)
    @Transactional
    public PlaceResponse update(Long id, PlaceRequest req) {
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Place not found: " + id));

        if (req.getName() != null && !req.getName().isBlank()) {
            place.changeName(req.getName());
        }
        if (req.getCategory() != null && !req.getCategory().isBlank()) {
            place.changeCategory(req.getCategory());
        }
        if (req.getAddress() != null && !req.getAddress().isBlank()) {
            place.changeAddress(req.getAddress());
        }
        if (req.getPhone() != null) {
            place.changePhone(req.getPhone());
        }
        if (req.getDescription() != null) {
            place.changeDescription(req.getDescription());
        }

        if (req.getRegionSlug() != null && !req.getRegionSlug().isBlank()) {
            Region region = regionRepository.findBySlug(req.getRegionSlug()).orElseThrow(() -> new EntityNotFoundException("Region not found: " + req.getRegionSlug()));
            place.changeRegion(region);
        }

        return PlaceResponse.from(place);
    }

    // 삭제
    @Transactional
    public void delete(Long id) {
        if (!placeRepository.existsById(id)) {
            throw new EntityNotFoundException("Place not found: " + id);
        }
        placeRepository.deleteById(id);
    }
}
