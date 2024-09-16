package com.boot.DAO;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.boot.DTO.UsertbDTO;

@Mapper
public interface UsertbDAO_3 {
    List<UsertbDTO> getUserList(); // User 테이블 가져오기
    void insertUser(UsertbDTO userdto); // 회원 가입
    UsertbDTO getUserByEmail(String email); // 이메일로 회원 정보 가져오기
    UsertbDTO getUserById(String userid); // ID로 회원 정보 가져오기
    void updateUser(UsertbDTO userdto); // 회원 정보 수정
    void deleteUser(String userid); // 회원 탈퇴
    int checkUserIdExists(String userid);  // ID 중복 체크 메소드
    int checkEmailExists(String email);  // 이메일 중복 체크 메소드
    String getUserIdByNameAndEmail(@Param("pname") String pname, @Param("email") String email);  // 아이디 찾기
    void updatePasswordByEmail(@Param("email") String email, @Param("ppass") String ppass);  // 비밀번호 찾기

    // UUID 중복 체크 메서드 추가
    boolean existsByUuid2(String uuid);  // UUID 중복 체크 메소드
}
