package com.boot.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MileageDTO {
    private String uuid;  // 사용자 UUID
    private int onhqt;  // 현재 마일리지 (전체 적립) 
    private int recqt;  //  현재 달의 총 적립 마일리지
    private LocalDateTime adate; //등록일자 (default: now()) 
    private LocalDateTime mdate;  //update시 현재 시각now()으로 갱신
}