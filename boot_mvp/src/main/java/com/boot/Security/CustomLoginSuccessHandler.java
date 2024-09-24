package com.boot.Security;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.boot.Service.AttendenceService;
import com.boot.Service.MembershipService;
import com.boot.Service.OauthtbService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private AttendenceService attendanceService;

    @Autowired
    private MembershipService memService;

    @Autowired
    private OauthtbService oauthtbService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("Authentication success. User: {}", authentication.getName());

        if (authentication != null && authentication.getPrincipal() instanceof OAuth2User) {
            // OAuth 사용자 확인
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            String registrationId = oauthToken.getAuthorizedClientRegistrationId(); // 공급자 ID (google, naver 등)
            String oauthUserId = getOAuthUserId(oauthToken, registrationId); 
            Object principal = authentication.getPrincipal();
            OAuth2User oauth2User = (OAuth2User) principal;
            String email = oauth2User.getAttribute("email");  // 이메일 정보

            log.info("OAuth 로그인: {} - 유저 ID: {}", registrationId, oauthUserId);

            // 데이터베이스에 해당 OAuth 회원이 있는지 확인
            boolean isExistingUser = oauthtbService.oauthCheckNewUser(oauthUserId, registrationId);
            log.info("기존 회원 여부: {}", isExistingUser);

            if (isExistingUser) {
                // 기존 회원일 경우 포인트 및 출석 체크 로직 수행
                processAttendanceAndRedirect(request, response, authentication);
            } else {
                // 신규 회원일 경우 추가 회원가입 페이지로 리디렉션
                log.info("신규 회원, 추가 가입 절차로 리디렉션");
                HttpSession session = request.getSession();
                session.setAttribute("oauthUserId", oauthUserId);
                session.setAttribute("registrationId", registrationId);
                session.setAttribute("email", email);
               
                response.sendRedirect("/oauthSignupSubmit1");
            }
        } else {
            // 기본 로그인 처리 (OAuth 로그인 외의 경우)
            processAttendanceAndRedirect(request, response, authentication);
        }
    }

    // OAuth2 사용자 ID 추출 로직 (각 OAuth 공급자별로 ID 방식이 다름)
    private String getOAuthUserId(OAuth2AuthenticationToken oauthToken, String registrationId) {
        String oauthUserId = null;
        if ("naver".equals(registrationId)) {
            oauthUserId = oauthToken.getPrincipal().getAttribute("id"); // 네이버 ID
        } else if ("google".equals(registrationId)) {
            oauthUserId = oauthToken.getPrincipal().getAttribute("sub"); // 구글 ID
        } else {
            oauthUserId = oauthToken.getPrincipal().getAttribute("id"); // 기타 공급자 ID
        }
        return oauthUserId;
    }

    // 출석 체크와 포인트 지급 후 리디렉션 처리
    private void processAttendanceAndRedirect(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        try {
            // 로그인 성공 후 포인트 지급 및 출석 체크
            boolean isNewAttendance = attendanceService.checkAttendance();
            log.info("오늘 처음 로그인함?: {}", isNewAttendance);

            // 출석 기록이 새로 추가되었으면 쿠키 설정
            if (isNewAttendance) {
                memService.updateMembership(); // 출석 포인트 지급 및 등급 업데이트

                // 자정까지 남은 시간 계산 후 쿠키 설정
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime midnight = now.toLocalDate().atStartOfDay().plusDays(1);
                long secondsUntilMidnight = java.time.Duration.between(now, midnight).getSeconds();

                Cookie attendancePopupCookie = new Cookie("showAttendancePopup", "true");
                attendancePopupCookie.setPath("/");
                attendancePopupCookie.setMaxAge((int) secondsUntilMidnight);
                response.addCookie(attendancePopupCookie);
            }

            log.info("Attendance and membership updated for user: {}", authentication.getName());
        } catch (Exception e) {
            log.error("Error occurred while processing attendance", e);
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
