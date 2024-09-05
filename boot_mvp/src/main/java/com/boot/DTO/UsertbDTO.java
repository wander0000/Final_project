package com.boot.DTO;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsertbDTO {
    private String userid;
    private String uuid;  // 중복 확인 후 가입 진행
    private String ppass;
    private String pname;
    private String email;
    private String phone;
    private String birth;  // yyyy-mm-dd
    private int gender;  // 1:남 2:여
    private int certno;  // ID or PW 인증번호
    private String account; //환불계좌 0905연주
    private LocalDateTime adate;  // default: now()
    private LocalDateTime mdate;  // default: now(), update시 현재 시각으로 자동 갱신
}