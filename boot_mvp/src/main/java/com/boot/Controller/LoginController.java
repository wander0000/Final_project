package com.boot.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boot.DTO.BoxOfficeDTO;
import com.boot.DTO.GenreDTO;
import com.boot.DTO.OauthtbDTO;
import com.boot.DTO.MovietbDTO;
import com.boot.DTO.SelecGenretbDTO;
import com.boot.DTO.UsertbDTO;
import com.boot.Security.CustomUserDetails;
import com.boot.Service.BoxOfficeService;
import com.boot.Service.GenreService;
import com.boot.Service.LoginService;

import com.boot.Service.OauthtbService;

import com.boot.Service.MembershipService;
import com.boot.Service.MovieService;

import com.boot.Service.SelecGenretbService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    private LoginService loginservice;
    
    @Autowired
    private OauthtbService oauthtbService;

    @Autowired
    private GenreService genreservice;

    @Autowired
    private SelecGenretbService selecgenretbService;
    
    @Autowired
    private MembershipService memService;
    
    @Autowired
	private MovieService movieService;
	
	@Autowired
	private BoxOfficeService boxofficeService;

    @Autowired
    private PasswordEncoder passwordEncoder; // PasswordEncoder 주입
    
    
    
//    public void checkPrincipalType() {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//        if (auth instanceof OAuth2AuthenticationToken) {
//            // OAuth2 로그인 사용자 처리
//            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) auth;
//
//            // OAuth2 공급자 정보 가져오기
//            String registrationId = oauthToken.getAuthorizedClientRegistrationId();  // 공급자 ID (google, naver 등)
//            System.out.println("OAuth2 로그인 사용자가 로그인했습니다. 공급자: " + registrationId);
//            
//            OAuth2User oauthUser = (OAuth2User) principal;
//            String name = oauthUser.getAttribute("name");
//            System.out.println("사용자 이름: " + name);
//            
//            // 공급자에 따라 추가 처리
//            if ("google".equals(registrationId)) {
//                System.out.println("구글로 로그인했습니다.");
//            } else if ("naver".equals(registrationId)) {
//                System.out.println("네이버로 로그인했습니다.");
//            } else if ("facebook".equals(registrationId)) {
//                System.out.println("페이스북으로 로그인했습니다.");
//            }
//        } else if (principal instanceof UserDetails) {
//            // 폼 로그인 사용자 처리
//            UserDetails userDetails = (UserDetails) principal;
//            System.out.println("폼 로그인 사용자가 로그인했습니다.");
//            System.out.println("아이디: " + userDetails.getUsername());
//        }
//    }
    
    

    @RequestMapping("/")
    public String home(Model model) {
      //  @@@@@@@@@@@@ 이 부분 삭제 x 0919,20 추석끝나고 같이 얘기해요 지금은 주석처리하고 써도댐// 
//       <<<<<<< login
//         Authentication auth = SecurityContextHolder.getContext().getAuthentication();

//         if (!(auth instanceof AnonymousAuthenticationToken)) {
//             Object principal = auth.getPrincipal();
            
//             if (auth instanceof OAuth2AuthenticationToken) {
//                 OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) auth;
//                 String registrationId = oauthToken.getAuthorizedClientRegistrationId();  // OAuth2 공급자 ID
//                 OAuth2User oauth2User = (OAuth2User) principal;
//                 String oauthUserId = oauth2User.getAttribute("sub");  // OAuth2 고유 ID, 제공자에 따라 다를 수 있음
//                 String email = null;
                
//                 // 공급자별 사용자 고유 ID와 이메일 확인
//                 if ("google".equals(registrationId)) {
//                     oauthUserId = oauth2User.getAttribute("sub");
//                     email = oauth2User.getAttribute("email");  // 구글 이메일 정보
//                 } else if ("naver".equals(registrationId)) {
//                     oauthUserId = oauth2User.getAttribute("id");
//                     email = oauth2User.getAttribute("email");  // 네이버 이메일 정보
//                 } else if ("facebook".equals(registrationId)) {
//                     oauthUserId = oauth2User.getAttribute("id");
//                     email = oauth2User.getAttribute("email");  // 페이스북 이메일 정보
//                 }
//                 log.info("오스2 유저아이디: {}", oauthUserId);
//                 log.info("오스2 프로바이더: {}", registrationId);
//                 log.info("오스2 이메일: {}", email);

//                 // 기존 회원 여부를 확인
//                 boolean isExistingUser = oauthtbService.oauthCheckNewUser(oauthUserId, registrationId);

//                 if (isExistingUser) {
//                     model.addAttribute("name", oauth2User.getAttribute("name"));
//                     model.addAttribute("provider", registrationId);
//                     return "login/homePage";  // 기존 회원
//                 } else {
//                     // 신규 회원일 경우 필요한 정보들(model에 추가)
//                     model.addAttribute("oauthUserId", oauthUserId);
//                     model.addAttribute("registrationId", registrationId);
//                     model.addAttribute("email", email);  // 이메일도 추가
//                     return "login/oauthSignup1";  // 신규 회원은 회원가입 페이지로 이동
//                 }
//             } else if (principal instanceof UserDetails) {
//                 // 폼 로그인 사용자 처리
//                 UserDetails userDetails = (UserDetails) principal;
//                 String username = userDetails.getUsername();
//                 model.addAttribute("username", username);
//                 return "login/homePage";
//             }
//         }
//         return "login/homePage";  // 인증되지 않은 사용자면 로그인 페이지로 이동
//     }

//     // 그냥 홈페이지로 이동하는 매핑
//     @GetMapping("/login/homePage")
//     public String homePage() {
//         return "/login/homePage";  // homePage.jsp 파일로 이동 (뷰 리졸버에서 prefix, suffix 설정에 따라 경로가 결정됨)
//     }

    
// //    // oauth 회원가입페이지이동
// //    @GetMapping("/oauthSignup1")
// //    public String oauthSignup(Model model) {
// //        return "/login/oauthSignup1";
// //    }
    
//     // oauth 추가 회원가입 1
//     @PostMapping("/oauthSignupSubmit1")
//     public String oauthSignupSubmit1(
//             @RequestParam("userid") String userid,
//             @RequestParam("pname") String pname,
//             @RequestParam("phone") String phone,
//             @RequestParam("birth") String birth,
//             @RequestParam("gender") int gender,
//             @RequestParam("email") String email,
//             @RequestParam("oauthUserId") String oauthUserId,
//             @RequestParam("registrationId") String registrationId,
//             HttpSession session) {

//         // OauthtbDTO 객체에 값 설정
//         OauthtbDTO oauthtbDTO = new OauthtbDTO();
//         oauthtbDTO.setUserid(userid);
//         oauthtbDTO.setPname(pname);
//         oauthtbDTO.setPhone(phone);
//         oauthtbDTO.setBirth(birth);
//         oauthtbDTO.setGender(gender);
//         oauthtbDTO.setOauthuniq(oauthUserId);
//         oauthtbDTO.setOauthdiff(registrationId);
//         oauthtbDTO.setEmail(email);

// //        // 서비스 호출하여 DB에 회원 정보 저장
// //        oauthtbService.oauthInsertUser(oauthtbDTO);
//         session.setAttribute("signupUser", oauthtbDTO);

//         // 저장 성공 시 홈 페이지로 리다이렉트
//         return "redirect:/oauthSignupSubmit2";  // 홈 페이지로 리다이렉트 (예시)
//     }
    
    
//     // oauth 추가 회원가입 2 
//     @GetMapping("/oauthSignupSubmit2")
//     public String oauthSignupSubmit2Page(HttpSession session, Model model) {
//     	OauthtbDTO oauthtbDTO = (OauthtbDTO) session.getAttribute("signupUser");
//         if (oauthtbDTO == null) {
//             return "redirect:/oauthSignupSubmit1";
//         }

//         List<GenreDTO> genres = genreservice.getAllGenres();
//         model.addAttribute("genres", genres);
//         model.addAttribute("user", oauthtbDTO);
//         log.info("oauthSignupSubmit2Page oauth 회원 정보: {}", oauthtbDTO);
//         return "login/oauthSignup2";
 //  @@@@@@@@@@@@ 이 부분 삭제 x 0919,20 추석끝나고 같이 얘기해요 지금은 주석처리하고 써도댐// 
      
      

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

    }
    
    
    
    @PostMapping("/oauthSignupSubmit2")
    public String oauthSignupSubmit2(@RequestParam(value = "genres", required = false) List<String> genres, HttpSession session) {
    	OauthtbDTO oauthtbDTO = (OauthtbDTO) session.getAttribute("signupUser");
    	
    	log.info("선택된 장르: {}", genres);
        log.info("oauthSignupSubmit2버트 oauth 회원 정보: {}", oauthtbDTO);
        
        
        if (oauthtbDTO == null) {
            return "redirect:/oauthSignupSubmit1";
        }

        try {
            // 회원 정보 저장
            oauthtbService.oauthInsertUser(oauthtbDTO);

            // 회원 정보 삽입 후 uuid 확인
            OauthtbDTO savedUser = oauthtbService.oauthGetUserById(oauthtbDTO.getUserid());
            log.info("savedUser 저장되냐 "+savedUser);
            if (savedUser != null) {
                // 장르 정보 저장
                if (genres != null && !genres.isEmpty()) {
                    for (String genrenm : genres) {
                        selecgenretbService.oauthtbinsertUserGenre(new SelecGenretbDTO(savedUser.getUuid(), genrenm));  // uuid로 수정
                    }
                }
            } else {
                return "redirect:/oauthSignupSubmit2?error_code=-99"; // 사용자 정보 확인 오류
            }

            session.removeAttribute("signupUser");
        } catch (DuplicateKeyException e) {
            return "redirect:/oauthSignupSubmit2?error_code=-3"; // 사용자 ID 중복
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/oauthSignupSubmit2?error_code=-90"; // 기타 오류
        }

        return "redirect:/login/homePage";
    }
    

    
    

    // 전체 유저리스트 (이거 안씀)
    @GetMapping("/userList")
    public String getUserList(Model model) {
        List<UsertbDTO> userlist = loginservice.getUserList();
        model.addAttribute("list", userlist);
        return "login/userListPage";
    }

    // 로그인페이지
    @GetMapping("/login")
    public String loginPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return "login/loginPage";
        }
        return "redirect:/";
    }

    
    // usertb 회원가입페이지 이동 (권한없으면 가입가능)
    @GetMapping("/signup")
    public String signupPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return "login/signupPage1";
        }
        return "redirect:/";
    }

    // user 회원가입 1
    @PostMapping("/signup/step1")
    public String signupStep1(@RequestParam String pname,
                              @RequestParam String email,
                              @RequestParam String userid,
                              @RequestParam String ppass,
                              @RequestParam String ppassConfirm,
                              @RequestParam String phone,
                              @RequestParam String birth,
                              @RequestParam int gender,
                              HttpSession session) {

        if (!ppass.equals(ppassConfirm)) {
            return "redirect:/signup?error_code=-2"; // 비밀번호 불일치
        }

        UsertbDTO userdto = new UsertbDTO();
        userdto.setPname(pname);
        userdto.setEmail(email);
        userdto.setUserid(userid);
        userdto.setPpass(ppass);
        userdto.setPhone(phone);
        userdto.setBirth(birth);
        userdto.setGender(gender);

        session.setAttribute("signupUser", userdto);
        return "redirect:/signup/step2";
    }

    // user 회원가입 2
    @GetMapping("/signup/step2")
    public String signupStep2Page(HttpSession session, Model model) {
    	UsertbDTO userdto = (UsertbDTO) session.getAttribute("signupUser");
        if (userdto == null) {
            return "redirect:/signup";
        }

        List<GenreDTO> genres = genreservice.getAllGenres();
        model.addAttribute("genres", genres);
        model.addAttribute("user", userdto);

        return "login/signupPage2";
    }

    @PostMapping("/signup/step2")
    public String signupStep2(@RequestParam(value = "genres", required = false) List<String> genres, HttpSession session) {
    	UsertbDTO userdto = (UsertbDTO) session.getAttribute("signupUser");
        if (userdto == null) {
            return "redirect:/signup";
        }

        try {
            // 회원 정보 저장
            loginservice.insertUser(userdto);

            // 회원 정보 삽입 후 uuid 확인
            UsertbDTO savedUser = loginservice.getUserById(userdto.getUserid());
            if (savedUser != null) {
                // 장르 정보 저장
                if (genres != null && !genres.isEmpty()) {
                    for (String genrenm : genres) {
                        selecgenretbService.usertbinsertUserGenre(new SelecGenretbDTO(savedUser.getUuid(), genrenm));  // uuid로 수정
                    }
                }
            } else {
                return "redirect:/signup/step2?error_code=-99"; // 사용자 정보 확인 오류
            }

            session.removeAttribute("signupUser");
        } catch (DuplicateKeyException e) {
            return "redirect:/signup/step2?error_code=-3"; // 사용자 ID 중복
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/signup/step2?error_code=-90"; // 기타 오류
        }

        return "redirect:/login";
    }



    @PostMapping("/delete")
    public String withdraw() {
//        String id = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String id = user.getUserId();  // 사용자 ID 가져오기
    	

        if (id != null) {
            loginservice.deleteUser(id);
        }
        SecurityContextHolder.clearContext();
        return "redirect:/";
    }
    

    // usertb 아이디 중복확인
    @GetMapping("/checkUserId")
    @ResponseBody
    public Map<String, Object> checkUserId(@RequestParam String userid) {
        boolean userExists = loginservice.checkUserIdExists(userid); // 아이디 존재 여부를 체크하는 서비스 메소드
        Map<String, Object> response = new HashMap<>();
        response.put("userExists", userExists);
        return response;
    }
    
    // oauthtb 아이디 중복확인
    @GetMapping("/oauthCheckUserid")
    @ResponseBody
    public Map<String, Object> oauthCheckUserid(@RequestParam String userid) {
        // ID 중복 체크 결과를 int로 받아서 boolean으로 변환
        boolean userExists = oauthtbService.oauthCheckUserid(userid) > 0;
        Map<String, Object> response = new HashMap<>();
        response.put("userExists", userExists);
        return response;
    }

    

    @GetMapping("/email/check-email")
    @ResponseBody
    public Map<String, Object> checkEmail(@RequestParam String email) {
        boolean emailExists = loginservice.checkEmailExists(email); // 이메일 존재 여부를 체크하는 서비스 메소드
        Map<String, Object> response = new HashMap<>();
        response.put("emailExists", emailExists);
        return response;
    }

    

    
    @GetMapping("/update")
    public String editPage(Model model) {
//        String id = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String id = user.getUserId();  // 사용자 ID 가져오기
        UsertbDTO userdto = loginservice.getUserById(id);
        model.addAttribute("user", userdto);
        return "login/editPage";
    }

    @PostMapping("/update")
    public String edit(UsertbDTO userdto) {
//        String id = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String id = user.getUserId();  // 사용자 ID 가져오기
        userdto.setUserid(id);
        loginservice.updateUser(userdto);
        return "redirect:/";
    }
    
    
    
    // 아이디 찾기
    
    @RequestMapping("/findIdPage")
    public String userController() {
        return "/login/findIdPage";
    }

    @RequestMapping("/userid")
    public ResponseEntity<String> getUserIdByNameAndEmail(@RequestParam String pname, @RequestParam String email) {
        String userId = loginservice.getUserIdByNameAndEmail(pname, email);
        if (userId != null) {
            return ResponseEntity.ok(userId); // 아이디가 존재하면 반환
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 아이디가 없으면 404 반환
        }
    }
    
    
    
    
    
    // 비밀번호 찾기
    
    // 비밀번호 재설정 폼 페이지로 이동
    @RequestMapping("/findPwPage")
    public String findPasswordPage() {
        return "login/findPwPage"; // 비밀번호 재설정 폼 페이지로 이동
    }
    
    


    @RequestMapping("/findPassword")
    @ResponseBody
    public Map<String, Object> findPassword(@RequestParam String email) {
        boolean emailExists = loginservice.checkEmailExists(email); // 이메일 존재 여부 확인
        Map<String, Object> response = new HashMap<>();

        if (emailExists) {
            // 이메일로 인증번호 전송 (EmailController의 인증번호 전송 메서드 호출)
            response.put("status", "success");
            response.put("message", "인증번호가 이메일로 전송되었습니다.");
            response.put("email", email);
        } else {
            response.put("status", "error");
            response.put("message", "이메일을 다시 확인해주세요.");
        }

        return response;
    }


    
    // 비밀번호 재설정 폼 페이지로 이동
    @RequestMapping("/resetPwPage")
    public String resetPasswordPage(@RequestParam String email, Model model) {
        model.addAttribute("email", email);
        return "login/resetPwPage"; // 비밀번호 재설정 폼 페이지로 이동
    }

    // 비밀번호 재설정 처리
    @RequestMapping("/resetPassword")
    public String resetPassword(@RequestParam String email,
                                @RequestParam String newPassword,
                                @RequestParam String confirmPassword,
                                Model model) {
        // 비밀번호 일치 여부 확인
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "login/resetPwPage";
        }

        // 비밀번호 업데이트
        loginservice.updatePassword(email, newPassword);

        // 성공 시 로그인 페이지로 리다이렉트
        return "redirect:/login";
    }
    
}
