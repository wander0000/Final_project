package com.boot.Controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.Service.ScreenService_2;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
@RequestMapping("/ticketing")
public class PayController {
	
	@Autowired
	private ScreenService_2 screenService;
	
	@RequestMapping("/kakao_ajax")
	public String kakao(Model model) {
		log.info("kakao");
		
		return "pay/kakaobutton_ajax";
	}
	
	@RequestMapping("/show_kakao")
	public String showkakao() {
		return "pay/kakaopay";
	}
	
	@RequestMapping("/toss")
	public String toss() {
		log.info("toss");
		return "pay/tosspay";
	}
	
	@RequestMapping("/paycompleted")
	public String paycompleted(HttpSession session, Model model) {
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
