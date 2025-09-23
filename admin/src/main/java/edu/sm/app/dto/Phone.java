package edu.sm.app.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Phone {
    private String phone_code;
    private int month;
    private String phone_name;
    private int price;
}
