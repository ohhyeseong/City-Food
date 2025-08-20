package com.example.food.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "regions")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 50, unique = true)
    private String name;

    @NotBlank
    @Column(nullable = false, length = 50, unique = true)
    private String slug;

    protected Region() {}
    private  Region(String name, String slug) {
        this.name = name;
        this.slug = slug;
    }

    public static Region of(String name, String slug) {
        return new Region(name,slug);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public  void changeName(String name) {
        this.name = name;
    }

    public  void changeSlug(String slug) {
        this.slug = slug;
    }

}
