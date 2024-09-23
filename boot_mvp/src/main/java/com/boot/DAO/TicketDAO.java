package com.boot.DAO;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.boot.DTO.ReservetbDTO;

@Mapper
public interface TicketDAO {
	//예매내역 테이블 조회(기간별필터링, 페이징"pageSize>보여질 목록크기/ offset-건너뛸 row의 갯수)
	public List<ReservetbDTO> getTicketListForDays(@Param("uuid") String uuid,
													@Param("days") int days,
													@Param("pageSize") int pageSize, 
													@Param("offset") int offset); 
	//예매내역 목록 개수 조회(기간별필터링)
	public int getTotalCountForDays(@Param("uuid") String uuid, @Param("days") int days); 
	//예매일별 특정연월 예매내역 조회(페이징"pageSize>보여질 목록크기/ offset-건너뛸 row의 갯수)
	public List<ReservetbDTO> getTicketListByReservationDateForMonth(@Param("uuid") String uuid, 
																	@Param("year") int year, 
																	@Param("month") int month,
																	@Param("pageSize") int pageSize, 
																	@Param("offset") int offset); 
	//예매일별 특정연월 예매내역 개수 조회
	public int getTotalCountByReservationDateForMonth(@Param("uuid") String uuid, @Param("year") int year, @Param("month") int month);
	//상영일별 특정연월 예매내역 조회(페이징"pageSize>보여질 목록크기/ offset-건너뛸 row의 갯수)
	public List<ReservetbDTO> getTicketListByScreeningDateForMonth(@Param("uuid") String uuid, 
																@Param("year") int year, 
																@Param("month") int month,
																@Param("pageSize") int pageSize,
																@Param("offset") int offset); 
	//상영일별 특정연월 예매내역 개수 조회
	public int getTotalCountByScreeningDateForMonth(@Param("uuid") String uuid, @Param("year") int year, @Param("month") int month);
	public List<ReservetbDTO> getTicketListCanceled(@Param("uuid") String uuid);//예매취소 내역조회
	public void deleteTicket(@Param("reservenum") String reservenum);//예매취소(reservetb 데이터)
	public void deleteTicketDetail(@Param("reservenum") String reservenum);//예매취소(reserdtltb 데이터)
	public void Call_deleteTicket(@Param("v_uuid") String v_uuid, @Param("v_reservenum") String v_reservenum); //예매 취소 포인트 처리(포인트 추가 및 차감, 좌석반환,마일리지 등급 업데이트-프로시져로)
}
