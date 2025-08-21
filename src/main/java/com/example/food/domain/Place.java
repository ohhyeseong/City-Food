package com.example.food.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(name = "places")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 120)
    private String name;

    @NotBlank
    @Column(nullable = false, length = 40)
    private String category;

    @NotBlank
    @Column(nullable = false, length = 200)
    private String address;

    @Column(length = 30)
    private String phone;

    @Column(length = 1000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "region_id")
    private Region region;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    protected Place() {}

    private Place(Region region, String name, String category, String address, String phone, String description){
        this.region = region;
        this.name = name;
        this.category = category;
        this.address = address;
        this.phone = phone;
        this.description = description;
    }

    public static Place of(Region region, String name, String category, String address, String phone, String description) {
        return new Place(region,name,category,address,phone,description);
    }

    @PrePersist
    void onCreate() {
        this.createdAt = this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }
    public Region getRegion() {
        return region;
    }
    public String getName() {
        return name;
    }
    public String getCategory() {
        return category;
    }
    public String getAddress() {
        return address;
    }
    public String getPhone() {
        return phone;
    }
    public String getDescription() {
        return description;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void changeRegion(Region region) {
        this.region = region;
    }
    public void changeName(String name) {
        this.name = name;
    }
    public void changeCategory(String category) {
        this.category = category;
    }
    public void changeAddress(String address) {
        this.address = address;
    }
    public void changePhone(String phone) {
        this.phone = phone;
    }
    public void changeDescription(String description) {
        this.description = description;
    }
}
