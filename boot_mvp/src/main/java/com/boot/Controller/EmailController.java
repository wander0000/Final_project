package com.boot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.boot.Service.EmailService;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSession;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    private static final Random RANDOM = new Random();
    private static final int CODE_LENGTH = 6;
    private final ConcurrentHashMap<String, String> verificationCodes = new ConcurrentHashMap<>();

 // 이메일로 인증번호 전송
    @RequestMapping("/send-verification-code")
    public String sendVerificationCode(@RequestParam String email, HttpSession session) {
        String code = generateVerificationCode();
        String subject = "비밀번호 찾기 인증 코드";
        String text = "귀하의 인증 코드는: " + code + " 입니다.";
        
        log.info("이건코드  "+code+"이건서브젝트  "+ subject+"이건텍스트  "+ text);
        log.info("Session ID: {}", session.getId());
        
     // 세션 저장 여부 확인을 위한 로그 추가
        
        session.setAttribute("verificationCode", code); // 세션에 인증코드 저장
        session.setAttribute("email", email);

        emailService.sendSimpleMessage(email, subject, text);
        log.info("세션에 저장된 이메일: {}, 저장된 코드: {}", session.getAttribute("email"), session.getAttribute("verificationCode"));
        return "인증 코드가 " + email + "로 전송되었습니다.";
    }

    // 인증번호 검증
    @PostMapping("/verify-code")
    public String verifyCode(@RequestParam String email, @RequestParam String code, HttpSession session, Model model) {
        String storedCode = (String) session.getAttribute("verificationCode");
        String storedEmail = (String) session.getAttribute("email");
        
        // 로그로 저장된 세션 값과 입력된 값을 출력
        log.info("세션에 저장된 이메일: {}, 저장된 코드: {}", storedEmail, storedCode);
        log.info("입력된 이메일: {}, 입력된 코드: {}", email, code);

        // 이메일과 인증번호가 일치하는지 검증
        if (storedCode != null && storedCode.equals(code) && storedEmail != null && storedEmail.equals(email)) {
            session.removeAttribute("verificationCode"); // 인증 성공 후 세션에서 인증번호 제거
            return "success"; // 인증 성공 시
        } else {
            model.addAttribute("error", "인증번호가 유효하지 않거나 만료되었습니다.");
            return "error"; // 인증 실패 시
        }
    }






    // 인증번호 생성
    private String generateVerificationCode() {
        StringBuilder code = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append(RANDOM.nextInt(10)); // 0-9 사이의 랜덤 숫자 생성
        }
        return code.toString();
    }
}
