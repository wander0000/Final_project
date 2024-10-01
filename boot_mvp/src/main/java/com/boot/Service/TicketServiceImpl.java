package com.boot.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.boot.DAO.TicketDAO;
import com.boot.DTO.ReservetbDTO;
import com.boot.Security.CustomUserDetails;
import com.boot.Security.CustomUserDetailsService;

import lombok.extern.slf4j.Slf4j;

@Service("TicketService")
@Slf4j
public class TicketServiceImpl implements TicketService {
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private CustomUserDetailsService userService;

	@Override
	public List<ReservetbDTO> getTicketListForDays(int days, int pageSize, int offset) {
		log.info("getTicketListForDays 서비스임플");
		
		// 1. 로그인 여부 확인
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    
	    if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
	        throw new RuntimeException("로그인된 사용자가 없습니다.");
	    }
	    
	    // 2. 로그인된 사용자 정보 가져오기
//		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String uuid = userService.getUuidFromAuthenticatedUser();  // 사용자 UUID 가져오기

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
//		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String uuid = userService.getUuidFromAuthenticatedUser();  // 사용자 UUID 가져오기

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
//		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String uuid = userService.getUuidFromAuthenticatedUser();  // 사용자 UUID 가져오기

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
//		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String uuid = userService.getUuidFromAuthenticatedUser();  // 사용자 UUID 가져오기

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
//		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String uuid = userService.getUuidFromAuthenticatedUser();  // 사용자 UUID 가져오기

	    try { 
	    	TicketDAO dao = sqlSession.getMapper(TicketDAO.class);
            return dao.getTicketListCanceled(uuid);
	       
	    } catch (Exception e) {
	        log.error("예외 발생: ", e);
	        throw new RuntimeException("예매취소 목록 조회 중 문제가 발생했습니다", e);
	    }
	}

	
	//===================== 예매취소 하기 시작(240923)=======================================================
	
	private static final String API_URL = "https://api.iamport.kr";
	private static final String IMP_KEY = "7705461586021156";   // 아임포트 REST API 키
	private static final String IMP_SECRET = "F1OJZ5t8GHk1epZmap1A3IVBzPF5WsmudjCRcKSani8o0emlomsFkgl80ExLohjtoZSgkJQGoFcoPvFQ"; // 아임포트 REST API Secret
	 
	// 1. 아임포트 Access Token을 가져오는 메서드
    public String getAccessToken() {
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + "/users/getToken";

        Map<String, String> params = new HashMap<>();
        params.put("imp_key", IMP_KEY);
        params.put("imp_secret", IMP_SECRET);

        ResponseEntity<Map> response = restTemplate.postForEntity(url, params, Map.class);
        
        if (response.getStatusCode() == HttpStatus.OK) {
            Map body = response.getBody();
            Map responseMap = (Map) body.get("response");
            return (String) responseMap.get("access_token");
        }
        return null;
    }
	
 // 결제 취소 요청 메서드와 DB 업데이트 추가
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean cancelPayment(String reservenum) {
        String accessToken = getAccessToken();
        if (accessToken == null) {
            return false;
        }

        String url = API_URL + "/payments/cancel";
        RestTemplate restTemplate = new RestTemplate();

        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        // 결제 취소 요청 데이터 설정
        Map<String, Object> params = new HashMap<>();
        String merchant_uid = "movie_ticket_" +reservenum;
        params.put( "merchant_uid", merchant_uid ); // 취소할 결제건의 고유번호
//        params.put("amount", amount);  // 취소할 금액 (부분 취소의 경우)

        // HTTP 엔티티 생성
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(params, headers);

        // API 호출
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            try {
                // 로그인 여부 확인
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

                if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
                    throw new RuntimeException("로그인된 사용자가 없습니다.");
                }
            	String uuid = userService.getUuidFromAuthenticatedUser();  // 사용자 UUID 가져오기
                // DAO를 통한 DB 업데이트
                TicketDAO dao = sqlSession.getMapper(TicketDAO.class);

                // 1. 예매 취소 (reservetb 데이터)
//                dao.deleteTicket(reservenum);
                // 2. 예매 상세 정보 취소 (reserdtltb 데이터)
//                dao.deleteTicketDetail(reservenum);
                
                
                // 프로시져 호출 (예매정보 취소로, 포인트, 마일리지, garde 업데이트, 좌석반환)
                dao.Call_deleteTicket(uuid,reservenum);
                log.error("Call_deleteTicket 호출할 때 가지고 가는 uuid:"+uuid+" reservenum:"+reservenum);

            } catch (Exception e) {
                log.error("DB 처리 중 예외 발생: ", e);
                throw new RuntimeException("예매 취소 중 문제가 발생했습니다", e);
            }

            return true; // 결제 취소 및 DB 업데이트 성공
        } else {
            log.error("결제 취소 실패: " + response.getBody().get("message"));
            return false; // 결제 취소 실패
        }
    }

	


	
	
}
