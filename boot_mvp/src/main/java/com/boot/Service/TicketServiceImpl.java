package com.boot.Service;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.DAO.PtHisttbDAO_4;
import com.boot.DAO.SelecGenretbDAO_4;
import com.boot.DAO.TicketDAO;
import com.boot.DAO.UsertbDAO_3;
import com.boot.DAO.UsertbDAO_4;
import com.boot.DTO.ReservetbDTO;
import com.boot.DTO.SelecGenretbDTO;
import com.boot.DTO.UsertbDTO;
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
	        throw new RuntimeException("포인트 이력조회 중 문제가 발생했습니다.", e);
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
	        throw new RuntimeException("포인트 이력조회 중 문제가 발생했습니다.", e);
	    }
	}

	@Override
	public List<ReservetbDTO> getTicketListForMonthly(int year, int month, int pageSize, int offset) {
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
	    	TicketDAO dao = sqlSession.getMapper(TicketDAO.class);
			return dao.getTicketListForMonthly(uuid,year,month,pageSize,offset);
			
	    } catch (Exception e) {
	        log.error("예외 발생: ", e);
	        throw new RuntimeException("포인트 이력조회 중 문제가 발생했습니다.", e);
	    }
	}

	@Override
	public int getTotalCountMonthly(int year, int month) {
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
			return dao.getTotalCountMonthly(uuid,year,month);
			
	    } catch (Exception e) {
	        log.error("예외 발생: ", e);
	        throw new RuntimeException("포인트 이력조회 중 문제가 발생했습니다.", e);
	    }
	}
	
	


	
	
}
