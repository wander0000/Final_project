package com.boot.Service;

import com.boot.DTO.UsertbDTO;

public interface UserService_4 {
	//	회원정보수정
	 public void updateEmail(UsertbDTO user);
//	 public void updatePassword(String ppass, String userid);
	 public void updatePassword(UsertbDTO user);
	 public void updatePhone(UsertbDTO user);
	 public void updateBirth(UsertbDTO user);
	 public void updateAccount(UsertbDTO user);
//	 public String getUuidFromAuthenticatedUser();
	 
	 public void updateSelectGenre(String genrenlList);
	 public void deleteSelectGenre(String uuid);
	 public String getSelectGenre();
}
