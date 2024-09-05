package com.boot.DAO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.boot.DTO.UsertbDTO;

@Mapper
public interface UsertbDAO_4 {
	public void updateEmail(@Param("email") String email, @Param("userid") String userid); // 이메일 수정
	public void updatePassword(@Param("ppass") String ppass, @Param("userid") String userid);
	 public void updatePhone(@Param("phone") String phone, @Param("userid") String userid);
	 public void updateBirth(@Param("birth") String birth, @Param("userid") String userid);
	 public void updateAccount(@Param("account") String account, @Param("userid") String userid);
	 public void updateSelectGenre(@Param("genre") String genre, @Param("uuid") String uuid);
	 public void deleteSelectGenre(@Param("uuid") String uuid);
}



