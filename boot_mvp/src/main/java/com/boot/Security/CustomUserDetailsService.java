package com.boot.Security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
                user.getPname(),
                user.getPpass(),
                Collections.singletonList(authority)  // 권한 목록
        );
    }

}

