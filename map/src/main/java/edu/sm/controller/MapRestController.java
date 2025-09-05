// MapRestController.java (최종 완성본)

package edu.sm.controller;

import edu.sm.app.dto.Content;
import edu.sm.app.service.ContentService; // MarkerService import는 제외
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MapRestController {

    private final ContentService contentService;

    @GetMapping("/nearby-places")
    public List<Content> getNearbyPlaces(
            @RequestParam("lat") double latitude,
            @RequestParam("lng") double longitude) {
        log.info("Request received for /nearby-places with lat={}, lng={}", latitude, longitude);
        return contentService.findNearbyPlaces(latitude, longitude);
    }
    // ✨ 아래 API 엔드포인트를 새로 추가해주세요.
    /**
     * 식당의 ID(target)를 PathVariable로 받아서
     * 해당 식당의 모든 상세 정보를 JSON으로 반환합니다.
     * @param target 조회할 식당의 ID
     * @return 식당 상세 정보
     */
    @GetMapping("/contents/{target}")
    public Content getContentDetails(@PathVariable int target) {
        log.info("Request received for content details with target ID: {}", target);
        return contentService.getContentDetails(target);
    }
}