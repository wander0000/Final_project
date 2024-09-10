package com.boot.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boot.DTO.PricetbDTO;
import com.boot.Security.CustomUserDetails;
import com.boot.Service.PricetbService_2;
import com.boot.Service.ReserdtltbService_2;
import com.boot.Service.ReservetbService_2;
import com.boot.Service.ScreenService_2;

import lombok.extern.slf4j.Slf4j;


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
	
	@RequestMapping("/reserve")
	@ResponseBody
	public void reserve(@RequestParam HashMap<String, String> param, HttpSession session) { //예매 정보 등록
		log.info("@# reserve");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		HashMap<String, String> params = (HashMap<String, String>) session.getAttribute("params");
		log.info("@# params: " + params);
		CustomUserDetails userDetails = (CustomUserDetails)auth.getPrincipal(); // 로그인된 사용자의 ID 가져오기
		String uuid = userDetails.getUuId();
		params.put("uuid", uuid);
		String[] seat = params.get("seats").split(",");//좌석 갯수

		/* 예매 번호 */
		Date now = new Date();
		SimpleDateFormat formatter1 = new SimpleDateFormat("MMdd");
		SimpleDateFormat formatter2 = new SimpleDateFormat("HHmm");
		String reservenum_1 = formatter1.format(now);
		String reservenum_2 = formatter2.format(now);
		long millis = System.currentTimeMillis(); // 밀리초 단위의 현재 시간
		String millisStr = Long.toString(millis); // long을 문자열로 변환
		String num_3 = millisStr.substring(millisStr.length() - 4); // 4번째 인덱스부터 끝까지의 문자열을 추출
		
		int reservenum_4 = (int) ( Integer.parseInt(reservenum_1) + Integer.parseInt(reservenum_2) + System.currentTimeMillis());
		String num_4 = Integer.toString(reservenum_4).substring(Integer.toString(reservenum_4).length() - 3);
		
		String reservenum = reservenum_1 + "-" + reservenum_2 + "-" + num_3 + "-" + num_4;
		params.put("reservenum", reservenum);
		/* 예매 번호 */
		
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
		for(int i = 0; i < tmember; i++) {
			price = pricedto.get(i);
			
			for(int j = 0; j < ptype.get(i); j++) {
				pricedate.add(price);
			}
		}
		
		for (int i = 0; i < sit.length; i++) {
			params.put("seat", sit[i]);
			params.put("price", pricedate.get(i)+"");
			reserdtlService.insertReserdtl(params);
		}
		/* reserdtltb */
	}
	
	@RequestMapping("/paycompleted")
	public String paycompleted(HttpSession session, Model model) { //예매 완료 화면
		log.info("@# paycompleted");
		
		// 세션에 등록한 값 사용
		HashMap<String, String> param = (HashMap<String, String>) session.getAttribute("params");
		model.addAttribute("movieinfo", screenService.selectmovieinfo(param));
		
		model.addAttribute("adult", param.get("adult"));
		model.addAttribute("youth", param.get("youth"));
		model.addAttribute("old", param.get("old"));
		model.addAttribute("disable", param.get("disable"));
		model.addAttribute("seats", param.get("seats"));
		
		return "ticketing/paycompleted";
	}
}