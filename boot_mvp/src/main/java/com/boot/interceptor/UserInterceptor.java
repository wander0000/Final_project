package com.boot.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.boot.DTO.OauthtbDTO;
import com.boot.Security.CustomUserDetails;
import com.boot.Service.MembershipService;
import com.boot.Service.OauthtbService;

@Component
public class UserInterceptor implements HandlerInterceptor {
	
	@Autowired
	private MembershipService memService;
	
	@Autowired
	private OauthtbService oauthService;
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        

        if (!(auth instanceof AnonymousAuthenticationToken)) { // 로그인한 유저이면
        	
            String loginedUserId = null;

            // OAuth2 로그인 유저일 경우
            if (auth instanceof OAuth2AuthenticationToken) {
                OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) auth;
                String registrationId = oauthToken.getAuthorizedClientRegistrationId();// 공급자 ID (google, naver 등)
                String oauthUserId = null;
                if ("naver".equals(registrationId)) {
                    oauthUserId = oauthToken.getPrincipal().getAttribute("id");  // 네이버 기준
                }else if("google".equals(registrationId)) {
                	oauthUserId = oauthToken.getPrincipal().getAttribute("sub");  // 구글 기준
                }else {
                	oauthUserId = oauthToken.getPrincipal().getAttribute("id");  // 페이스북 기준
                }
                
                loginedUserId = oauthUserId;

            } else { // 일반 로그인 유저일 경우
                CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
                loginedUserId = userDetails.getUserId();
            }

            if (loginedUserId != null && oauthService.oauthGetUserByuniq(loginedUserId) != null) {
                // 로그인한 유저의 ID를 request에 추가
                request.setAttribute("userid", loginedUserId);

                // 로그인한 유저의 등급 정보 가져오기
                String grade = memService.getGrade();
                request.setAttribute("grade", grade);
            }
        }

        return true;
    }
}
