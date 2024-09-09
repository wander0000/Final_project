package com.boot.Config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.boot.DTO.UsertbDTO;
import com.boot.Security.CustomUserDetails;
import com.boot.Service.LoginService;

@Component
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    private LoginService loginservice;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userid = (String) authentication.getName(); 
        String password = (String) authentication.getCredentials(); 

        UsertbDTO userdto = loginservice.getUserById(userid);
        if (userdto == null || userdto.getPpass() == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        if (!passwordEncoder.matches(password, userdto.getPpass())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        // 권한 리스트 생성
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));

        // CustomUserDetails 객체 생성 (UsertbDTO 정보를 기반으로)
        CustomUserDetails customUserDetails = new CustomUserDetails(
            userdto.getUserid(), 
            userdto.getUuid(), 
            userdto.getPname(), 
            userdto.getPpass(), 
            authorities
        );

        // CustomUserDetails를 사용하여 UsernamePasswordAuthenticationToken 생성
        return new UsernamePasswordAuthenticationToken(customUserDetails, null, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // UsernamePasswordAuthenticationToken 지원
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
