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

import com.boot.Security.CustomUserDetails;
import com.boot.Service.MembershipService;

@Component
public class UserInterceptor implements HandlerInterceptor {
	
	@Autowired
	private MembershipService memService;
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        
//
//        if (!(auth instanceof AnonymousAuthenticationToken)) { // 로그인한 유저이면
//        	
//            String loginedUserId = null;
//
//            // OAuth2 로그인 유저일 경우
//            if (auth instanceof OAuth2AuthenticationToken) {
//                OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) auth;
//                OAuth2User oauthUser = oauthToken.getPrincipal();
//
//                // OAuth2 공급자에 따라 다르게 사용자 ID를 처리해야 할 수 있음
//                // 예시로 네이버, 구글 등의 id 추출
//                String oauthProvider = oauthToken.getAuthorizedClientRegistrationId(); // 공급자 정보 (네이버, 구글 등)
//                
//                // 공급자별로 다른 key로 id를 추출할 수 있음 (ex. 네이버: "response.id", 구글: "sub")
//                if ("naver".equals(oauthProvider)) {
//                    Map<String, Object> responseMap = (Map<String, Object>) oauthUser.getAttributes().get("response");
//                    loginedUserId = (String) responseMap.get("id");
//                } else if ("google".equals(oauthProvider)) {
//                    loginedUserId = (String) oauthUser.getAttributes().get("sub"); // 구글의 경우
//                }
//
//            } else { // 일반 로그인 유저일 경우
//                CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
//                loginedUserId = userDetails.getUserId();
//            }
//
//            if (loginedUserId != null) {
//                // 로그인한 유저의 ID를 request에 추가
//                request.setAttribute("userid", loginedUserId);
//
//                // 로그인한 유저의 등급 정보 가져오기
//                String grade = memService.getGradeByUserId(loginedUserId);
//                request.setAttribute("grade", grade);
//            }
//        }

        return true;
    }
}
