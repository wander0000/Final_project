package com.boot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.boot.Service.EmailService;

import javax.servlet.http.HttpSession;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    private static final Random RANDOM = new Random();
    private static final int CODE_LENGTH = 6;
    private final ConcurrentHashMap<String, String> verificationCodes = new ConcurrentHashMap<>();

    // 이메일로 인증번호 전송
    @PostMapping("/send-verification-code")
    public String sendVerificationCode(@RequestParam String email, HttpSession session) {
        String code = generateVerificationCode();
        String subject = "비밀번호 찾기 인증 코드";
        String text = "귀하의 인증 코드는: " + code + " 입니다.";

        emailService.sendSimpleMessage(email, subject, text);
        verificationCodes.put(email, code); // 메모리에 인증코드 저장
        session.setAttribute("verificationCode", code);

        return "인증 코드가 " + email + "로 전송되었습니다.";
    }

    // 인증번호 검증
    @PostMapping("/verify-code")
    public String verifyCode(@RequestParam String email, @RequestParam String code) {
        String storedCode = verificationCodes.get(email);
        if (storedCode != null && storedCode.equals(code)) {
            verificationCodes.remove(email); // 성공적으로 인증되면 코드 삭제
            return "인증에 성공하였습니다.";
        } else {
            return "유효하지 않거나 만료된 인증 코드입니다.";
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
