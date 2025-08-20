package com.example.food.controller;

import com.example.food.dto.PlaceResponse;
import com.example.food.service.PlaceQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController // 이 클래스가 RESTful API 컨트롤러임을 명시
@RequestMapping("/api/places") // "/api/places"로 시작하는 요청 매핑
public class PlaceQueryController {

    private final PlaceQueryService placeQueryService;// 조회 작업을 처리하기 위한 서비스

    // 생성자 주입을 통한 PlaceQueryService 의존성 주입
    public PlaceQueryController(PlaceQueryService placeQueryService) {
        this.placeQueryService = placeQueryService;
    }

    // 단건 조회(get) 엔드포인트
    @GetMapping("/{id}") // HTTP GET 요청으로 특정 ID의 Place 조회
    public ResponseEntity<PlaceResponse> get(@PathVariable Long id) {
        // PlaceQueryService의 get 메서드를 호출하여 id로 데이터를 검색 후 결과 반환
        return ResponseEntity.ok(placeQueryService.get(id)); // HTTP 200 OK와 함께 조회된 데이터 반환
    }

    // 목록 조회(search) 엔드포인트 - 필터, 검색, 페이지네이션 포함
    @GetMapping // HTTP GET 요청 처리 (목록 조회, 필터, 검색, 페이지네이션)
    public ResponseEntity<Page<PlaceResponse>> search(
            @RequestParam(required = false) Long regionId, // 선택적 파라미터: 지역 필터 (없을 수도 있음)
            @RequestParam(required = false) String keyword, // 선택적 파라미터: 검색을 위한 키워드
            @RequestParam(required = false) String category, // 선택적 파라미터: 카테고리 필터
            @RequestParam(defaultValue = "0") int page, // 페이징: 시작 페이지 번호 (기본값 0)
            @RequestParam(defaultValue = "10") int size // 페이징: 페이지당 아이템 수 (기본값 10)
    ) {
        // PageRequest를 사용해 페이지 네이션 정보를 생성
        // PlaceQueryService의 search 메서드에 필터, 검색, 페이지네이션 정보를 전달하여 경과 반환
        return ResponseEntity.ok(
                placeQueryService.search(regionId,keyword,category, PageRequest.of(page,size))
        );
    }
}


