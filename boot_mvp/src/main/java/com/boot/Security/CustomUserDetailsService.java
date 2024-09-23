package com.boot.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

import com.boot.DAO.UsertbDAO_3;
import com.boot.DTO.OauthtbDTO;
import com.boot.DTO.UsertbDTO;
import com.boot.Service.OauthtbService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UsertbDAO_3 usertbDAO_3;

    public CustomUserDetailsService(UsertbDAO_3 usertbDAO_3) {
        this.usertbDAO_3 = usertbDAO_3;
    }
    
    @Autowired
    private OauthtbService oauthService;

    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
        // userid를 사용하여 사용자 정보 가져오기
        UsertbDTO user = usertbDAO_3.getUserById(userid);

        if (user == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + userid);
        }

        // 사용자 권한 설정 (여기서는 예시로 ROLE_USER 권한을 부여)
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("USER");

        return new CustomUserDetails(
                user.getUserid(),
                user.getUuid(),
                user.getPname(),
                user.getPpass(),
                user.getEmail(),
                user.getPhone(),
                Collections.singletonList(authority)  // 권한 목록
        );
    }
    
    public String getUuidFromAuthenticatedUser() {
    		
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
                CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
                return userDetails.getUuId();  // uuid 값을 가져옴
            } else if(authentication != null && authentication.getPrincipal() instanceof OAuth2User){
            	log.info("naver로그인 OAuth2User");
            	
                OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
                String registrationId = oauthToken.getAuthorizedClientRegistrationId();// 공급자 ID (google, naver 등)
                String oauthUserId = "";
                if ("naver".equals(registrationId)) {
                    oauthUserId = oauthToken.getPrincipal().getAttribute("id");  // 네이버 기준
                    log.info("naver로그인 oauthUserId"+oauthUserId);
                }else if("google".equals(registrationId)) {
                	oauthUserId = oauthToken.getPrincipal().getAttribute("sub");  // 구글 기준
                }else {
                	oauthUserId = oauthToken.getPrincipal().getAttribute("id");  // 페이스북 기준
                }
                OauthtbDTO user = oauthService.oauthGetUserByuniq(oauthUserId);
                log.info("user:"+user+"oauthUserId"+oauthUserId);
                return user.getUuid();
                
            } else {
            
                // 인증되지 않은 사용자이거나, 사용자 정보가 CustomUserDetails가 아닌 경우
                throw new IllegalStateException("Authentication is not valid or the user is not logged in.");
            }
    		
    }
    public String getNameFromAuthenticatedUser() {
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
    		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
    		return userDetails.getUsername();  // username 값을 가져옴
    	} else if(authentication != null && authentication.getPrincipal() instanceof OAuth2User){
    		log.info("naver로그인 OAuth2User");
    		
    		OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
    		String registrationId = oauthToken.getAuthorizedClientRegistrationId();// 공급자 ID (google, naver 등)
    		String oauthUserId = "";
    		if ("naver".equals(registrationId)) {
    			oauthUserId = oauthToken.getPrincipal().getAttribute("id");  // 네이버 기준
    			log.info("naver로그인 oauthUserId"+oauthUserId);
    		}else if("google".equals(registrationId)) {
    			oauthUserId = oauthToken.getPrincipal().getAttribute("sub");  // 구글 기준
    		}else {
    			oauthUserId = oauthToken.getPrincipal().getAttribute("id");  // 페이스북 기준
    		}
    		OauthtbDTO user = oauthService.oauthGetUserByuniq(oauthUserId);
    		log.info("user:"+user+"oauthUserId"+oauthUserId);
    		return user.getPname();
    		
    	} else {
    		
    		// 인증되지 않은 사용자이거나, 사용자 정보가 CustomUserDetails가 아닌 경우
    		throw new IllegalStateException("Authentication is not valid or the user is not logged in.");
    	}
    	
    }

    
    
}

