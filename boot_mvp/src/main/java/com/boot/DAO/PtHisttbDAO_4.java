package com.boot.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.boot.DTO.PthistDTO;

@Mapper
public interface PtHisttbDAO_4 {
	public List<PthistDTO> getUserPtHis(@Param("uuid") String uuid); //포인트이력 테이블 조회
	public void updatePtHis(PthistDTO dto);//포인트적립 또는 포인트 사용
	public void deletePtHis(@Param("uuid") String uuid);//회원탈퇴시 삭제
}



