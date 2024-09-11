package com.boot.Controller;


import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.DTO.BoxOfficeDTO;
import com.boot.DTO.GenreDTO;
import com.boot.DTO.MovietbDTO;
import com.boot.Service.BoxOfficeService;
import com.boot.Service.MovieService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/movie")
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private BoxOfficeService boxofficeService;
	
	@RequestMapping("/movie")
    public String movie(Model model) 
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

        return "/movie/movie";
    }  

    @RequestMapping("/movieGenre")
    public String movieGenre(Model model, @RequestParam(value = "genre", required = false, defaultValue = "액션") String genre) 
    {        
        ArrayList<MovietbDTO> GenreList = movieService.GenreList(genre);
        List<GenreDTO> genres = movieService.Genre();
        log.info("@#@#@# genre==>" + genre);
        // 날짜 차이 계산을 유틸리티 클래스로 변경
        GenreList.forEach(GenreD -> 
        {
            Long diffInDays = DateUtils.calculateDaysDifference(GenreD.getOpenday());
            if (diffInDays != null) 
            {
            	GenreD.setDaysDifference(diffInDays);
                log.info("@#@#@# diffInDays==>" + diffInDays);
            }
        });
        
        model.addAttribute("GenreList", GenreList);
        model.addAttribute("genres", genres);
        model.addAttribute("genre", genre);

        return "/movie/movieGenre";
    }
    
    @RequestMapping("/movieInfo")
    public String movieInfo(Model model, @RequestParam(value = "movieno", required = false, defaultValue = "0")String movieno ) 
    {   
    	log.info("@#@#@# movieno==>" + movieno);
    	MovietbDTO movieInfo = movieService.movieInfo(movieno);
    	log.info("@#@#@# movieInfo==>" + movieInfo);
        // 날짜 차이 계산을 유틸리티 클래스로 변경
    	int audienceCount = movieInfo.getAudiAcc();
    	String formattedNumber = String.format("%,d", audienceCount);
    	
    	String salesAcc = movieInfo.getSalesAcc();
    	String salesAccNumber ="";
		if (salesAcc == null || salesAcc.isEmpty()) 
		{
			salesAcc = "0"; // 기본값 설정
		}
    	 
        try
        {
    	NumberFormat numberFormat = NumberFormat.getInstance(Locale.getDefault());
        Number number = numberFormat.parse(salesAcc);
        
        // 정수형 데이터를 포맷
        salesAccNumber = numberFormat.format(number);
        }
        catch (ParseException e) 
        {
            e.printStackTrace();
        }

        Long diffInDays = DateUtils.calculateDaysDifference(movieInfo.getOpenday());
        if (diffInDays != null) 
        {
        	movieInfo.setDaysDifference(diffInDays);
            log.info("@#@#@# diffInDays==>" + diffInDays);
        }
        
        model.addAttribute("audiAcc", formattedNumber);
        model.addAttribute("salesAcc", salesAccNumber);
        model.addAttribute("movieInfo", movieInfo);

        return "/movie/movieInfo";
    }

} //public class MovieController 끝
