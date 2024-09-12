package com.boot.Service;

import java.sql.Timestamp;
import java.util.List;

import com.boot.DTO.MembershipDTO;
import com.boot.DTO.PthistDTO;

public interface MembershipService {
	//멤버십 생성(등급, 포인트, 마일리지)-회원가입시
	public void insertMembership(String uuid);
	//멤버십 조회(등급, 포인트, 마일리지)
	public MembershipDTO getMembership();
	//등급 조회
	public String getGrade();
	//포인트 업데이트(포인트이력이 생기면 등급, 포인트, 마일리지이 함께 변동됨)
	public void updateMembership();
	//포인트 삭제(탈퇴할 때 등급, 포인트, 마일리지 함께 삭제)
	public void deleteMembership();
	//포인트 이력 목록조회(필터링:기간별, page는 페이지 크기,offset은 건너뛸 row의 갯수)
	public List<PthistDTO> getPointHistoryForDays(int days,int pageSize,int offset);
	//포인트 이력 목록 count(필터링:기간별)
	public int getTotalCountFiltered(int days);
	//포인트 이력 목록조회(필터링:특정기간)
	public List<PthistDTO> getPointHistoryBetween(Timestamp startDate, Timestamp endDate, int pageSize,int offset);
	//포인트 이력 목록 count(필터링:기간별)
	public int getTotalCountBetween(Timestamp startDate, Timestamp endDate);
	

}
