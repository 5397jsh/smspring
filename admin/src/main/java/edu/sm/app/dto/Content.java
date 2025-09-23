package edu.sm.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 생성합니다. (e.g. new Content())
@AllArgsConstructor // 모든 필드를 파라미터로 받는 생성자를 생성합니다.
public class Content {
    // 데이터베이스 컬럼에 맞춰 필드를 정의합니다.
    private int target;
    private String title;
    private String img;
    private double lat;
    private double lng;
    private int loc;

    /**
     * MapRestController의 getcontents 메소드에서 임시 데이터를 생성할 때 사용하는 생성자입니다.
     * new Content(lat, lng, title, img, target) 순서에 맞게 파라미터를 받습니다.
     *
     * @param lat    위도
     * @param lng    경도
     * @param title  제목
     * @param img    이미지 경로
     * @param target 고유 ID
     */
    public Content(double lat, double lng, String title, String img, int target) {
        this.lat = lat;
        this.lng = lng;
        this.title = title;
        this.img = img;
        this.target = target;
    }
}
