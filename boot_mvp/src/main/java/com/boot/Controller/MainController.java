package com.boot.Controller;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.boot.DTO.BoxOfficeDTO;
import com.boot.DTO.MovietbDTO;
import com.boot.DTO.SelecGenretbDTO;
import com.boot.Security.CustomUserDetails;
import com.boot.Security.CustomUserDetailsService;
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
	
	@Autowired
    private CustomUserDetailsService userService;
	
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
		

//	@GetMapping("/saveMovie")
//    public ModelAndView insertMovie()
//	{
//        try 
//        {
//        	movieService.insertMovie();  // TMDB 영화 데이터 수집 및 저장
//        	boxofficeService.insertBoxOffice();  // TMDB 영화 데이터 수집 및 저장
//            return new ModelAndView("redirect:/main");  // 성공 시 영화 목록 페이지로 리다이렉트
//        } 
//        catch (Exception e) 
//        {
//            e.printStackTrace();
//            return new ModelAndView("error");  // 오류 발생 시 에러 페이지로 이동
//        }
//    }
	
	@RequestMapping("/recommendPop")
	public ResponseEntity<Object> recommendPop(@RequestParam HashMap<String, Object> param) 
	{
//    	CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String uuid = userService.getUuidFromAuthenticatedUser();  // 사용자 UUID 가져오기
    	
//    	String uuid = user.getUuId();  // 사용자 ID 가져오기
    	param.put("uuid",uuid);
    	ArrayList<SelecGenretbDTO> selectGD = movieService.selectUserGenre(param);
    	
    	log.info("recommendPop selectGD=================>"+selectGD);
    	
    	// genre 이름들을 배열 형태로 생성
    	List<String> genreNames = new ArrayList<>();
    	for (SelecGenretbDTO dto : selectGD) {
    	    genreNames.add(dto.getGenrenm());  // genrenm을 추출하여 리스트에 추가
    	}
    	
    	log.info("recommendPop genreNames=================>"+genreNames);
    	
    	// genreNames 리스트를 param에 넣음 (필요한 경우 콤마로 구분된 문자열로 변환)
    	param.put("genreList", genreNames);
    	
    	log.info("recommendPop param=================>"+param);
    	
    	List<BoxOfficeDTO> movies = movieService.selectMoviesByGenres(param);
    	
    	log.info("recommendPop movies=================>"+movies);
    	
		return ResponseEntity.ok(movies);
	}
	



} // public class MainController 끝 
