package com.boot.DTO;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MembershipDTO {
    private String grade;  // 멤버십 등급
    private List<PthistDTO> Pthist;  // 포인트 이용내역 리스트 
    private PointDTO point;  // 포인트상세(현재포인트, 총 사용포인트, 총 적립 포인트, 최근수정일, 최초생성일)
    private  MileageDTO mileage;  // 마일리지상세 (현재 마일리지, 현재달 적립마일리지, 최근수정일, 최초생성일)
}