package com.boot.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class PayController {
	@RequestMapping("kakao")
	public String kakao() {
		log.info("kakao");
		return "pay/kakaopay";
	}
	
	@RequestMapping("toss")
	public String toss() {
		log.info("toss");
		return "pay/tosspay";
	}
	
}
