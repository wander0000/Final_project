package com.boot.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.boot.DTO.UsertbDTO;

@Mapper
public interface UsertbDAO_3 {
    List<UsertbDTO> getUserList(); // User 테이블 가져오기
    void insertUser(UsertbDTO userdto); // 회원 가입
    UsertbDTO getUserByEmail(String email); // 회원 정보 가져오기
    UsertbDTO getUserById(String userid);
    void updateUser(UsertbDTO userdto); // 회원 정보 수정
    void deleteUser(String userid); // 회원 탈퇴
    int checkUserIdExists(String userid);  // 중복체크 메소드
}



