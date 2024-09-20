package com.boot.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            CustomUserDetails userDetails = (CustomUserDetails)auth.getPrincipal();
            String loginedUserId = userDetails.getUserId();
            request.setAttribute("userid", loginedUserId);
            
            // 등급도 마찬가지로 설정
            String grade = memService.getGrade();
            request.setAttribute("grade", grade);
        }
        return true;
    }
}
