package com.boot.Controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boot.DTO.CouponDTO;
import com.boot.DTO.GenreDTO;
import com.boot.DTO.MembershipDTO;
import com.boot.DTO.PthistDTO;
import com.boot.DTO.ReservetbDTO;
import com.boot.DTO.UsertbDTO;
import com.boot.Security.CustomUserDetails;
import com.boot.Security.CustomUserDetailsService;
import com.boot.Service.CouponService;
import com.boot.Service.GenreService;
import com.boot.Service.LoginService;
import com.boot.Service.MembershipService;
import com.boot.Service.TicketService;
import com.boot.Service.UserService_4;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MypageController {
	
	@Autowired
	private UserService_4 userService;

	@Autowired
	private LoginService loginservice;
	
	@Autowired
	private GenreService genreservice;
	
	@Autowired
	private MembershipService memService;
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private CouponService couponService;
	
	
	@RequestMapping("mypage")//마이페이지 메인으로
	public String mypage(Model model) {
		log.info("@# Mypage Main");		
		
		return "mypage/main";
	}
	
	@RequestMapping("mypage/ticket")//마이페이지 예매관리로
	public String ticket(Model model) {
		log.info("@# Mypage ticket");		
		
		return "mypage/ticket";
	}
	
	@GetMapping("/loadTicketList")//기간별 포인트 예매내역조회(ajax로 접근)
	@ResponseBody
	public Map<String, Object> loadTicketList(@RequestParam("days") int days, 
											@RequestParam("page") int page,
									        @RequestParam("pageSize") int pageSize) {
		
		  	int offset = (page - 1) * pageSize;//offset은 건너뛸 row의 갯수이기 때문에 1페이지 일때는 0, 2페이지 있을 때 페이지크기*1 
	        List<ReservetbDTO> ticketList = ticketService.getTicketListForDays(days, pageSize, offset);
	        int totalCount = ticketService.getTotalCountForDays(days);  // 전체 데이터 개수 조회

	        Map<String, Object> result = new HashMap<>();
	        result.put("ticketList", ticketList);
	        result.put("totalCount", totalCount);  // 전체 개수를 추가하여 반환
	        return result;
	}
	
	@GetMapping("/loadMonthlyTicketList")//특정기간 예매내역 조회(ajax로 접근)
	@ResponseBody
	public Map<String, Object> loadMonthlyTicketList(@RequestParam("keyword") String keyword,
												@RequestParam("year") int year,
												@RequestParam("month") int month,
												@RequestParam("page") int page,
										        @RequestParam("pageSize") int pageSize) {
		log.info("@# Mypage loadMonthlyTicketList로 접근");
		int offset = (page - 1) * pageSize;
        List<ReservetbDTO> ticketList = ticketService.getTicketListForMonthly(keyword, year, month, pageSize, offset);
        int totalCount = ticketService.getTotalCountMonthly(keyword, year, month);  // 전체 데이터 개수

        Map<String, Object> result = new HashMap<>();
        result.put("ticketList", ticketList);
        result.put("totalCount", totalCount);  // 전체 개수를 반환
        return result;
	}
	
	
	@GetMapping("/loadCanceledTicketList")//취소한 예매내역 조회(ajax로 접근)
	@ResponseBody
	public Map<String, Object> loadCanceledTicketList() {
		log.info("@# Mypage loadCanceledTicketList로 접근");
        List<ReservetbDTO> ticketList = ticketService.getTicketListCanceled();

        Map<String, Object> result = new HashMap<>();
        result.put("ticketList", ticketList);
        return result;
	}
	
	
	@RequestMapping("/cancelTicket")//예매 취소
	public ResponseEntity<String> cancelTicket(@RequestParam String reservenum) {
		log.info("@# Mypage cancelTicket");		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {//익명의 사용자가 아니라면
			
			ticketService.deleteTicket(reservenum);
		}
		return ResponseEntity.ok("예매내역이 성공적으로 취소되었습니다.");
	}
	
	
	@RequestMapping("mypage/membership")//마이페이지 멤버십로
	public String membership(Model model) {
		log.info("@# Mypage membership");		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (!(auth instanceof AnonymousAuthenticationToken)) {//익명의 사용자가 아니라면
       	
        	MembershipDTO membership = memService.getMembership();
	        model.addAttribute("membership", membership);//등급,포인트,포인트이력,마일리지 정보담기
	       
	    }
		return "mypage/membership";
	}
	
	@GetMapping("/filterPtHis")//기간별 포인트 이력조회(ajax로 접근)
	@ResponseBody
	public Map<String, Object> getPointHistory(@RequestParam("days") int days, 
											@RequestParam("page") int page,
									        @RequestParam("pageSize") int pageSize) {
		
		  	int offset = (page - 1) * pageSize;//offset은 건너뛸 row의 갯수이기 때문에 1페이지 일때는 0, 2페이지 있을 때 페이지크기*1 
	        List<PthistDTO> historyList = memService.getPointHistoryForDays(days, pageSize, offset);
	        int totalCount = memService.getTotalCountFiltered(days);  // 전체 데이터 개수 조회

	        Map<String, Object> result = new HashMap<>();
	        result.put("historyList", historyList);
	        result.put("totalCount", totalCount);  // 전체 개수를 추가하여 반환
	        return result;
	}
	
	@GetMapping("/filterPtHisBetween")//특정기간 포인트 이력조회(ajax로 접근)
	@ResponseBody
	public Map<String, Object> getPointHistoryBetween(@RequestParam("startDate") String startDateStr,
												@RequestParam("endDate")  String endDateStr,
												@RequestParam("page") int page,
										        @RequestParam("pageSize") int pageSize) {
		log.info("@# Mypage getPointHistoryBetween_ajax로 접근");
		
		// 시간 정보가 없을 경우 기본 시간 추가
	    if (!startDateStr.contains(" ")) {
	        startDateStr += " 00:00:00";
	    }
	    if (!endDateStr.contains(" ")) {
	        endDateStr += " 23:59:59";  // 끝날짜는 하루 끝 시간으로 설정
	    }
	    
	    Timestamp startDate = Timestamp.valueOf(startDateStr);
	    Timestamp endDate = Timestamp.valueOf(endDateStr);
	    
		int offset = (page - 1) * pageSize;
        List<PthistDTO> historyList = memService.getPointHistoryBetween(startDate, endDate, pageSize, offset);
        int totalCount = memService.getTotalCountBetween(startDate, endDate);  // 전체 데이터 개수

        Map<String, Object> result = new HashMap<>();
        result.put("historyList", historyList);
        result.put("totalCount", totalCount);  // 전체 개수를 반환
        return result;
	}
	
	@RequestMapping("mypage/movieStory")//마이페이지 무비스토리로
	public String movieStory(Model model) {
		log.info("@# Mypage movieStory");		
		
		return "mypage/movieStory";
	}
	
	
	@RequestMapping("mypage/coupon")//마이페이지 쿠폰/할인권으로
	public String coupon(Model model) {
		log.info("@# Mypage coupon");		
		
		return "mypage/coupon";
	}
	
	
	@GetMapping("/getCouponList")//기간별 포인트 이력조회(ajax로 접근)
	@ResponseBody
	public Map<String, Object> getcouponList(@RequestParam("type") String type, 
											@RequestParam("acrec") String acrec, 
											@RequestParam("page") int page,
									        @RequestParam("pageSize") int pageSize) {
		
		  	int offset = (page - 1) * pageSize;//offset은 건너뛸 row의 갯수이기 때문에 1페이지 일때는 0, 2페이지 있을 때 페이지크기*1 
	        List<CouponDTO> couponList = couponService.getCouponList(pageSize, offset,type,acrec);
	        int totalCount = couponService.getTotalCountCoupon(type,acrec);  // 전체 데이터 개수 조회

	        Map<String, Object> result = new HashMap<>();
	        result.put("couponList", couponList);
	        result.put("totalCount", totalCount);  // 전체 개수를 추가하여 반환
	        return result;
	}
	
//	@PostMapping("/generateCoupon")
//	@ResponseBody
//	public ResponseEntity<String> generateCoupon(@RequestParam String couponno, RedirectAttributes redirectAttributes) {
//	    log.info("@# Mypage generateCoupon, 쿠폰 번호: " + couponno);
//
//	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//	    if (!(auth instanceof AnonymousAuthenticationToken)) {
//	        couponService.generateCoupon(couponno);
//	        redirectAttributes.addFlashAttribute("message", "쿠폰이 성공적으로 등록되었습니다.");
//	    }
//	    return ResponseEntity.ok("쿠폰이 성공적으로 등록되었습니다.");
//	}

	
	@PostMapping("/generateCoupon")
	@ResponseBody
	public ResponseEntity<String> generateCoupon(@RequestParam String couponno) {
	    log.info("@# Mypage generateCoupon, 쿠폰 번호: " + couponno);

	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (!(auth instanceof AnonymousAuthenticationToken)) {
	        try {
	            couponService.generateCoupon(couponno);
	            return ResponseEntity.ok("쿠폰이 성공적으로 등록되었습니다.");
	        } catch (Exception e) {
	            return ResponseEntity.badRequest().body(e.getMessage());
	        }
	    }
	    return ResponseEntity.badRequest().body("인증되지 않은 사용자입니다.");
	}
	
	@RequestMapping("mypage/userInfo")//마이페이지 회원정보으로
	public String userInfo(Model model) {
		log.info("@# Mypage userInfo");	
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    if (!(auth instanceof AnonymousAuthenticationToken)) {
		    	CustomUserDetails userDetails = (CustomUserDetails)auth.getPrincipal(); // 로그인된 사용자의 ID 가져오기
	        	String loginedUserId = userDetails.getUserId();  // 사용자 ID 가져오기
	        	
		        UsertbDTO userdto = loginservice.getUserById(loginedUserId);
		        log.info("@# Mypage userdto=>"+userdto);	
		        
		        userdto.setPpass(null); // 비밀번호는 숨김
		        model.addAttribute("user", userdto);
		        
		        String genreList = userService.getSelectGenre();
		        model.addAttribute("genreList", genreList);//선호장르 정보 담기
		        
		        List<GenreDTO> genres = genreservice.getAllGenres();
		        model.addAttribute("genres", genres);//장르참조테이블 정보 담기
		    }
		
		return "mypage/userInfo";
	}
	
	@RequestMapping("mypage/withdraw")//마이페이지 회원탈퇴로
	public String Withdraw(Model model) {
		log.info("@# Mypage Withdraw");		
		
		return "mypage/withdraw";
	}


}
