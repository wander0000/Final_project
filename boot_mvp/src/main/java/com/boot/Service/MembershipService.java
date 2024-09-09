package com.boot.Service;

import com.boot.DTO.MembershipDTO;

public interface MembershipService {
	//멤버십 생성(등급, 포인트, 마일리지)-회원가입시
	public void insertMembership(String uuid);
	//멤버십 조회(등급, 포인트, 마일리지)
	public MembershipDTO getMembership();
	//포인트 업데이트(포인트이력이 생기면 등급, 포인트, 마일리지이 함께 변동됨)
	public void updateMembership();
	//포인트 삭제(탈퇴할 때 등급, 포인트, 마일리지이 함께 삭제)
	public void deleteMembership();
	

}
