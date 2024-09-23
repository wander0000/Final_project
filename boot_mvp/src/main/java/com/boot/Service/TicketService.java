package com.boot.Service;

import java.util.HashMap;
import java.util.List;

import com.boot.DTO.ReservetbDTO;

public interface TicketService {
	//예매내역 목록조회(필터링:기간별, page는 페이지 크기,offset은 건너뛸 row의 갯수)
	public List<ReservetbDTO> getTicketListForDays(int days,int pageSize,int offset);
	//예매내역 목록 count(필터링:기간별)
	public int getTotalCountForDays(int days);
	//예매내역 목록조회(필터링:특정년월)
	public List<ReservetbDTO> getTicketListForMonthly(String keyword,int year, int month, int pageSize,int offset);
	//예매내역 목록 count(필터링:특정년월)
	public int getTotalCountMonthly(String keyword,int year, int month);
	//예매취소 내역 목록조회
	public List<ReservetbDTO> getTicketListCanceled();
	public String getAccessToken();//아임포트 결제취소용 엑세스토큰
	public boolean cancelPayment(String reservenum);//예매취소
	
}