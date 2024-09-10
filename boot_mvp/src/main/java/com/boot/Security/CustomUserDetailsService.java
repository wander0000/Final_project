package com.boot.Security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

import com.boot.DAO.UsertbDAO_3;
import com.boot.DTO.UsertbDTO;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsertbDAO_3 usertbDAO_3;

    public CustomUserDetailsService(UsertbDAO_3 usertbDAO_3) {
        this.usertbDAO_3 = usertbDAO_3;
    }

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
                Collections.singletonList(authority)  // 권한 목록
        );
    }
    
    public String getUuidFromAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            return userDetails.getUuId();  // uuid 값을 가져옴
        } else {
            // 인증되지 않은 사용자이거나, 사용자 정보가 CustomUserDetails가 아닌 경우
            throw new IllegalStateException("Authentication is not valid or the user is not logged in.");
        }
    }

    
    
}

