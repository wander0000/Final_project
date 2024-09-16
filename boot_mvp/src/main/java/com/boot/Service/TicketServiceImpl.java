package com.boot.Service;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boot.DAO.TicketDAO;
import com.boot.DTO.ReservetbDTO;
import com.boot.Security.CustomUserDetails;

import lombok.extern.slf4j.Slf4j;

@Service("TicketService")
@Slf4j
public class TicketServiceImpl implements TicketService {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<ReservetbDTO> getTicketListForDays(int days, int pageSize, int offset) {
		log.info("getTicketListForDays 서비스임플");
		
		// 1. 로그인 여부 확인
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    
	    if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
	        throw new RuntimeException("로그인된 사용자가 없습니다.");
	    }
	    
	    // 2. 로그인된 사용자 정보 가져오기
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String uuid = userDetails.getUuId();  // 사용자 ID 가져오기

	    try { 
	    	TicketDAO dao = sqlSession.getMapper(TicketDAO.class);
			return dao.getTicketListForDays(uuid,days,pageSize,offset);
			
	    } catch (Exception e) {
	        log.error("예외 발생: ", e);
	        throw new RuntimeException("예매 목록 조회 중 문제가 발생했습니다", e);
	    }
	}

	@Override
	public int getTotalCountForDays(int days) {
		log.info("getTotalCountForDays 서비스임플");
		
		// 1. 로그인 여부 확인
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    
	    if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
	        throw new RuntimeException("로그인된 사용자가 없습니다.");
	    }
	    
	    // 2. 로그인된 사용자 정보 가져오기
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String uuid = userDetails.getUuId();  // 사용자 ID 가져오기

	    try { 
	    	TicketDAO dao = sqlSession.getMapper(TicketDAO.class);
			return dao.getTotalCountForDays(uuid,days);
			
	    } catch (Exception e) {
	        log.error("예외 발생: ", e);
	        throw new RuntimeException("예매 목록 조회 중 문제가 발생했습니다", e);
	    }
	}

	@Override
	public List<ReservetbDTO> getTicketListForMonthly(String keyword, int year, int month, int pageSize, int offset) {
		log.info("getTicketListForMonthly 서비스임플");
		
		// 1. 로그인 여부 확인
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    
	    if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
	        throw new RuntimeException("로그인된 사용자가 없습니다.");
	    }
	    
	    // 2. 로그인된 사용자 정보 가져오기
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String uuid = userDetails.getUuId();  // 사용자 ID 가져오기

    	 try {
	        // DAO 객체 가져오기
	        TicketDAO dao = sqlSession.getMapper(TicketDAO.class);

	        // keyword에 따라 다른 메소드 호출
	        if ("예매일".equals(keyword)) {
	            return dao.getTicketListByReservationDateForMonth(uuid, year, month, pageSize, offset);
	        } else if ("상영일".equals(keyword)) {
	            return dao.getTicketListByScreeningDateForMonth(uuid, year, month, pageSize, offset);
	        } else {
	            throw new IllegalArgumentException("유효하지 않은 keyword입니다: " + keyword);
	        }

	    } catch (Exception e) {
	        log.error("예외 발생: ", e);
	        throw new RuntimeException("예매 목록 조회 중 문제가 발생했습니다.", e);
	    }
	}

	@Override
	public int getTotalCountMonthly(String keyword, int year, int month) {
		log.info("getTotalCountMonthly 서비스임플");
		
		// 1. 로그인 여부 확인
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    
	    if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
	        throw new RuntimeException("로그인된 사용자가 없습니다.");
	    }
	    
	    // 2. 로그인된 사용자 정보 가져오기
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String uuid = userDetails.getUuId();  // 사용자 ID 가져오기

	    try { 
	    	TicketDAO dao = sqlSession.getMapper(TicketDAO.class);
	    	  // keyword에 따라 다른 메소드 호출
	        if ("예매일".equals(keyword)) {
	            return dao.getTotalCountByReservationDateForMonth(uuid, year, month);
	        } else if ("상영일".equals(keyword)) {
	            return dao.getTotalCountByScreeningDateForMonth(uuid, year, month);
	        } else {
	            throw new IllegalArgumentException("유효하지 않은 keyword입니다: " + keyword);
	        }
			
	    } catch (Exception e) {
	        log.error("예외 발생: ", e);
	        throw new RuntimeException("예매 목록 조회 중 문제가 발생했습니다", e);
	    }
	}

	@Override
	public List<ReservetbDTO> getTicketListCanceled() {

		// 1. 로그인 여부 확인
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    
	    if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
	        throw new RuntimeException("로그인된 사용자가 없습니다.");
	    }
	    
	    // 2. 로그인된 사용자 정보 가져오기
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String uuid = userDetails.getUuId();  // 사용자 ID 가져오기

	    try { 
	    	TicketDAO dao = sqlSession.getMapper(TicketDAO.class);
            return dao.getTicketListCanceled(uuid);
	       
	    } catch (Exception e) {
	        log.error("예외 발생: ", e);
	        throw new RuntimeException("예매취소 목록 조회 중 문제가 발생했습니다", e);
	    }
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteTicket(String reservenum) {
		// 1. 로그인 여부 확인
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    
	    if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
	        throw new RuntimeException("로그인된 사용자가 없습니다.");
	    }
	    
	    try { 
	    	TicketDAO dao = sqlSession.getMapper(TicketDAO.class);
	    	// 2. 예매취소하기(reservetb 데이터)
            dao.deleteTicket(reservenum);
            // 2. 예매취소하기(reserdtltb 데이터)
            dao.deleteTicketDetail(reservenum);
	       
	    } catch (Exception e) {
	        log.error("예외 발생: ", e);
	        throw new RuntimeException("예매취소 중 문제가 발생했습니다", e);
	    }
		
	}
	
	


	
	
}
