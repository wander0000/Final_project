package com.boot.Controller;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boot.DTO.BoxOfficeDTO;
import com.boot.DTO.MovietbDTO;
import com.boot.Security.CustomUserDetails;
import com.boot.Service.AttendenceService;
import com.boot.Service.BoxOfficeService;
import com.boot.Service.MembershipService;
import com.boot.Service.MovieService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MainController {
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private BoxOfficeService boxofficeService;
	
	@Autowired
	private AttendenceService attendService;
	
	@RequestMapping("/main")
	public String main(Model model) 
	{
		ArrayList<BoxOfficeDTO> boxDTO = boxofficeService.BoxOfficeList();
		              
        
		ArrayList<MovietbDTO> moviePlayingList = movieService.MoviePlayingList();
		ArrayList<MovietbDTO> movieUpcomingList = movieService.MovieUpcomingList();	
		
		// 날짜 차이 계산을 유틸리티 클래스로 변경
        movieUpcomingList.forEach(movieUpcoming -> 
        {
            Long diffInDays = DateUtils.calculateDaysDifference(movieUpcoming.getOpenday());
            if (diffInDays != null) 
            {
                movieUpcoming.setDaysDifference(diffInDays);
                log.info("@#@#@# diffInDays==>" + diffInDays);
            }
        });		  
		
		model.addAttribute("boxOffice", boxDTO);
		model.addAttribute("moviePlayingList", moviePlayingList);
		model.addAttribute("movieUpcomingList", movieUpcomingList);
		
		return "main";
	};
		

	@GetMapping("/saveMovie")
    public ModelAndView insertMovie()
	{
        try 
        {
        	movieService.insertMovie();  // TMDB 영화 데이터 수집 및 저장
        	boxofficeService.insertBoxOffice();  // TMDB 영화 데이터 수집 및 저장
            return new ModelAndView("redirect:/main");  // 성공 시 영화 목록 페이지로 리다이렉트
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return new ModelAndView("error");  // 오류 발생 시 에러 페이지로 이동
        }
    }
	



} // public class MainController 끝 
