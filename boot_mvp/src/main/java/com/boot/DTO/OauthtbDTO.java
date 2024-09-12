package com.boot.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OauthtbDTO {
    private Long oauthid;        // 기본키, AUTO_INCREMENT
    private String oauthdiff;    // 네이버, 구글, 카카오 구분 (예: 'naver', 'google', 'kakao')
    private String oauthuniq;    // OAuth 제공자의 고유 ID 값 (예: 'mno9PgcgOyr3zpebnhk6o192KiwIl0bNEgdygizyGTs')
    private String userid;       // usertb의 userid와 연동, 신규일 경우 NULL 가능
    private String uuid;         // usertb와 중복되지 않는 UUID
    private String pname;        // 사용자 이름
    private String email;        // 이메일
    private String phone;        // 핸드폰 번호
    private String birth;        // 생년월일 (yyyy-mm-dd)
    private int gender;          // 1:남성, 2:여성
    private LocalDateTime adate; // 가입일자, 기본값: 현재 시각
    private LocalDateTime mdate; // 수정일자, 기본값: 현재 시각, 수정 시 자동 갱신
    
    private String oauthUserId;
    private String registrationId;
}
