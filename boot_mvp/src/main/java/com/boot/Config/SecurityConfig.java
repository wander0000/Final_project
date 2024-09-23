package com.boot.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.boot.Security.CustomLoginSuccessHandler;
import com.boot.Service.CustomOAuth2UserService;
import com.boot.Service.OauthtbService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final OauthtbService oauthtbService;

    // CustomOAuth2UserService 및 OauthtbService 의존성 주입
    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService, OauthtbService oauthtbService) {
        this.customOAuth2UserService = customOAuth2UserService;
        this.oauthtbService = oauthtbService;
    }
    
    
    @Autowired
	private CustomLoginSuccessHandler customLoginSuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/login", "/signup", "/signup/**", "/css/**", "/js/**", "/images/**",
                        "/checkUserId", "/verify-code", "/oauthCheckUserid","/oauthSignupSubmit1","/main",
                        "/findIdPage", "/userid", "/findPwPage", "/findPassword", "/resetPwPage", "/resetPassword", "/email/**")
                .permitAll()  // 인증 없이 접근 가능하게 설정
            .anyRequest().authenticated()
            .and()  // authorizeRequests 체인 종료
            .csrf()
                .ignoringAntMatchers("/email/**")  // 이메일 인증에 CSRF 비활성화
            .and()  // csrf 체인 종료
            .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/auth")
                .usernameParameter("userid")
                .passwordParameter("ppass")
                .defaultSuccessUrl("/main")
                .successHandler(customLoginSuccessHandler)//로그인 시 출석포인트 지급하기 위해 custum
            .and()  // formLogin 체인 종료
            .oauth2Login()
                .loginPage("/login")  // 소셜 로그인도 동일한 로그인 페이지 사용
                .defaultSuccessUrl("/main")  // 로그인 성공 시 메인 페이지로 리디렉션
                .userInfoEndpoint()
                .userService(customOAuth2UserService)  // 의존성 주입된 customOAuth2UserService 사용
            .and()  // userInfoEndpoint 체인 종료
            .successHandler(customLoginSuccessHandler)//로그인 시 출석포인트 지급하기 위해 custum
//            .successHandler((HttpServletRequest request, HttpServletResponse response, Authentication authentication) -> {
//                OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
//                String registrationId = oauthToken.getAuthorizedClientRegistrationId();
//                String oauthUserId = oauthToken.getPrincipal().getAttribute("sub");  // 구글 기준
//                if ("naver".equals(registrationId)) {
//                    oauthUserId = oauthToken.getPrincipal().getAttribute("id");  // 네이버 기준
//                    log.info("#######################################oauthUserId: "+oauthUserId);
//                }
//                boolean isExistingUser = oauthtbService.oauthCheckNewUser(oauthUserId, registrationId);
//                log.info("#######################################isExistingUser: "+isExistingUser);
//                
//                if (isExistingUser) {
//                    response.sendRedirect("/main");  // 기존 회원이면 홈 페이지로 리다이렉트
//                } else {
//                    response.sendRedirect("/oauthSignupSubmit1");  // 신규 회원이면 추가 회원가입 페이지로 리다이렉트
//                }
//            })  // OAuth2 로그인 성공 후 로직 추가
            .and()  // oauth2Login 체인 종료
            .logout()  // 로그아웃 설정
                .logoutUrl("/logout")
                .logoutSuccessUrl("/main")
                .invalidateHttpSession(true) // 세션 무효화
//                .deleteCookies("JSESSIONID", "token") // 쿠키 삭제
            .and()  // logout 체인 종료
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
