package com.boot.DAO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.boot.DTO.PointDTO;

@Mapper
public interface PointDAO_4 {
	public PointDTO getUserPoint(@Param("uuid") String uuid); //포인트 테이블 조회
	public void insertPoint(@Param("uuid") String uuid); //회원가입시 사용자 포인트 생성
	public void updatePoint(@Param("uuid") String uuid);//포인트이력발생시 uuid를 통해 pthisttb를 참조하여 업데이트
	public void deletePoint(@Param("uuid") String uuid);//회원탈퇴시 사용자 포인트 삭제
}



