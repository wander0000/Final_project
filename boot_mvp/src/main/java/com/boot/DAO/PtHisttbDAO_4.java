package com.boot.DAO;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.boot.DTO.PthistDTO;

@Mapper
public interface PtHisttbDAO_4 {
	//포인트이력 테이블 조회(기간별필터링, 페이징"pageSize>보여질 목록크기/ offset-건너뛸 row의 갯수)
	public List<PthistDTO> getUserPtHis(@Param("uuid") String uuid,
										@Param("days") int days,
										@Param("pageSize") int pageSize, 
										@Param("offset") int offset); 
	//포인트이력 테이블 목록 개수 조회(기간별필터링)
	public int getTotalCountFiltered(@Param("uuid") String uuid, @Param("days") int days); 
	//포인트이력 테이블 조회(특정기간, 페이징"pageSize>보여질 목록크기/ offset-건너뛸 row의 갯수)
	public List<PthistDTO> getUserPtHisBetween(@Param("uuid") String uuid, 
												@Param("startDate") Timestamp startDate, 
												@Param("endDate") Timestamp endDate,
												@Param("pageSize") int pageSize, 
												@Param("offset") int offset); 
	//포인트이력 테이블 목록 개수 조회(기간별필터링)
	public int getTotalCountBetween(@Param("uuid") String uuid, @Param("startDate") Timestamp startDate,@Param("endDate") Timestamp endDate);
	public void updatePtHis(PthistDTO dto);//포인트적립 또는 포인트 사용
	public void deletePtHis(@Param("uuid") String uuid);//회원탈퇴시 삭제
}


