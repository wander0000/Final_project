package com.boot.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boot.Security.CustomUserDetails;
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
	
	/* 카카오페이 */
	@RequestMapping("/kakao_ajax")
	public String kakao(Model model) {
		log.info("kakao");
		
		return "pay/kakaobutton_ajax";
	}
	@RequestMapping("/show_kakao")
	public String showkakao() {
		return "pay/kakaopay";
	}
	/* 카카오페이 */
	
	/* 토스페이 */
	@RequestMapping("/toss")
	public String toss() {
		log.info("toss");
		return "pay/tosspay";
	}
	/* 토스페이 */
	
	@RequestMapping("/reserve")
	@ResponseBody
	public String reserve(@RequestParam HashMap<String, String> param, HttpSession session) { //예매 정보 등록
		log.info("@# reserve");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		HashMap<String, String> params = (HashMap<String, String>) session.getAttribute("params");
		log.info("@# params: " + params);
		CustomUserDetails userDetails = (CustomUserDetails)auth.getPrincipal(); // 로그인된 사용자의 ID 가져오기
		String uuid = userDetails.getUuId();
		
		String[] seat = params.get("seats").split(",");//좌석 갯수

		/* 예매 번호 */
		Date now = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MMdd-HHmm");
		String formattedDate = formatter.format(now);
		/* 예매 번호 */
		/* reservetb */
		
		/* reservetb */
		
		
		
		/* reserdtltb */
		
		/* reserdtltb */
		
		return null;
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