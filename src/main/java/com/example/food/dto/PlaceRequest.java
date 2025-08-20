package com.example.food.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PlaceRequest {
    @NotBlank
    @Size(max = 120)
    private String name;

    @NotBlank
    @Size(max = 40)
    private String category;

    @NotBlank
    @Size(max = 200)
    private String address;

    private String phone;
    private String description;

    @NotBlank
    private String regionSlug;

    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getDescription() { return description; }
    public String getRegionSlug() { return regionSlug; }
}
