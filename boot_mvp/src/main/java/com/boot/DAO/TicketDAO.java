package com.boot.DAO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.boot.DTO.PthistDTO;
import com.boot.DTO.ReserdtltbDTO;
import com.boot.DTO.ReservetbDTO;

@Mapper
public interface TicketDAO {
	//포인트이력 테이블 조회(기간별필터링, 페이징"pageSize>보여질 목록크기/ offset-건너뛸 row의 갯수)
	public List<ReservetbDTO> getTicketListForDays(@Param("uuid") String uuid,
										@Param("days") int days,
										@Param("pageSize") int pageSize, 
										@Param("offset") int offset); 
	//포인트이력 테이블 목록 개수 조회(기간별필터링)
	public int getTotalCountForDays(@Param("uuid") String uuid, @Param("days") int days); 
	//포인트이력 테이블 조회(특정기간, 페이징"pageSize>보여질 목록크기/ offset-건너뛸 row의 갯수)
	public List<ReservetbDTO> getTicketListForMonthly(@Param("uuid") String uuid, 
												@Param("year") int year, 
												@Param("month") int month,
												@Param("pageSize") int pageSize, 
												@Param("offset") int offset); 
	//포인트이력 테이블 목록 개수 조회(기간별필터링)
	public int getTotalCountMonthly(@Param("uuid") String uuid, @Param("year") int year,@Param("month") int month);
	public void deleteTicket(@Param("uuid") String uuid, @Param("reservenum") String reservenum);//예매취소
}
