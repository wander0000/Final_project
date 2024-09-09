package com.boot.DAO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.boot.DTO.MileageDTO;

@Mapper
public interface MileageDAO_4 {
	public MileageDTO getUserMileage(@Param("uuid") String uuid); //마일리지 테이블 조회
	public void insertMileage(@Param("uuid") String uuid); //회원가입시 사용자 마일리지 생성
	public void updateMileage(@Param("uuid") String uuid);//포인트이력발생시 uuid를 통해 pthisttb를 참조하여 업데이트
	public void deleteMileage(@Param("uuid") String uuid);//회원탈퇴시 삭제
}



