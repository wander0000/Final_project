package com.boot.DAO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UsertbDAO_4 {
    void updateEmail(@Param("email") String email, @Param("userid") String userid); // 이메일 수정
}



