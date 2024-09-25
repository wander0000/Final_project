package com.boot.Service;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.DAO.SelecGenretbDAO_4;
import com.boot.DAO.UsertbDAO_3;
import com.boot.DAO.UsertbDAO_4;
import com.boot.DTO.SelecGenretbDTO;
import com.boot.DTO.UsertbDTO;
import com.boot.Security.CustomUserDetails;
import com.boot.Security.CustomUserDetailsService;

import lombok.extern.slf4j.Slf4j;

@Service("UserService_4")
@Slf4j
public class UserServiceImpl_4 implements UserService_4 {
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomUserDetailsService userService;

	@Override
	public void updateEmail(UsertbDTO user) {
		log.info("updateEmail 서비스임플");
		
//		String loginedUserId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String loginedUserId = userDetails.getUserId();  // 사용자 ID 가져오기
		log.info("@# currentUserId=>"+loginedUserId);
		
		String email = user.getEmail();
        log.info("@# 바꿀 이메일=>"+email);
		
		UsertbDAO_4 dao = sqlSession.getMapper(UsertbDAO_4.class);
		
		dao.updateEmail(email, loginedUserId);
	}

	@Override
	public void updatePassword(UsertbDTO user) {
		log.info("updatePassword 서비스임플");
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String loginedUserId = userDetails.getUserId();  // 사용자 ID 가져오기
		String ppass = passwordEncoder.encode(user.getPpass());//requestbody에서 꺼낸 비밀번호(사용자가 바꾸고자 하는 비번)를 인코딩
		log.info("updatePassword ppass========>"+ppass);
		UsertbDAO_4 dao = sqlSession.getMapper(UsertbDAO_4.class);
		
		dao.updatePassword(ppass, loginedUserId);
		
	}
	
	@Override
	public void updatePhone(UsertbDTO user) {
		log.info("updatePhone 서비스임플");
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String loginedUserId = userDetails.getUserId();  // 사용자 ID 가져오기
		String phone = user.getPhone();
        log.info("@# 바꿀 핸드폰번호=>"+phone);
		UsertbDAO_4 dao = sqlSession.getMapper(UsertbDAO_4.class);
		
		dao.updatePhone(phone, loginedUserId);
	}
	
	@Override
	public void updateBirth(UsertbDTO user) {
		log.info("updateBirth 서비스임플");
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String loginedUserId = userDetails.getUserId();  // 사용자 ID 가져오기
		String birth = user.getBirth();
        log.info("@# 바꿀 생년월일=>"+birth);
		UsertbDAO_4 dao = sqlSession.getMapper(UsertbDAO_4.class);
		
		dao.updateBirth(birth, loginedUserId);
		
	}
	
	@Override
	public void updateAccount(UsertbDTO user) {
		log.info("updateAccount 서비스임플");
		UsertbDAO_4 dao = sqlSession.getMapper(UsertbDAO_4.class);

	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String account = user.getAccount();
	    log.info("@# 바꿀 환불계좌=>"+account);
	    String loginedUserId ="";
	    if (authentication.getPrincipal() instanceof OAuth2User) {//Oauth 로그인 유저
	    	String uuid = userService.getUuidFromAuthenticatedUser();  // 사용자 UUID 가져오기
	           dao.updateAccountOauth(account, uuid);
	    }else {//일반 유저
	    	CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    	loginedUserId = userDetails.getUserId();  // 사용자 ID 가져오기
	    	dao.updateAccount(account, loginedUserId);
	    }
	    
	}
	
	
	
	@Override
	//트랜잭션 내에서 성공적으로 모든 작업이 완료되면, 트랜잭션은 커밋
	//트랜잭션 내에서 예외가 발생하면, 그 시점까지의 모든 작업이 롤백되어, 데이터베이스 상태가 메서드 호출 전 상태로 복원
	@Transactional  // 트랜잭션이 적용된 메서드
	public void updateSelectGenre(String genreList) {
		log.info("updateSelectGenre 서비스임플");
		log.info("@# 바꿀 장르리스트=>"+genreList);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String uuid = userService.getUuidFromAuthenticatedUser();  // 사용자 UUID 가져오기
    	
		SelecGenretbDAO_4 dao = sqlSession.getMapper(SelecGenretbDAO_4.class);
		
		// 선호장르번호가 ,로 구분된 문자열로 넘어오기 때문에 분리해서 배열로 만들어 insert해 줌
	    if (genreList != null) {
	    	dao.deleteSelectGenre(uuid);//수정전 데이터 삭제
	    	// Stream을 사용하여 문자열을 String 배열로 변환
	    	String[] arr = Arrays.stream(genreList.split(","))
                    .map(String::trim)  // 각 문자열의 앞뒤 공백을 제거
                    .toArray(String[]::new);
	    	for(int i = 0; i<arr.length; i++){
	    		if (authentication.getPrincipal() instanceof OAuth2User) {//Oauth 로그인 유저
	    			dao.updateSelectGenreOauth(arr[i], uuid); //새 데이터 입력
	    	    }else {//일반 유저
	    	    	dao.updateSelectGenre(arr[i], uuid); //새 데이터 입력
	    	    }
	    	}	
	    }
	}
	
	@Override
	public String getSelectGenre() {
		log.info("getSelectGenre 서비스임플");
		
//		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String uuid = userService.getUuidFromAuthenticatedUser();  // 사용자 UUID 가져오기
		SelecGenretbDAO_4 dao = sqlSession.getMapper(SelecGenretbDAO_4.class);
		String genreList="";//리턴할 문자열 초기화, 로그인유져가 없으면 빈문자열로 반환됨
		
		if(uuid != null || uuid !="") {
			  //선호장르가 ,로 구분된 문자열로 넘어오기 때문에 분리해서 배열로 만들어 insert해 줌
	    	List<String> list = dao.getUserGenre(uuid);//선호장르 이름으로가 져오기
	    	// list의 요소들을 ','로 연결된 문자열로 변환
	    	genreList = list.stream().collect(Collectors.joining(",")); // 콤마로 각 요소 연결
		}
		
		return genreList;
	}
	
	@Override
	public void deleteSelectGenre(String uuid) {
		log.info("updateSelectGenre 서비스임플");
		log.info("updateSelectGenre uuid========>"+uuid);
		SelecGenretbDAO_4 dao = sqlSession.getMapper(SelecGenretbDAO_4.class);
		
		dao.deleteSelectGenre(uuid);
	}

	@Override
	public String getUserPhoneNumber(String uuid) {
		log.info("getUserPhoneNumber 서비스임플");
		log.info("getUserPhoneNumber uuid========>"+uuid);
		UsertbDAO_4 dao = sqlSession.getMapper(UsertbDAO_4.class);
		
		return dao.getUserPhoneNumber(uuid);
	}

	
	@Override
	public int deleteUserInfo() {
		log.info("deleteUserInfo 서비스임플");
		UsertbDAO_4 dao = sqlSession.getMapper(UsertbDAO_4.class);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String uuid = userService.getUuidFromAuthenticatedUser();  // 사용자 UUID 가져오기
		
		int deletedRows = 0;  // 삭제된 행의 수 저장 변수
	    
	    if (authentication.getPrincipal() instanceof OAuth2User) { // Oauth 로그인 유저
	        deletedRows = dao.deleteUserInfoOauth(uuid);
	    } else { // 일반 유저
	        deletedRows = dao.deleteUserInfo(uuid);
	    }
	    
	    // 삭제된 행의 수 반환
	    return deletedRows;

	}
	
}
