package com.boot.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.boot.DTO.GenreDTO;
import com.boot.DTO.SelecGenretbDTO;
import com.boot.DTO.UsertbDTO;
import com.boot.Service.EmailService;
import com.boot.Service.GenreService;
import com.boot.Service.LoginService;
import com.boot.Service.SelecGenretbService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    private LoginService loginservice;

    @Autowired
    private GenreService genreservice;

    @Autowired
    private SelecGenretbService selecgenretbService;

    @Autowired
    private PasswordEncoder passwordEncoder; // PasswordEncoder 주입

    @GetMapping("/")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            String id = auth.getName(); // 로그인된 사용자의 ID 가져오기
            UsertbDTO userdto = loginservice.getUserById(id);
            userdto.setPpass(null); // 비밀번호는 숨김
            model.addAttribute("user", userdto);
        }
        return "login/homePage";
    }

    @GetMapping("/userList")
    public String getUserList(Model model) {
        List<UsertbDTO> userlist = loginservice.getUserList();
        model.addAttribute("list", userlist);
        return "login/userListPage";
    }

    @GetMapping("/login")
    public String loginPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return "login/loginPage";
        }
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String signupPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return "login/signupPage1";
        }
        return "redirect:/";
    }

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
    public String signupStep2(@RequestParam(value = "genres", required = false) List<Integer> genres, HttpSession session) {
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
                    for (Integer genreno : genres) {
                        selecgenretbService.insertUserGenre(new SelecGenretbDTO(savedUser.getUuid(), genreno));  // uuid로 수정
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

    @GetMapping("/update")
    public String editPage(Model model) {
        String id = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UsertbDTO userdto = loginservice.getUserById(id);
        model.addAttribute("user", userdto);
        return "login/editPage";
    }

    @PostMapping("/update")
    public String edit(UsertbDTO userdto) {
        String id = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userdto.setUserid(id);
        loginservice.updateUser(userdto);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String withdraw(HttpSession session) {
        String id = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (id != null) {
            loginservice.deleteUser(id);
        }
        SecurityContextHolder.clearContext();
        return "redirect:/";
    }
    


    @GetMapping("/checkUserId")
    @ResponseBody
    public Map<String, Object> checkUserId(@RequestParam String userid) {
        boolean userExists = loginservice.checkUserIdExists(userid); // 아이디 존재 여부를 체크하는 서비스 메소드
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

    
    
    
    
}
