package com.example.food.Repository;

import com.example.food.domain.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

// 키워드, 특정지역(regionId), 카테고리 를 이용해서 찾는 검색
public interface PlaceRepository extends JpaRepository<Place,Long> {

    // 키워드에 해당하는 이름을 대소문자 구분 없이 포함하는 장소들을 Page 형태로 반환
    Page<Place> findByNameContainingIgnoreCase(String keyword, Pageable pageable);

    // 특정 지역(regionId)에 해당하는 장소들을 Page 형태로 반환
    Page<Place> findByRegion_Id(Long regionId, Pageable pageable);

    // 특정 지역(regionId) 내에서 지정된 카테고리(categor)를 대소문자 구분 없이 검색하여 장소들을 Page 형태로 반환
    Page<Place> findByRegion_IdAndCategoryIgnoreCase(Long regionId, String category, Pageable pageable);
    // 특정 지역(regionId) 내에서 이름에 지정된 키워드(keyword)가 포함되는 장소들을 Page 형태로 반환
    Page<Place> findByRegion_IdAndNameContainingIgnoreCase(Long regionId, String keyword, Pageable pageable);
}
