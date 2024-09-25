package com.boot.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import com.boot.DTO.ReserdtltbDTO;
import com.boot.Security.CustomUserDetailsService;
import com.boot.Service.AreaService_2;
import com.boot.Service.MovieService_2;
import com.boot.Service.Movieinfotb_vService_2;
import com.boot.Service.PricetbService_2;
import com.boot.Service.ReserdtltbService_2;
import com.boot.Service.ReserdtltmptbService_2;
import com.boot.Service.ScreenService_2;
import com.boot.Service.TheaterService_2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	//@Autowired
	//private MovieService_2 movieService;
	
	@Autowired
	private Movieinfotb_vService_2 movieinfotb_vService;
	
	@Autowired
	private PricetbService_2 priceService;
	
	@Autowired
	private ReserdtltbService_2 reserdtlService;
	
	@Autowired
	private ReserdtltmptbService_2 reserdtltmptbService;
	
	@Autowired
	private CustomUserDetailsService userService;
	
	//로그인 유무 체크
    @GetMapping("/logincheck")
    public ResponseEntity<Boolean> logincheck() {
    	log.info("@# logincheck");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isLoggedIn = authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken);
        log.info("@# isLoggedIn: " + isLoggedIn);
        
        return ResponseEntity.ok(isLoggedIn);
    }
	
	@RequestMapping("/movieselect")
	public String Ticketing(HttpSession session, Model model) {
		log.info("ticketing");
		
		//세션 초기화
		if(session.getAttribute("movieInfo") != null)
			session.setAttribute("movieInfo", "");
		
		model.addAttribute("area", areaservice.selectAll());
		model.addAttribute("date", areaservice.datedual(""));
		model.addAttribute("movie", movieinfotb_vService.selectAll());
		model.addAttribute("gubun", "none");
		
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
		mav.addObject("movie", movieinfotb_vService.selectAll());
		
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
		
		mav.addObject("minfo", movieinfotb_vService.getTitleRating(param));
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
		session.setAttribute("movieInfo", param);
		session.setMaxInactiveInterval(3600);
		
		return "jsonView";
	}
	
	@RequestMapping("/seatselect")
	public String seatselect(HttpSession session, Model model) {
		log.info("@# seatselect");
		// 세션에 등록한 값 사용
		HashMap<String, String> param = (HashMap<String, String>) session.getAttribute("movieInfo");
		log.info("@# param: " + param);
		
		// movieInfo가 없을 경우 예외 발생
	    if (param == null || param.isEmpty()) {
	        throw new IllegalArgumentException("Movie information is missing in session.");
	    }
		
		//model.addAttribute("param", param);
		model.addAttribute("movieinfo", screenService.selectmovieinfo(param));
		
		ArrayList<String> seat = new ArrayList<>();
		for(int i = 65; i < 78; i++) { //A부터 M까지
			seat.add(((char)i)+"");
		}
		log.info("seat: " + seat);
		
		/* 선택된 좌석 유무 확인 */
		int cnt = reserdtlService.selected_count(param);
		Map<String, Integer> seats = new LinkedHashMap<>(); // LinkedHashMap 삽입 순서 유지

		for(int i = 65; i < 78; i++) { //A부터 M까지
			for(int j = 1; j < 15; j++)
			seats.put(((char)i)+j+"", 0);
		}
		
		if(cnt > 0) {
			ArrayList<ReserdtltbDTO> reserdtl = reserdtlService.selected_seat(param);
			for(int i = 0; i < cnt; i++) {
				seats.put(reserdtl.get(i).getSeat(), 1);
			}
		}
		/* 선택된 좌석 유무 확인 */
		model.addAttribute("seatline", seat); //전제 좌석
		model.addAttribute("seats", seats); //선택 유뮤 좌석 표시
		model.addAttribute("prices", priceService.selectprice(param));
		return "ticketing/seatselect";
	}
	
	@RequestMapping("/saveSessionParamsMore")
	@ResponseBody
	public String saveSessionParamsMore(@RequestParam HashMap<String, String> param, HttpSession session) {
		log.info("@# saveSessionParamsMore");
		log.info("@# param: " + param);
		//선택한 영화 관련 지역, 상영관, 영화, 일자, 시간 값 세션 등록
		HashMap<String, String> params = (HashMap<String, String>) session.getAttribute("movieInfo");
		
		String uuid = userService.getUuidFromAuthenticatedUser();
		params.put("uuid", uuid);
		
		params.put("calc", param.get("calc")); // 총 가격
		params.put("pricetype", param.get("pricetype")); // 가격 타입
		params.put("adult", param.get("adult")); //성인 숫자
		params.put("youth", param.get("youth")); //청소년 숫자
		params.put("old", param.get("old")); //경로 숫자
		params.put("disable", param.get("disable")); //장애인 숫자
		params.put("seats", param.get("seats"));
		
		//선택한 좌석 임시 테이블 저장
		String seats[] = param.get("seats").split(",");
		params.put("idno", reserdtltmptbService.getCnt(params)+"");
		for (int i = 0; i < seats.length; i++) {
			//uuid 기준 으로 선택한 좌석 임시 테이블 저장
			params.put("seat", seats[i]);
			reserdtltmptbService.inserttmp(params);
		}
		
		session.setAttribute("params", params);
		session.setMaxInactiveInterval(3600);
		
		return "jsonView";
	}
	
	@RequestMapping("/payment")
	public String payment(HttpSession session, Model model) {
		log.info("@# payment");
		HashMap<String, String> params = (HashMap<String, String>) session.getAttribute("movieInfo");
		log.info("@# params: " + params);
		
		model.addAttribute("movieinfo", screenService.selectmovieinfo(params));
		
		model.addAttribute("adult", params.get("adult"));
		model.addAttribute("youth", params.get("youth"));
		model.addAttribute("old", params.get("old"));
		model.addAttribute("disable", params.get("disable"));
		model.addAttribute("seats", params.get("seats"));
		model.addAttribute("calc", params.get("calc"));
		model.addAttribute("pricetype", params.get("pricetype")); // 가격 타입
		//이후 컨트롤러 작업은 PayContoller에서 진행
		return "ticketing/payment";
	}
	
	@RequestMapping("/move_ticketing")
	public String move_ticketing(@RequestParam HashMap<String, String> param, Model model) {
		log.info("@# move_ticketing");
		
		model.addAttribute("area", areaservice.selectAll());
		model.addAttribute("date", areaservice.datedual(""));
		model.addAttribute("movie", movieinfotb_vService.selectAll());
		model.addAttribute("sel_movino", param.get("movieno"));
		model.addAttribute("gubun", "mypage");
		//return "ticketing/movieselected";
		return "ticketing/movieselect";
	}
	
	@GetMapping("/check-delete-status")
    public ResponseEntity<Boolean> checkDeleteStatus() {
        return ResponseEntity.ok(reserdtltmptbService.checkDelStatus());
    }

    @PostMapping("/reset-delete-status")
    public ResponseEntity<Void> resetDeleteStatus() {
        reserdtltmptbService.resetDelStatus();
        return ResponseEntity.ok().build();
    }
	
	@RequestMapping("/ticketerrer")
	public String ticketing_error() {
		log.info("ticketing_error");
		return "ticketing/ticketerrer";
	}
}