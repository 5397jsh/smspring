package edu.sm.app.service;

import edu.sm.app.dto.Content;
import edu.sm.app.mapper.ContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 이 클래스가 비즈니스 로직을 처리하는 Service 계층의 컴포넌트임을 나타냅니다.
public class ContentService {

    @Autowired
    private ContentMapper contentMapper; // MyBatis 매퍼 인터페이스 주입

    /**
     * 병원 목록을 조회하는 비즈니스 로직을 수행합니다.
     * @return DB에서 조회한 병원 목록
     */
    public List<Content> getHospitals() {
        // Mapper를 호출하여 loc 코드가 100인 데이터를 조회합니다.
        // (제공해주신 SQL insert 구문에서 병원의 loc가 100인 것을 기반으로 합니다.)
        return contentMapper.findByLoc(100);
    }
}
