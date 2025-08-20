package com.example.food.controller;

import com.example.food.dto.PlaceRequest; // Place 요청 데이터 전송 객체
import com.example.food.dto.PlaceResponse; // Place 응답 데이터 전송 객체
import com.example.food.service.PlaceService; // 데이터 조작(생성, 수정, 삭제) 로직을 담당하는 서비스
import jakarta.validation.Valid; // 유효성 검사 어노테이션
import org.springframework.http.ResponseEntity; // HTTP 응답 빙더 객체
import org.springframework.web.bind.annotation.*; // REST API 관련 어노테이션 제공

@RestController // 이 클래스가 RESTful API 컨트롤러임을 명시
@RequestMapping("/api/places") // "/api/places"로 시작하는 요청 매핑
public class PlaceCommandController {

    private final PlaceService placeService; // 쓰기 작업을 처리하기 위한 서비스

    // 생성자 주입을 통한 PlaceService 의존성 주입
    public PlaceCommandController(PlaceService placeService) {
        this.placeService = placeService;
    }

    // 생성: POST /api/places
    @PostMapping // HTTP POST 요청을 처리 (새로운 데이터를 생성)
    public ResponseEntity<PlaceResponse> create(@Valid @RequestBody PlaceRequest req) {
        // @Valid: PlaceRequest의 필드 유효성 검사를 수행
        // @RequestBody: HTTP 요청 본문을 PlaceRequest 객체로 변환
        // PlaceService의 create 메서드 호출 후 생성된 PlaceResponse 반환
        return ResponseEntity.ok(placeService.create(req)); // HTTP 200 OK와 함께 응답 반환
    }

    // 부분 수정 엔드포인트
    @PatchMapping("/{id}") // HTTP POST 요청을 처리 (새로운 데이터를 생성)
    public ResponseEntity<PlaceResponse> update(@PathVariable Long id, @RequestBody PlaceRequest req) {
        // @PathVariable: URL 경로의 {id} 값을 바인딩
        // @RequestBody: HTTP 요청 본문을 PlaceRequest 객체로 변환
        // PlaceService의 update 메서드를 호출하여 데이터를 업데이트 후 결과 반환
        return ResponseEntity.ok(placeService.update(id, req)); // HTTP 200 OK와 함께 응답과 업데이트 된 데이터 반환
    }

    // 삭제 엔드 포인트
    @DeleteMapping("/{id}") // HTTP DELETE 요청을 처리 (대상 데이터를 삭제)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        // PlaceService의 delete 메서드를 호출하여 데이터 삭제 처리
        placeService.delete(id);
        // HTTP 204 No Content 응답을 반환하여 삭제 완료를 알림
        return ResponseEntity.noContent().build();
    }
}
