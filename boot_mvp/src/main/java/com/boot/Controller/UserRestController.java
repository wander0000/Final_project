package com.boot.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.DTO.UsertbDTO;
import com.boot.Security.CustomUserDetails;
import com.boot.Service.LoginService;
import com.boot.Service.UserService_4;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/user")
public class UserRestController {
	
	@Autowired
	private UserService_4 userService;
	
	
	
	
	@DeleteMapping
	public ResponseEntity<Map<String, Object>>  deleteUser() {
		 int deletedRows = userService.deleteUserInfo();
	        
	        Map<String, Object> response = new HashMap<>();
	        response.put("deletedRows", deletedRows);
	        
	        if (deletedRows > 0) {
	            response.put("message", "회원 정보가 성공적으로 삭제되었습니다.");
	            SecurityContextHolder.clearContext();
	            return ResponseEntity.ok(response);
	        } else {
	            response.put("message", "회원 정보를 찾을 수 없습니다.");
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	        }
	}
    
	
			
	// 이메일 수정
    @PatchMapping("/email")
//    public ResponseEntity<String> updateEmail(@RequestBody UsertbDTO user, Authentication authentication) {
    public ResponseEntity<String> updateEmail(@RequestBody UsertbDTO user) {
    	log.info("@# updateEmail로 접근");

        // 서비스에 UserDTO와 Authentication 정보를 넘김
        userService.updateEmail(user);

        return ResponseEntity.ok("이메일이 성공적으로 변경되었습니다.");
    }
    
    // 비번 수정
    @PatchMapping("/password")
    public ResponseEntity<String> updatePassword(@RequestBody UsertbDTO user) {
    	log.info("@# updatePassword로 접근");
    	userService.updatePassword(user);
    	
    	return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");
    }
    
    // 핸드폰번호 수정
    @PatchMapping("/phone")
    public ResponseEntity<String> updatePhone(@RequestBody UsertbDTO user) {
    	log.info("@# updatePhone로 접근");
    	userService.updatePhone(user);
    	
    	return ResponseEntity.ok("휴대폰번호가 성공적으로 변경되었습니다.");
    }
    
    // 생년월일 수정
    @PatchMapping("/birth")
    public ResponseEntity<String> updateBirth(@RequestBody UsertbDTO user) {
    	log.info("@# updateBirth로 접근");
    	userService.updateBirth(user);
    	
    	return ResponseEntity.ok("생년월일이 성공적으로 변경되었습니다.");
    }
    
    // 환불계좌 수정
    @PatchMapping("/account")
    public ResponseEntity<String> updateAccount(@RequestBody UsertbDTO user) {
    	log.info("@# updateAccount로 접근");
    	userService.updateAccount(user);
    	
    	return ResponseEntity.ok("환불계좌가 성공적으로 변경되었습니다.");
    }
    
    // 선호장르 수정
    @PostMapping("/genre")
    public ResponseEntity<String> updateSelectGenre(@RequestBody HashMap<String, String> param) {
    	log.info("@# updateSelectGenre로 접근");

		// JSON에서 "genre"라는 key로 넘어온 값을 처리
		String genreList = param.get("genre");
		
		// 서비스에 전달
		userService.updateSelectGenre(genreList);
		
		return ResponseEntity.ok("선호장르가 성공적으로 변경되었습니다.");
    }



}
