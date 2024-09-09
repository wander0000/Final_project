package com.boot.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.DTO.GenreDTO;
import com.boot.DTO.UsertbDTO;
import com.boot.Security.CustomUserDetailsService;
import com.boot.Service.GenreService;
import com.boot.Service.LoginService;
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
	
	@RequestMapping("mypage/membership")//마이페이지 멤버십로
	public String membership(Model model) {
		log.info("@# Mypage membership");		
		
		return "mypage/membership";
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
	
	@RequestMapping("mypage/userInfo")//마이페이지 회원정보으로
	public String userInfo(Model model) {
		log.info("@# Mypage userInfo");	
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    if (!(auth instanceof AnonymousAuthenticationToken)) {
		        String id = auth.getName(); // 로그인된 사용자의 ID 가져오기
		        UsertbDTO userdto = loginservice.getUserById(id);
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
