package com.boot.DAO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GradeDAO_4 {
	public String getUserGrade(@Param("uuid") String uuid); //등급조회
	public void insertGrade(@Param("uuid") String uuid); //회원가입시 사용자 grade 생성 - default Welcome
	//마일리지테이블 참조하여 어느포인트 이상이면 업데이트, 어떤 로직으로 할지 고민중
	public void updateGrade(@Param("uuid") String uuid);
	public void deleteGrade(@Param("uuid") String uuid);//회원탈퇴시 grade 삭제
	
}



