package com.boot.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/login", "/signup", "/signup/**", "/css/**", "/js/**", "/images/**", "/checkUserId", "/send-verification-code", "/verify-code", "/email/check-email", "/findIdPage", "/userid", "/login/userid").permitAll() // 로그인 및 회원가입 페이지는 인증 없이 접근 허용
                .anyRequest().authenticated() // 나머지 페이지는 인증된 사용자만 접근 허용
            .and()
            .formLogin()
                .loginPage("/login") // 로그인 페이지 설정
                .loginProcessingUrl("/auth") // 로그인 폼 제출 URL
                .usernameParameter("userid") // 로그인 ID 파라미터 이름
                .passwordParameter("ppass") // 로그인 비밀번호 파라미터 이름
                .defaultSuccessUrl("/") // 로그인 성공 시 리디렉션 URL
            .and()
            .logout()
                .logoutUrl("/logout") // 로그아웃 URL
                .logoutSuccessUrl("/") // 로그아웃 성공 시 리디렉션 URL
            .and()
            .sessionManagement()
                .sessionFixation().migrateSession(); // 세션 고정 공격 방지

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCryptPasswordEncoder 빈 등록
    }
}
