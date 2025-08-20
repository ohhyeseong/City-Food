package com.example.food.service;

import com.example.food.Repository.PlaceRepository;
import com.example.food.domain.Place;
import com.example.food.dto.PlaceResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // 스프링 컨테이너에 서비스 컴포넌트로 등록
@Transactional(readOnly = true) // 조회 전용으로 DB 변경 작업이 없음을 표시
public class PlaceQueryService {

    private final PlaceRepository placeRepository; // PlaceRepository 의존성 선언

    // 생성자 주입으로 PlaceRepository 의존성을 초기화
    public PlaceQueryService(PlaceRepository placeRepository){
        this.placeRepository = placeRepository;
    }

    // 단순 조회
    public PlaceResponse get(Long id) {
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Place not found: " + id));
        return PlaceResponse.from(place);
    }

    // 검색 메서드: 지역, 키워드, 카테고리 필터를 이용하여 조건별 검색 수행 후, 결과를 PlaceResponse로 매핑하여 Page 형태로 반환
    public Page<PlaceResponse> search(Long regionId,String keyword, String category, Pageable pageable) {
        
        // regionId가 전달되었는지 여부 확인 (null이 아니면 true)
        boolean hasRegion = regionId != null;
        // keyword가 전달되었고 공백이 아니면 true
        boolean hasKeyword = keyword != null && !keyword.isBlank();
        // category가 전달되었고 공백이 아니면 true
        boolean hasCategory = category != null && !category.isBlank();
            
        Page<Place> page; // 검색 결과를 담을 page 객체
        
        // 조건 1 : 지역과 카테고리 값이 제공되었으나, 키워드는 제공되지 않은경우
        if (hasRegion && hasCategory && !hasKeyword) {
            // 지정된 지역 내에서 카테고리를 기준으로 검색 (대소문자 무시)
            page = placeRepository.findByRegion_IdAndCategoryIgnoreCase(regionId, category, pageable);
        }   // 조건 2 : 지역과 키워드가 모두 제공된 경우
            else if (hasRegion && hasKeyword) {
                // 지정된 지역 내에서 이름에 키워드가 포함되는 장소들을 검색 (대소문자 무시)
                page = placeRepository.findByRegion_IdAndNameContainingIgnoreCase(regionId, keyword, pageable);
        }   // 조건 3: 오직 지역 값만 제공된 경우
            else if (hasRegion) {
                // 지정된 지역에 해당하는 모든 장소들을 검색
                page = placeRepository.findByRegion_Id(regionId, pageable);
        }   // 조건 4: 지역 없이 키워드만 제공된 경우
            else if (hasKeyword) {
                // 전체 장소 중에서 이름에 키워드가 포함되는 결과를 검색 (대소문자 무시)
                page = placeRepository.findByNameContainingIgnoreCase(keyword, pageable);
        }   // 조건 5: 어떠한 검색 조건도 제공되지 않은 경우 전체 데이터를 반환
            else {
                page = placeRepository.findAll(pageable);
        }
        // 검색된 Place 객체들을 PlaceResponse DTO로 매핑 후 Page 형태로 반환
        return page.map(PlaceResponse::from);

    }


}
