package edu.sm.app.repository;

import edu.sm.app.dto.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Integer> {

    /**
     * Haversine formula를 사용하여 현재 위치 반경 내의 장소를 검색하는 네이티브 SQL 쿼리입니다.
     * content 테이블의 lat, lng 컬럼을 사용하여 거리를 계산합니다.
     */
    @Query(value =
            "SELECT *, " +
                    "(6371 * acos(cos(radians(:latitude)) * cos(radians(lat)) * cos(radians(lng) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(lat)))) AS distance " +
                    "FROM content " +
                    "HAVING distance <= :distanceKm " +
                    "ORDER BY distance",
            nativeQuery = true)
    List<Content> findContentsInRadius(
            @Param("latitude") double latitude,
            @Param("longitude") double longitude,
            @Param("distanceKm") double distanceKm);
}