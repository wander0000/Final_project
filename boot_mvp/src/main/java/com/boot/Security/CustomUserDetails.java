package com.boot.Security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


//보안 인증 정보의 중앙화, 코드의 재사용성과 일관성, 캐싱 및 성능향상(을 위해 custom
public class CustomUserDetails implements UserDetails{

    private String userid;
    private String uuid;
    private String username;
    private String ppass;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(String userid, String uuid, String username, String ppass, String email, Collection<? extends GrantedAuthority> authorities) {
        this.userid = userid;
        this.uuid = uuid;
        this.username = username;
        this.ppass = ppass;
        this.email = email;
        this.authorities = authorities;
    }

    // UserDetails 인터페이스의 메서드 구현
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return ppass;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // 추가로 만든 userId 가져오기 메서드
    public String getUserId() {
        return userid;
    }
    
    // 추가로 만든 userId 가져오기 메서드
    public String getUuId() {
    	return uuid;
    }

}
