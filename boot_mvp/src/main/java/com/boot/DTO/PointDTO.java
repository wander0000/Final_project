package com.boot.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointDTO {
    private String uuid;  // 사용자 UUID
    private int onhqt;  // 현재 포인트 
    private int recqt;  //  총 획득 포인트 (적립 포인트)
    private int issqt;  //  총 사용 포인트 (차감 포인트)
    private LocalDateTime adate; //등록일자 (default: now()) 
    private LocalDateTime mdate;  //update시 현재 시각now()으로 갱신
}