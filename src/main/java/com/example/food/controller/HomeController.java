package com.example.food.controller;

import com.example.food.domain.Region;
import com.example.food.dto.PlaceRequest;
import com.example.food.repository.RegionRepository;
import com.example.food.service.PlaceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {
    private final RegionRepository regionRepository;

    public HomeController(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Region> regions = regionRepository.findAll();
        model.addAttribute("regions", regions);
        return "index";
    }
}
