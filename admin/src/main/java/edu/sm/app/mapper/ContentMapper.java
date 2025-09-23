package edu.sm.app.mapper;

import edu.sm.app.dto.Content;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper // 이 인터페이스가 MyBatis의 매퍼 인터페이스임을 나타냅니다.
public interface ContentMapper {

    /**
     * content 테이블에서 특정 loc 코드를 가진 모든 데이터를 조회합니다.
     * @param loc 조회할 위치 코드 (예: 100)
     * @return 조회된 Content 객체 리스트
     */
    @Select("SELECT target, title, img, lat, lng, loc FROM content WHERE loc = #{loc}")
    List<Content> findByLoc(int loc);

}
