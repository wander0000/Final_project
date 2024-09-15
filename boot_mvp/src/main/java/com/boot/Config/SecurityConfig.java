package com.boot.Config;

import com.boot.Security.CustomLogoutHandler;
import com.boot.Service.CustomOAuth2UserService;
import com.boot.Service.OauthtbService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

@Configuration
public class SecurityConfig {


//    private final CustomOAuth2UserService customOAuth2UserService;
//    private final OauthtbService oauthtbService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeRequests()
	            .antMatchers("/login", "/signup", "/signup/**", "/css/**", "/js/**", "/images/**","/main","/movie/**", 
	                    "/checkUserId", "/verify-code", 
	                    "/ticketing/logincheck", "/ticketing/movieselect", "/ticketing/theatershow", "/ticketing/movieshow",
	                    "/ticketing/dateshow", "/ticketing/datetxt", "/ticketing/datetxtparam", "/ticketing/saveSessionParams",
	                    "/findIdPage", "/userid", // 여기서 /userid 추가
	                    "/findPwPage", "/findPassword", "/resetPwPage", "/resetPassword", "/email/**")
	            .permitAll()  // 인증 없이 접근 가능하게 설정
	        .anyRequest().authenticated()
	        .and()
	        .csrf()
	            .ignoringAntMatchers("/email/**") // 필요에 따라 CSRF 무시할 경로 설정  // 이메일인증은 로그인이 되어있지않은 상태이기때문에 예외처리함
	        .and()
	        .formLogin()
	            .loginPage("/login")
	            .loginProcessingUrl("/auth")
	            .usernameParameter("userid")
	            .passwordParameter("ppass")
	            .defaultSuccessUrl("/main")
	            .successHandler((request, response, authentication) -> {
	                String redirectUrl = request.getParameter("redirect");
	                if (redirectUrl == null || redirectUrl.isEmpty()) {
	                    redirectUrl = "/main"; // 기본 리디렉션 URL
	                }
	                response.sendRedirect(redirectUrl);
	            })
	        .and()
	        .logout()
	            .logoutUrl("/logout")
	            .logoutSuccessUrl("/main")
	            .addLogoutHandler(new CustomLogoutHandler()) // 선택한 영화 정보 (세션) 삭제
	            .invalidateHttpSession(true) // 세션 무효화
	        .and()
	        .sessionManagement()
	            .sessionFixation().none();

	    return http.build();
	}




    // PasswordEncoder 빈을 등록
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // BCryptPasswordEncoder 사용
    }
}
