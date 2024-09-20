package com.boot.Service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boot.DAO.AttendanceDAO;
import com.boot.DAO.CouponDAO;
import com.boot.DAO.UsertbDAO_4;
import com.boot.DTO.CouponDTO;
import com.boot.DTO.UsertbDTO;
import com.boot.Security.CustomUserDetails;
import com.boot.Security.CustomUserDetailsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("AttendenceService")
public class AttendenceServiceImpl implements AttendenceService {
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private CustomUserDetailsService userService;
	
	// 출석 기록 추가
    @Override
    @Transactional
    public boolean checkAttendance() {
        log.info("AttendenceServiceImpl.checkAttendance() 시작");
        try {
            // 사용자 정보 가져오기
//    		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        	String uuid = userService.getUuidFromAuthenticatedUser();  // 사용자 UUID 가져오기
            log.info("checkAttendance, UUID: {}", uuid);

            // 오늘 출석 체크 여부 확인
            AttendanceDAO dao = sqlSession.getMapper(AttendanceDAO.class);
            int todayAttendance = dao.checkTodayAttendance(uuid);
            if (todayAttendance == 0) {
                dao.insertAttendance(uuid);
                log.info("출석 기록 추가 완료, UUID: {}", uuid);
                return true;  // 오늘 출석이 새로 추가됨
            } else {
                log.info("이미 오늘 출석 체크가 완료되었습니다. UUID: {}", uuid);
                return false; // 이미 출석 체크 완료됨
            }
        } catch (Exception e) {
            log.error("checkAttendance 처리 중 오류 발생", e);
            throw e;  // 상위로 예외를 전파하여 트랜잭션 롤백
        }
    }

	// 출석 만근자 uuid 조회
	@Override
	public List<String> checkMonthlyAttendance() {
		log.info("AttendenceService임플 checkMonthlyAttendance 접근");
		
		AttendanceDAO dao = sqlSession.getMapper(AttendanceDAO.class);
		return dao.checkMonthlyAttendance();
	}

	// 유저의 현재 달 출석조회
	@Override
	public List<Integer> checkUserAttendance() {
		log.info("AttendenceService임플 checkUserAttendance 접근");
//		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String uuid = userService.getUuidFromAuthenticatedUser();  // 사용자 UUID 가져오기
		
		AttendanceDAO dao = sqlSession.getMapper(AttendanceDAO.class);
		return dao.checkUserAttendance(uuid);
	}
    
    
    
    
}
	
	

