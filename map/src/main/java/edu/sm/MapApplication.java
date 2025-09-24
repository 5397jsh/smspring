// MapApplication.java (가장 단순한 기본 형태로 복귀)

package edu.sm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 추가 옵션 없이, 이것 하나만 둡니다.
public class MapApplication {

    public static void main(String[] args) {
        SpringApplication.run(MapApplication.class, args);
    }
}