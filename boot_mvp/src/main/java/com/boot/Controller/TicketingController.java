package com.boot.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.boot.DTO.ScreentbDTO;
import com.boot.Service.AreaService_2;
import com.boot.Service.MovieService_2;
import com.boot.Service.PricetbService_2;
import com.boot.Service.ScreenService_2;
import com.boot.Service.TheaterService_2;


@Controller
@Slf4j
@RequestMapping("/ticketing")
public class TicketingController {
	
	@Autowired
	private AreaService_2 areaservice;
	
	@Autowired
	private TheaterService_2 theaterservice;
	
	@Autowired
	private ScreenService_2 screenService;
	
	@Autowired
	private MovieService_2 movieService;
	
	@Autowired
	private PricetbService_2 priceService;
	
	@RequestMapping("/movieselect")
	public String Ticketing(Model model) {
		log.info("ticketing");
		
		model.addAttribute("area", areaservice.selectAll());
		model.addAttribute("date", areaservice.datedual(""));
		model.addAttribute("movie", movieService.selectAll());
		
		return "ticketing/movieselect";
	}
	
	@RequestMapping("/theatershow")
	public ModelAndView theatershow_ajax(@RequestParam HashMap<String, String> param) {
		log.info("@# theatershow_ajax");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("ticketing/theater_ajax");
		mav.addObject("areano", param.get("areano"));
		mav.addObject("theater", theaterservice.theater(param));
		
		return mav;
	}

	@RequestMapping("/movieshow")
	public ModelAndView movieshow_ajax(@RequestParam HashMap<String, String> param) {
		log.info("@# movieshow_ajax");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("ticketing/movie_ajax");
		mav.addObject("areano", param.get("areano"));
		mav.addObject("theaterno", param.get("theaterno"));
		mav.addObject("movie", movieService.selectAll());
		
		return mav;
	}
	
	@RequestMapping("/dateshow")
	public ModelAndView dateshow_ajax(@RequestParam HashMap<String, String> param) {
		log.info("@# dateshow_ajax");
		log.info("param: " + param);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("ticketing/date_ajax");
		
		if(param.get("viewday").equals("no")) {
			// 오늘 날짜를 가져옵니다
	        LocalDate today = LocalDate.now();
	        // 날짜 형식을 설정합니다
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        // 포맷에 맞춰 문자열로 변환합니다
	        String formattedDate = today.format(formatter);
	        param.put("viewday", formattedDate);
		}
		
		mav.addObject("title", movieService.getTitle(param));
		mav.addObject("areano", param.get("areano"));
		mav.addObject("theaterno", param.get("theaterno"));
		mav.addObject("detailinfo", screenService.selectdtl(param));
		
		return mav;
	}
	
	@RequestMapping("/datetxt")
	public @ResponseBody Map<String, Object> datetxt() {
		log.info("@# datetxt");
		Map<String, Object> respones = new HashMap<>();
		
		String txt = areaservice.datedual("").get(0).getTxt();
		log.info("@# txt: " + txt);
		respones.put("date", txt);
		
		return respones;
	}
	
	@RequestMapping("/datetxtparam")
	public @ResponseBody Map<String, Object> datetxtparma(@RequestParam(value = "viewday") String viewday) {
		log.info("@# datetxtparam");
		Map<String, Object> respones = new HashMap<>();
		log.info("dates: " + viewday);
		String txt = areaservice.datedual(viewday).get(0).getTxt();
		log.info("txt: " + txt);
		log.info("@# txt: " + txt);
		respones.put("date", txt);
		
		return respones;
	}
	
	@RequestMapping("/saveSessionParams")
	@ResponseBody
	public String saveSessionParams(@RequestParam HashMap<String, String> param, HttpSession session) {
		log.info("@# saveSessionParams");
		log.info("@# param: " + param);
		//선택한 영화 관련 지역, 상영관, 영화, 일자, 시간 값 세션 등록
		session.setAttribute("params", param);
		session.setMaxInactiveInterval(3600);
		
		return "jsonView";
	}
	
	@RequestMapping("/seatselect")
	public String seatselect(HttpSession session, Model model) {
		log.info("@# seatselect");
		// 세션에 등록한 값 사용
		HashMap<String, String> param = (HashMap<String, String>) session.getAttribute("params");
		log.info("@# param: " + param);
		
		//model.addAttribute("param", param);
		model.addAttribute("movieinfo", screenService.selectmovieinfo(param));
		
		ArrayList<String> seat = new ArrayList<>();
		for(int i = 65; i < 78; i++) {
			seat.add(((char)i)+"");
		}
		log.info("seat: " + seat);
		
		model.addAttribute("seatline", seat); //전제 좌석
		model.addAttribute("prices", priceService.selectprice(param));
		return "ticketing/seatselect";
	}
	
	@RequestMapping("/saveSessionParamsMore")
	@ResponseBody
	public String saveSessionParamsMore(@RequestParam HashMap<String, String> param, HttpSession session) {
		log.info("@# saveSessionParamsMore");
		log.info("@# param: " + param);
		//선택한 영화 관련 지역, 상영관, 영화, 일자, 시간 값 세션 등록
		HashMap<String, String> params = (HashMap<String, String>) session.getAttribute("params");
		
		params.put("calc", param.get("calc")); // 총 가격
		params.put("adult", param.get("adult")); //성인 숫자
		params.put("youth", param.get("youth")); //청소년 숫자
		params.put("old", param.get("old")); //경로 숫자
		params.put("disable", param.get("disable")); //장애인 숫자
		params.put("seats", param.get("seats"));
		
		session.setAttribute("params", params);
		session.setMaxInactiveInterval(3600);
		
		return "jsonView";
	}
	
	@RequestMapping("/payment")
	public String payment(@RequestParam HashMap<String, String> param, HttpSession session, Model model) {
		log.info("@# payment");
		HashMap<String, String> params = (HashMap<String, String>) session.getAttribute("params");
		log.info("@# params: " + params);
		
		model.addAttribute("movieinfo", screenService.selectmovieinfo(params));
		
		model.addAttribute("adult", params.get("adult"));
		model.addAttribute("youth", params.get("youth"));
		model.addAttribute("old", params.get("old"));
		model.addAttribute("disable", params.get("disable"));
		model.addAttribute("seats", params.get("seats"));
		model.addAttribute("calc", params.get("calc"));
		
		//이후 컨트롤러 작업은 PayContoller에서 진행
		return "ticketing/payment"; 
	}
}