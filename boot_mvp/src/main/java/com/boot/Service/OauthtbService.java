package com.boot.Service;

import com.boot.DTO.OauthtbDTO;


public interface OauthtbService {
	
	public void oauthInsertUser(OauthtbDTO oauthtbDTO); // 회원 가입
	public int oauthCheckUserid(String userid);  // ID 중복 체크 메소드
	public boolean existsByUuid(String uuid);    // UUID 중복 체크 메소드
    public boolean oauthCheckNewUser(String oauthUserId, String registrationId);  // 기존회원여부 확인
    OauthtbDTO oauthGetUserById(String userid); // ID로 회원 정보 가져오기
    public OauthtbDTO oauthGetUserByuniq(String oauthuniq); // oauthuniq로 회원 정보 가져오기
    public int oauthGetUserByuniqcnt(String oauthuniq); // oauthuniq로 회원 정보 가져오기

}
