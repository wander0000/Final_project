package com.boot.Security;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.boot.Service.AttendenceService;
import com.boot.Service.MembershipService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private AttendenceService attendanceService;

    @Autowired
    private MembershipService memService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("Authentication success. User: {}", authentication.getName());

        try {
            // 로그인 성공 후 포인트 지급 및 출석 체크
            boolean isNewAttendance = attendanceService.checkAttendance();  // 출석 기록 여부 확인 > 출석기록
            log.info("Authentication success. 오늘 처음 로그인함?:"+isNewAttendance);

            // 출석 기록이 새로 추가되었으면 팝업을 띄우도록 쿠키 설정
            if (isNewAttendance) {
                memService.updateMembership(); // 출석 포인트 지급, 포인트, 마일리지 등급 업데이트

                // 현재 시간 가져오기
                LocalDateTime now = LocalDateTime.now();
                // 오늘 자정 시간 설정
                LocalDateTime midnight = now.toLocalDate().atStartOfDay().plusDays(1);  // 다음 날 자정
                // 자정까지 남은 시간 계산 (초 단위)
                long secondsUntilMidnight = java.time.Duration.between(now, midnight).getSeconds();
                // 쿠키 생성
                Cookie attendancePopupCookie = new Cookie("showAttendancePopup", "true");
                attendancePopupCookie.setPath("/"); // 사이트 전역에서 사용 가능
                // 자정까지 남은 시간 동안만 유효하게 설정
                attendancePopupCookie.setMaxAge((int) secondsUntilMidnight);
                // 쿠키 응답에 추가
                response.addCookie(attendancePopupCookie);
            }


            log.info("Attendance checked successfully for user: {}", authentication.getName());
        } catch (Exception e) {
            log.error("Error occurred while checking attendance", e);
        }

        // 리디렉션 처리
        String redirectUrl = request.getParameter("redirect");
        if (redirectUrl == null || redirectUrl.isEmpty()) {
            redirectUrl = "/main"; // 기본 리디렉션 URL
        }
        log.info("Redirecting to: {}", redirectUrl);
        response.sendRedirect(redirectUrl);
    }
}
