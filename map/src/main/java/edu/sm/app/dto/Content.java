package edu.sm.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Entity; // Spring Boot 3.x에 맞는 import
import jakarta.persistence.Id;       // Spring Boot 3.x에 맞는 import
import jakarta.persistence.Table;   // Spring Boot 3.x에 맞는 import

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity // 이 클래스가 JPA가 관리하는 '엔티티'임을 선언합니다.
@Table(name = "content") // 이 엔티티가 데이터베이스의 'content' 테이블에 연결되었음을 알려줍니다.
public class Content {

    @Id // 'target' 필드가 이 테이블의 기본 키(Primary Key)임을 선언합니다.
    private int target;

    private String title;
    private String img;
    private double lat;
    private double lng;
    private int loc;
    private String details;
}