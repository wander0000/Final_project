package com.boot.Service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);

        // 네이버 사용자 정보 처리
        Map<String, Object> attributes = oauth2User.getAttributes();
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        String userId = (String) response.get("id");
        String email = (String) response.get("email");
        String name = (String) response.get("name");

        // 필요한 사용자 정보들로 CustomUserDetails를 생성하거나 처리
        return new DefaultOAuth2User(
            Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
            response,
            "email"
        );
    }
}
