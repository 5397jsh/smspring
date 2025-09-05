// ContentService.java (최종 완성본)

package edu.sm.app.service;

import edu.sm.app.dto.Content;
import edu.sm.app.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;

    public List<Content> findNearbyPlaces(double latitude, double longitude) {
        final double DISTANCE_KM = 3.0; //
        return contentRepository.findContentsInRadius(latitude, longitude, DISTANCE_KM);
    }// ✨ 아래 메소드를 새로 추가해주세요.
    /**
     * ID(target)를 받아서 특정 식당의 상세 정보를 조회합니다.
     * @param target 조회할 식당의 ID
     * @return 조회된 식당 정보 (없으면 null 반환)
     */
    public Content getContentDetails(int target) {
        // findById는 Optional<Content>를 반환하므로, orElse(null)로 내용물이 없으면 null을 반환하게 합니다.
        return contentRepository.findById(target).orElse(null);
    }

    public List<Content> getHospitals() {
        return contentRepository.findAll();
    }
}