package com.boot.Controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boot.Security.CustomUserDetails;
import com.boot.Security.CustomUserDetailsService;
import com.boot.Service.CouponService_2;
import com.boot.Service.MovieService;
import com.boot.Service.MovieService_2;
import com.boot.Service.Movieinfotb_vService_2;
import com.boot.Service.PointService_2;
import com.boot.Service.PricetbService_2;
import com.boot.Service.ReserdtltbService_2;
import com.boot.Service.ReservetbService_2;
import com.boot.Service.ScreenService_2;
import com.boot.Service.TheaterService_2;

import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@Slf4j
@RequestMapping("/ticketing")
public class PayController {
	
	@Autowired
	private ScreenService_2 screenService;
	
	@Autowired
	private ReservetbService_2 reserveService;
	
	@Autowired
	private ReserdtltbService_2 reserdtlService;
	
	@Autowired
	private PricetbService_2 priceService;
	
	//@Autowired
	//private MovieService_2 movieService;
	
	@Autowired
	private Movieinfotb_vService_2 movieinfotb_vService;
	
	@Autowired
	private TheaterService_2 theaterService;
	
	@Autowired
	private CouponService_2 couponService;
	
	@Autowired
	private PointService_2 pointService;
	
	@Autowired
	private CustomUserDetailsService userService;
	
	/* sms 전송을 위한 세팅 */
	private DefaultMessageService messageService = NurigoApp.INSTANCE.initialize("NCSEPSQXUWDO2WGS", "BJUJJHURG1BIKIUKNKJLH1XIIQWPIYSL", "https://api.coolsms.co.kr");
	
	public PayController() {
        // 반드시 계정 내 등록된 유효한 API 키, API Secret Key를 입력해주셔야 합니다!
      this.messageService = NurigoApp.INSTANCE.initialize("NCSEPSQXUWDO2WGS", "BJUJJHURG1BIKIUKNKJLH1XIIQWPIYSL", "https://api.coolsms.co.kr");
    }
    
    /* sms 전송을 위한 세팅 */
	
	/* 카카오페이 */
	@RequestMapping("/kakao_ajax")
	public String kakao(Model model) {
		log.info("kakao");
		
		return "pay/kakaobutton_ajax";
	}
	/* 카카오페이 */
	
	/* 토스페이 */
	@RequestMapping("/toss_ajax")
	public String toss() {
		log.info("toss");
		return "pay/tossbutton_ajax";
	}
	/* 토스페이 */
	
	/* 카카오페이 */
	@RequestMapping("/card_ajax")
	public String card(Model model) {
		log.info("card");
		
		return "pay/cardbutton_ajax";
	}
	/* 카카오페이 */
	
	@RequestMapping("/reserve")
	@Transactional
	@ResponseBody
	public void reserve(@RequestParam HashMap<String, String> param, HttpSession session) { //예매 정보 등록
		log.info("@# reserve");
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			log.info("@# param: " + param);
			HashMap<String, String> params = (HashMap<String, String>) session.getAttribute("movieInfo");
			log.info("@# params: " + params);
			//CustomUserDetails userDetails = (CustomUserDetails)auth.getPrincipal(); // 로그인된 사용자의 ID 가져오기
			//String uuid = userDetails.getUuId();
			String uuid = userService.getUuidFromAuthenticatedUser();
			params.put("uuid", uuid);
	
			/* 예매 번호 */
			/*
			Date now = new Date();
			SimpleDateFormat formatter1 = new SimpleDateFormat("MMdd");
			SimpleDateFormat formatter2 = new SimpleDateFormat("HHmm");
			String reservenum_1 = formatter1.format(now);
			String reservenum_2 = formatter2.format(now);
			long millis = System.currentTimeMillis(); // 밀리초 단위의 현재 시간
			String millisStr = Long.toString(millis); // long을 문자열로 변환
			String num_3 = millisStr.substring(millisStr.length() - 4); // (총길이 - 4)번째 인덱스부터 끝까지의 문자열을 추출
			
			int reservenum_4 = (int) ( Integer.parseInt(reservenum_1) + Integer.parseInt(reservenum_2) + System.currentTimeMillis());
			String num_4 = Integer.toString(reservenum_4).substring(Integer.toString(reservenum_4).length() - 3); //(총길이 - 3)번째 인덱스부터
			
			String reservenum = reservenum_1 + "-" + reservenum_2 + "-" + num_3 + "-" + num_4;
			params.put("reservenum", reservenum);
			*/
			/* 예매 번호 */
			params.put("reservenum", param.get("reservenum"));
			/* reservetb */
			// tmember = 성인 + 청소년 + 경로 + 장애인
			int tmember = Integer.parseInt(params.get("adult")) + Integer.parseInt(params.get("youth")) + Integer.parseInt(params.get("old")) + Integer.parseInt(params.get("disable"));
			params.put("tmember", tmember+"");
			params.put("tprice", params.get("calc"));
			reserveService.insertReserve(params);
			/* reservetb */
			
			/* reserdtltb */
			String sit[] = params.get("seats").split(",");
			params.put("priceno", params.get("pricetype"));
			ArrayList<Integer> ptype = new ArrayList<>();
			ptype.add(Integer.parseInt(params.get("adult"))); // 2명 가격 11000
			ptype.add(Integer.parseInt(params.get("youth"))); // 0명 가격 11000
			ptype.add(Integer.parseInt(params.get("old"))); // 0명 가격 10000
			ptype.add(Integer.parseInt(params.get("disable"))); // 0명 가격 5000
			
			int price = 0;
			ArrayList<Integer> pricedto = priceService.selectprice(params);
			ArrayList<Integer> pricedate = new ArrayList<>();
			//총 인원으로 각 가격 타입별 금액 계산
			for(int i = 0; i < pricedto.size(); i++) {
				price = pricedto.get(i);
				
				for(int j = 0; j < ptype.get(i); j++) {
					pricedate.add(price);
				}
			}
			
			int len = sit.length;
			for (int i = 0; i < len; i++) {
				params.put("seat", sit[i]);
				params.put("price", pricedate.get(i)+"");
				reserdtlService.insertReserdtl(params);
			}
			/* reserdtltb */
			
			/* 좌석 숫자만큼 전체 좌석에서 차감 */
			params.put("len", len+"");
			screenService.Seat_deduction(params);
			/* 좌석 숫자만큼 전체 좌석에서 차감 */
			
			/* 쿠폰 및 할인권 사용 */
			if(!param.get("couponno").equals("none")) {
				params.put("couponno", param.get("couponno"));
				
				couponService.usingCoupon(params); //사용된 쿠폰으로 변경
			}
			
			/* 포인트 사용 */
			params.put("t_point", param.get("t_point"));
			params.put("t_calc", param.get("t_calc"));
			pointService.Call_movie_payment_points(params);
			
			/* 문자 전송을 위한 세팅 (금액 문제로 현재는 주석 처리) */
			//선택한 영화 제목
			
			String title = movieinfotb_vService.getTitleRating(params).getMovienm();
			String theaternm = theaterService.gettheaternm(params);
			if(title.length() > 5) {
				title = title.substring(0, 5).trim()+"..";
			}
			
			//params.get("starttime") -> yyyy-MM-dd HH:mm 으로 변경
			// 문자열을 LocalDateTime으로 변환
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
	        LocalDateTime dateTime = LocalDateTime.parse(params.get("starttime"), formatter);
			// 분 단위까지 잘라내기
	        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	        String formattedDateTime = dateTime.format(newFormatter);
	        // params.get("starttime") -> yyyy-MM-dd HH:mm 으로 변경
	        
			//log.info("@# SMS: 예매완료\nMVP-"+theaternm+"\n"+title+"("+len+"매)"+"\n예매 번호 : [" + reservenum + "]\n"+formattedDateTime);
			
			Message message = new Message();
			String phone = userService.getPhoneFromAuthenticatedUser().replaceAll("-", "");//userDetails.getPhone().replaceAll("-", "");
			log.info("@# phone: " + phone);
	        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
	        message.setFrom("01049190758");
	        message.setTo(phone);
	        message.setText("예매완료\nMVP-"+theaternm+"\n"+title+"("+len+"매)"+"\n예매 번호 : [" + param.get("reservenum") + "]\n"+formattedDateTime);
			
	        //SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
	        /* 문자 전송을 위한 세팅 */
			
		} catch (Exception e) {
	        log.error("Error during reservation process", e);
	        throw e;
	    }
	}
	
	@RequestMapping("/paycompleted")
	public String paycompleted(HttpSession session, Model model) { //예매 완료 화면
		log.info("@# paycompleted");
		
		// 세션에 등록한 값 사용
		HashMap<String, String> param = (HashMap<String, String>) session.getAttribute("movieInfo");
		model.addAttribute("movieinfo", screenService.selectmovieinfo(param));
		
		model.addAttribute("adult", param.get("adult"));
		model.addAttribute("youth", param.get("youth"));
		model.addAttribute("old", param.get("old"));
		model.addAttribute("disable", param.get("disable"));
		model.addAttribute("seats", param.get("seats"));
		
		return "ticketing/paycompleted";
	}
	
	@RequestMapping("couponPopUp/{type}")
	public String couponPopUp(@PathVariable("type") String type, @RequestParam HashMap<String, String> param, Model model) {
		log.info("@# couponPopUp");
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//CustomUserDetails userDetails = (CustomUserDetails)auth.getPrincipal(); // 로그인된 사용자의 ID 가져오기
		//String uuid = userDetails.getUuId();
		String uuid = userService.getUuidFromAuthenticatedUser();
		param.put("uuid", uuid);
		param.put("type", type);
		String title = type.equals("D") ? "할인권" : "쿠폰";
		
		model.addAttribute("title", title);
		model.addAttribute("gubun", type);
		model.addAttribute("pid", type.equals("D")?"discount":"coupon");
		model.addAttribute("coupons", couponService.getALLCoupon(param));
		
		return "common/couponPopUp";
	}
	
	@RequestMapping("/pointPopUp")
	public String pointPopUp(@RequestParam HashMap<String, String> param, Model model) {
		log.info("@# pointPopUp");
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//CustomUserDetails userDetails = (CustomUserDetails)auth.getPrincipal(); // 로그인된 사용자의 ID 가져오기
		//String uuid = userDetails.getUuId();
		String uuid = userService.getUuidFromAuthenticatedUser();
		param.put("uuid", uuid);
		
		model.addAttribute("title", "Point");
		model.addAttribute("points", pointService.getAllPoint(param));
		
		return "common/pointPopUp";
	}
	
	@RequestMapping("couponMatch")
	public ResponseEntity<Map<String, Object>> couponMatch(@RequestParam HashMap<String, String> param, Model model) {
		log.info("@# couponMatch");
		log.info("@# param ==> " + param);
		Map<String, Object> data = new HashMap<>();
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//CustomUserDetails userDetails = (CustomUserDetails)auth.getPrincipal(); // 로그인된 사용자의 ID 가져오기
		//String uuid = userDetails.getUuId();
		String uuid = userService.getUuidFromAuthenticatedUser();
		param.put("uuid", uuid);
		String result = "F";
		String msg = "";
		int cnt = 0;
		String benefit = "";
		int refno = 0;
		int couponcnt = couponService.checkCoupon(param);
		
		if(couponcnt == 1) {
			result = "T";
			msg = "입력한 쿠폰이 등록되었습니다.\n결제 취소 시, 해당 쿠폰은 다시 사용할 수 없습니다.";
			cnt = 1;
			
			benefit = couponService.getCoupon(param).getBenefit();
			refno = couponService.getCoupon(param).getRefno();
		} else {
			result = "F";
			msg = "존재하지 않는 쿠폰입니다. 다시 확인해 주세요.";
			cnt = 0;
		}
		
		data.put("result", result);
		data.put("msg", msg);
		data.put("cnt", cnt);
		data.put("benefit", benefit);
		data.put("refno", refno);
		
		return ResponseEntity.ok(data);
	}
}