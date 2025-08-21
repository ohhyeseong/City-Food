package com.example.food.repository;

import com.example.food.domain.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    // 기존 검색 메서드 (List 반환)
    List<Place> findByNameContaining(String keyword);
    List<Place> findByRegionId(Long regionId);
    List<Place> findByNameContainingAndRegionId(String keyword, Long regionId);

    // N+1 문제 해결을 위해 @EntityGraph 추가
    // JpaRepository의 findAll을 오버라이드하여 EntityGraph 적용
    @Override
    @EntityGraph(attributePaths = {"region"})
    Page<Place> findAll(Pageable pageable);

    // 새로운 검색 메서드 (Page 반환, PlaceQueryService용)
    @EntityGraph(attributePaths = {"region"})
    Page<Place> findByRegion_IdAndCategoryIgnoreCase(Long regionId, String category, Pageable pageable);
    @EntityGraph(attributePaths = {"region"})
    Page<Place> findByRegion_IdAndNameContainingIgnoreCase(Long regionId, String keyword, Pageable pageable);
    @EntityGraph(attributePaths = {"region"})
    Page<Place> findByRegion_Id(Long regionId, Pageable pageable);
    @EntityGraph(attributePaths = {"region"})
    Page<Place> findByNameContainingIgnoreCase(String keyword, Pageable pageable);
}