package com.boot.Controller;


import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.boot.DTO.BoxOfficeDTO;
import com.boot.DTO.GenreDTO;
import com.boot.DTO.LikeDTO;
import com.boot.DTO.MovietbDTO;
import com.boot.DTO.ReviewDTO;
import com.boot.Security.CustomUserDetails;
import com.boot.Security.CustomUserDetailsService;
import com.boot.Service.BoxOfficeService;
import com.boot.Service.LikeService;
import com.boot.Service.MovieService;
import com.boot.Service.ReviewService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/movie")
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private BoxOfficeService boxofficeService;
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private LikeService likeService;
	
	@Autowired
    private CustomUserDetailsService userService;	
	
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
    public String movieInfo(Model model, @RequestParam(value = "movieno", required = false, defaultValue = "0")String movieno) 
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
        
        HashMap<String,String> param = new HashMap<String,String>();
        param.put("movieno", movieno);
        
//        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    	String uuid = user.getUuId();
    	String uuid = userService.getUuidFromAuthenticatedUser();  // 사용자 UUID 가져오기
    	param.put("uuid",uuid);
        
        ReviewDTO reviewdto = new ReviewDTO();
        reviewdto.setMovieno(movieno);
        
//      Integer reviewNum = reviewService.countReview(param);
        Integer reviewNum = reviewService.countReview(reviewdto);
    	Integer starSum = reviewService.countstar(param);
    	int count = reviewService.findMovieno(param);
    	
    	log.info("@#@#@# movieInfo param==>" + param);
    	log.info("@#@#@# movieInfo count==>" + count);
    	
    	if(starSum == null) 
    	{
    		starSum = 0;
    	}
    	
    	Double average;
    	
    	if (reviewNum != 0) {
    		average = (double) (starSum/reviewNum);
    	} 
    	else 
    	{
    	    // 분모가 0일 때 처리
    		average = (double) 0; // 예를 들어 기본값 설정
    	}    	
    	
    	  
    	log.info("@#@# review2 param =========>"+param);
    	log.info("@#@# review2 count =========>"+count);    	
    	
    	model.addAttribute("count", count);
    	model.addAttribute("reviewNum", reviewNum);
        model.addAttribute("audiAcc", formattedNumber);
        model.addAttribute("salesAcc", salesAccNumber);
        model.addAttribute("movieInfo", movieInfo);
        model.addAttribute("average", average);

        return "/movie/movieInfo";
    }

    @RequestMapping("/review")
    public ModelAndView review(ReviewDTO reviewdto, @RequestParam HashMap<String, String> param) 
    {
    	MovietbDTO movieInfo = movieService.movieInfo(param.get("movieno"));
    	log.info("#### review1 reviewdto1==>"+reviewdto);

    	ArrayList<ReviewDTO> reviewInfo = reviewService.selectReview(param);
//    	ArrayList<ReviewDTO> reviewInfo = reviewService.selectReview(reviewdto);
//    	List<ReviewDTO> reviewInfo = reviewService.selectReview(reviewdto);
    	
    	log.info("#### review1 reviewInfo==>"+reviewInfo);
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/movie/review");
    	mav.addObject("movieInfo",movieInfo);
    	mav.addObject("reviewInfo",reviewInfo);
    	
//    	CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    	String id = user.getUuId();  // 사용자 ID 가져오기
    	String id = userService.getUuidFromAuthenticatedUser();  // 사용자 UUID 가져오기
//    	String id ="50a14682-69d3-11ef-84e9-4cedfbc1d545";
    	param.put("uuid",id);

    	log.info("#### review1 param==>"+param);
    	int count = reviewService.findMovieno(param);
    	log.info("#### review1 count==>"+count);
//    	int reviewNum = reviewService.countReview(param);
    	int reviewNum = reviewService.countReview(reviewdto);
    	Integer starSum = reviewService.countstar(param);
    	
    	if(starSum == null) 
    	{
    		starSum = 0;
    	}
    	
    	Double average;
    	
    	if (reviewNum != 0) {
    		average = (double) (starSum/reviewNum);
    	} 
    	else 
    	{
    	    // 분모가 0일 때 처리
    		average = (double) 0; // 예를 들어 기본값 설정
    	} 
    	
    	mav.addObject("uuid",id);
    	mav.addObject("count",count);
    	mav.addObject("reviewNum",reviewNum);
    	mav.addObject("average",average);
    	return mav;
    }

    @RequestMapping("/review2")
    @ResponseBody
//    public ModelAndView review2(@RequestParam HashMap<String, String> param) 
    public Map<String, Object> review2(ReviewDTO reviewdto, 
    		@RequestParam(value = "page", defaultValue = "1", required = false) int page) throws Exception 
    {
    	Map<String, Object> result = new HashMap<String, Object>();
        int limit = 10; // 한 페이지에 10개씩
        int listcount = reviewService.countReview(reviewdto);

        // 총 페이지수 계산 (리스트 개수를 한 페이지 당 개수로 나눈 값에 올림 처리)
        int maxpage = (int) Math.ceil((double) listcount / limit); 

        // 시작 페이지수
        int startpage = ((page - 1) / 10) * 10 + 1;

        // 마지막 페이지수
        int endpage = startpage + 10 - 1;

        if (endpage > maxpage)
           endpage = maxpage;
        
        int offset = (page - 1) * limit;
        reviewdto.setOffset(offset); // ReviewDTO에 offset 설정
        
        reviewdto.setLimit(limit);
        reviewdto.setPage(page);
        reviewdto.setStartpage(startpage);
        reviewdto.setEndpage(endpage);
        reviewdto.setMaxpage(maxpage);
        log.info("@#@#page==>"+page);
        log.info("@#@#startpage==>"+startpage);
        log.info("@#@#endpage==>"+endpage);
        log.info("@#@#listcount==>"+listcount);
        log.info("@#@#limit==>"+limit);
        log.info("@#@#offset==>"+offset);
        int reviewNum = reviewService.countReview(reviewdto);
        
        List<ReviewDTO> reviewList = reviewService.selectReviewPaging(reviewdto);    
        
//    	CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    	String id = user.getUuId();  // 사용자 ID 가져오기
    	String id = userService.getUuidFromAuthenticatedUser();  // 사용자 UUID 가져오기
    	log.info("@# id ==> " + id);    	
    	
    	HashMap<String,String> param = new HashMap<String,String>();
    	
    	String movieno = reviewdto.getMovieno();
    	log.info("@# review 2 movieno ==> " + movieno);
    	param.put("uuid", id);
    	param.put("movieno", movieno);
    	
    	int count = reviewService.findMovieno(param);
    	log.info("@# review 2 count ==> " + count);
    	log.info("@# review 2 reviewdto ==> " + reviewdto);
    	
    	MovietbDTO movieInfo = movieService.movieInfo(movieno);
    	
    	result.put("movieInfo", movieInfo);
    	result.put("count", count);    	
    	result.put("uuid2", id);    	
    	result.put("reviewNum", reviewNum);
        result.put("reviewList", reviewList);
        result.put("page",  page);
        result.put("startpage",  startpage);
        result.put("endpage",  endpage);
        return result;
    }    
    
    @RequestMapping("/insertReview")
    public ResponseEntity<String> insertReview(@RequestParam HashMap<String, String> param) 
    {
    	
//    	CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    	String id = user.getUuId();  // 사용자 ID 가져오기
    	String id = userService.getUuidFromAuthenticatedUser();  // 사용자 UUID 가져오기    	
//    	String id ="11";
    	param.put("uuid",id);
    	
    	log.info("####Insert param==>"+param);
    	
    	reviewService.insertReview(param);
    	return ResponseEntity.ok("Review inserted");
    }    
    
    @RequestMapping("/modifyReview")
    public ResponseEntity<String> modifyReview(@RequestParam HashMap<String, String> param) 
    {
    	
//    	CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    	String id = user.getUuId();  // 사용자 ID 가져오기
    	String id = userService.getUuidFromAuthenticatedUser();  // 사용자 UUID 가져오기    	
//    	String id ="11";
    	param.put("uuid",id);
    	
    	log.info("####param==>"+param);
    	
    	reviewService.updateReview(param);
    	return ResponseEntity.ok("Review modify");
    } 
    
    @RequestMapping("/deleteReview")
    public ResponseEntity<String> deleteReview(@RequestParam HashMap<String, String> param) 
    {
    	
//    	CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    	String id = user.getUuId();  // 사용자 ID 가져오기
    	String id = userService.getUuidFromAuthenticatedUser();  // 사용자 UUID 가져오기    	
//    	String id ="11";
    	param.put("uuid",id);
    	
    	
    	log.info("####param==>"+param);
    	
    	reviewService.deleteReview(param);
    	return ResponseEntity.ok("Review delete");
    }     

//    @RequestMapping("/insertLike")
//    public ResponseEntity<String> insertLike(@RequestParam HashMap<String, String> param) 
//    {    	
//    	CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    	String id = user.getUuId();  // 사용자 ID 가져오기
//    	String id = userService.getUuidFromAuthenticatedUser();  // 사용자 UUID 가져오기    
//    	param.put("uuid",id);
//    	
//    	log.info("####Insert param==>"+param);
//    	
//    	likeService.insertLike(param);
//    	return ResponseEntity.ok("Like inserted");
//    } 
//    
//    @RequestMapping("/deleteLike")
//    public ResponseEntity<String> deleteLike(@RequestParam HashMap<String, String> param) 
//    {
//    	
//    	CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    	String id = user.getUuId();  // 사용자 ID 가져오기
//    	param.put("uuid",id);    	
//    	
//    	log.info("####param==>"+param);
//    	
//    	likeService.deleteLike(param);
//    	return ResponseEntity.ok("Like delete");
//    } 

    @RequestMapping("/isLike")
    public ResponseEntity<String> isLike(@RequestParam HashMap<String, String> param) 
    {    	
//    	CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    	String id = user.getUuId();  // 사용자 ID 가져오기
    	String id = userService.getUuidFromAuthenticatedUser();  // 사용자 UUID 가져오기    	
    	param.put("uuid",id);    	
    	
    	log.info("####param==>"+param);
    	
    	int islike = likeService.isLike(param);
    	
    	log.info("####islike==>"+islike);
    	String condition ="";
    	
    	if(islike == 0) 
    	{
    		likeService.insertLike(param);
    		condition ="I";
    	}
    	else if(islike !=0)
    	{
    		likeService.deleteLike(param);
    		condition ="D";
    	}    	
    	
    	Integer num = likeService.numLike(param);
    	String numLike = num.toString();
    	LikeDTO response = new LikeDTO("","",condition, numLike);
    	
    	return ResponseEntity.ok(condition);    	
    }  
    
    @RequestMapping("/numLike")
    public ResponseEntity<LikeDTO> numLike(@RequestParam HashMap<String, String> param) 
    {   
//    	CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    	String id = user.getUuId();  // 사용자 ID 가져오기
    	String id = userService.getUuidFromAuthenticatedUser();  // 사용자 UUID 가져오기    	
    	param.put("uuid",id);

    	int islike = likeService.isLike(param);
    	String condition ="";
    	
    	if(islike == 0) 
    	{
    		condition ="I";
    	}
    	else if(islike !=0)
    	{
    		condition ="D";
    	} 
    	
    	log.info("@#@# numLike condition===> "+condition);
    	
    	Integer num = likeService.numLike(param);
    	String numLike = num.toString();
    	LikeDTO response = new LikeDTO("","",condition, numLike);
    	
    	return ResponseEntity.ok(response);    	
    }  
    
} //public class MovieController 끝
