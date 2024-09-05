package com.boot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.boot.Service.EmailService;

import javax.servlet.http.HttpSession;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    private static final Random RANDOM = new Random();
    private static final int CODE_LENGTH = 6;
    private final ConcurrentHashMap<String, String> verificationCodes = new ConcurrentHashMap<>();

    @PostMapping("/send-verification-code")
    public String sendVerificationCode(@RequestParam String email, HttpSession session) {
        String code = generateVerificationCode();
        String subject = "Your Verification Code";
        String text = "Your verification code is: " + code;
        
        emailService.sendSimpleMessage(email, subject, text);

        // Store the verification code in the session or a map
        verificationCodes.put(email, code);
        
        // Also set an expiration time for the code if needed
        session.setAttribute("verificationCode", code);

        return "Verification code sent to " + email;
    }

    @PostMapping("/verify-code")
    public String verifyCode(@RequestParam String email, @RequestParam String code) {
        String storedCode = verificationCodes.get(email);
        if (storedCode != null && storedCode.equals(code)) {
            verificationCodes.remove(email); // Remove the code after successful verification
            return "Verification successful";
        } else {
            return "Invalid or expired verification code";
        }
    }

    private String generateVerificationCode() {
        StringBuilder code = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append(RANDOM.nextInt(10));
        }
        return code.toString();
    }
}
