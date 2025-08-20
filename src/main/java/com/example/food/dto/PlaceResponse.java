package com.example.food.dto;

import com.example.food.domain.Place;

import java.time.LocalDateTime;

public class PlaceResponse {
    private Long id;
    private String name;
    private String category;
    private String address;
    private String phone;
    private String description;

    private Long regionId;
    private String regionName;
    private String regionSlug;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PlaceResponse(Long id, String name, String category, String address, String phone, String description,
                         Long regionId, String regionName, String regionSlug,
                         LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id; this.name = name; this.category = category; this.address = address;
        this.phone = phone; this.description = description;
        this.regionId = regionId; this.regionName = regionName; this.regionSlug = regionSlug;
        this.createdAt = createdAt; this.updatedAt = updatedAt;
    }

    public static PlaceResponse from(Place p) {
        return new PlaceResponse(
                p.getId(), p.getName(), p.getCategory(), p.getAddress(), p.getPhone(), p.getDescription(),
                p.getRegion().getId(), p.getRegion().getName(), p.getRegion().getSlug(),
                p.getCreatedAt(), p.getUpdatedAt()
        );
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getDescription() { return description; }
    public Long getRegionId() { return regionId; }
    public String getRegionName() { return regionName; }
    public String getRegionSlug() { return regionSlug; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
