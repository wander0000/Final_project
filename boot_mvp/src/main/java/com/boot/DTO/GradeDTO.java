package com.boot.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeDTO {
    private String uuid;  // 사용자 UUID
    private String grade;  // 현재 등급
    private int current_year;  // 승급년도
}