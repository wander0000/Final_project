package com.boot.Service;

import java.util.List;

import com.boot.DTO.UsertbDTO;

public interface LoginService {
    List<UsertbDTO> getUserList(); // User 테이블 가져오기
    void insertUser(UsertbDTO userdto); // 회원 가입
    UsertbDTO getUserByEmail(String email); // 회원 정보 가져오기
    UsertbDTO getUserById(String userid);
    void updateUser(UsertbDTO userdto); // 회원 정보 수정
    void deleteUser(String userid); // 회원 탈퇴
    boolean checkUserIdExists(String userid); // 중복 체크 메소드
}
