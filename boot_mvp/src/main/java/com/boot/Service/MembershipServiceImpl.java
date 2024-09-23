package com.boot.Service;


import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boot.DAO.GradeDAO_4;
import com.boot.DAO.MileageDAO_4;
import com.boot.DAO.PointDAO_4;
import com.boot.DAO.PtHisttbDAO_4;
import com.boot.DTO.MembershipDTO;
import com.boot.DTO.MileageDTO;
import com.boot.DTO.PointDTO;
import com.boot.DTO.PthistDTO;
import com.boot.Security.CustomUserDetails;
import com.boot.Security.CustomUserDetailsService;

import lombok.extern.slf4j.Slf4j;

@Service("MembershipService")
@Slf4j
public class MembershipServiceImpl implements MembershipService {
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private CustomUserDetailsService userService;
	
	@Override
	//트랜잭션 내에서 성공적으로 모든 작업이 완료되면, 트랜잭션은 커밋
	//트랜잭션 내에서 예외가 발생하면, 그 시점까지의 모든 작업이 롤백되어, 데이터베이스 상태가 메서드 호출 전 상태로 복원
	@Transactional(rollbackFor = Exception.class)  // 체크된 예외(SQLException, IOException 등)에서도 롤백되도록 설정
	public void insertMembership(String uuid) {
	    log.info("insertMembership 서비스임플");

	    try {
	        // 1. 사용자 grade 생성 - default Welcome
	        GradeDAO_4 dao = sqlSession.getMapper(GradeDAO_4.class);
	        dao.insertGrade(uuid); 

	        // 2. 가입축하 포인트 적립
	        PtHisttbDAO_4 dao2 = sqlSession.getMapper(PtHisttbDAO_4.class);
	        PthistDTO dto = new PthistDTO();
	        dto.setDescription("가입축하포인트 적립"); 
	        dto.setKind("적립"); 
	        dto.setRecqt(1000); // 가입축하포인트 
	        dto.setUuid(uuid); 
	        dao2.updatePtHis(dto); 

	        // 3. 사용자 point 생성 
	        PointDAO_4 dao3 = sqlSession.getMapper(PointDAO_4.class);
	        dao3.insertPoint(uuid); 

	        // 4. 사용자 mileage 생성 
	        MileageDAO_4 dao4 = sqlSession.getMapper(MileageDAO_4.class);
	        dao4.insertMileage(uuid);

	    } catch (Exception e) {
	        log.error("예외 발생: ", e);
	        throw new RuntimeException("회원가입 중 문제가 발생했습니다.", e);
	    }
	}
	

	@Override
	@Transactional(rollbackFor = Exception.class)
	public MembershipDTO getMembership() {//멤버십 조회(등급, 포인트, 마일리지)
		log.info("getMembership 서비스임플");
		
		// 1. 로그인 여부 확인
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    
	    if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
	        throw new RuntimeException("로그인된 사용자가 없습니다.");
	    }
	    
	    // 2. 로그인된 사용자 정보 가져오기
//		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String uuid = userService.getUuidFromAuthenticatedUser();  // 사용자 UUID 가져오기

	    try {
	        // 1. 사용자 grade 조회
	    	GradeDAO_4 dao = sqlSession.getMapper(GradeDAO_4.class);
			String grade = dao.getUserGrade(uuid); 
			log.info("getMembership 서비스임플 getUserGrade=>"+grade);
			

	        // 2. 포인트 이력조회
			PtHisttbDAO_4 dao2 = sqlSession.getMapper(PtHisttbDAO_4.class);
			List<PthistDTO> pthist = dao2.getUserPtHis(uuid, 30, 5,0);//기본 30일간 내력조회
			log.info("getMembership 서비스임플 getUserPtHis=>"+pthist);

	        // 3. 사용자 point 조회 
	        PointDAO_4 dao3 = sqlSession.getMapper(PointDAO_4.class);
	        PointDTO point =dao3.getUserPoint(uuid); 
	        log.info("getMembership 서비스임플 getUserPoint=>"+point);

	        // 4. 사용자 mileage 조회 
	        MileageDAO_4 dao4 = sqlSession.getMapper(MileageDAO_4.class);
	        MileageDTO mileage = dao4.getMileage(uuid);
	        log.info("getMembership 서비스임플 getMileage=>"+mileage);
	        
	        // 5. 조회한 정보 멤버십DTO에 담에 반환하기
	        MembershipDTO result = new MembershipDTO(grade,pthist,point,mileage);
	        log.info("getMembership 서비스임플 멤버십 조회 result=>"+result);
	        return result;
	        

	    } catch (Exception e) {
	        log.error("예외 발생: ", e);
	        throw new RuntimeException("멤버십 조회 중 문제가 발생했습니다.", e);
	    }
	}
    	
    	
	


	@Override
	@Transactional(rollbackFor = Exception.class)  // 체크된 예외(SQLException, IOException 등)에서도 롤백되도록 설정
	public void updateMembership() {//포인트 업데이트(포인트이력이 생기면 등급, 포인트, 마일리지이 함께 변동됨)
		log.info("updateMembership 멤버십서비스임플");
		
		// 1. 로그인 여부 확인
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    
	    if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
	        throw new RuntimeException("로그인된 사용자가 없습니다.");
	    }
	    
	    // 2. 로그인된 사용자 정보 가져오기
//		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String uuid = userService.getUuidFromAuthenticatedUser();  // 사용자 UUID 가져오기

	    try {
	    	// 1. 포인트 적립(pthisttb에 정보 삽입)
			PtHisttbDAO_4 dao2 = sqlSession.getMapper(PtHisttbDAO_4.class);
			PthistDTO dto = new PthistDTO();
			dto.setDescription("출석이벤트 포인트적립"); 
			dto.setKind("적립"); 
			dto.setRecqt(10); // 출석 포인트
			dto.setUuid(uuid); 
			dao2.updatePtHis(dto); 
			
			// 3. 사용자 point 업데이트
			PointDAO_4 dao3 = sqlSession.getMapper(PointDAO_4.class);
			dao3.updatePoint(uuid); 
			
			// 4. 사용자 mileage 업데이트 
			MileageDAO_4 dao4 = sqlSession.getMapper(MileageDAO_4.class);
			dao4.updateMileage(uuid);

	        // 2. 등급 업데이트
			GradeDAO_4 dao = sqlSession.getMapper(GradeDAO_4.class);
			dao.updateGrade(uuid);

	    } catch (Exception e) {
	        log.error("예외 발생: ", e);
	        throw new RuntimeException("포인트 업데이트 중 문제가 발생했습니다.", e);
	    }
	}



	@Override
	public void deleteMembership() {//포인트 삭제(탈퇴할 때 등급, 포인트, 마일리지이 함께 삭제)
		log.info("deleteMembership 서비스임플");
		
		// 1. 로그인 여부 확인
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    
	    if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
	        throw new RuntimeException("로그인된 사용자가 없습니다.");
	    }
	    
	    // 2. 로그인된 사용자 정보 가져오기
//		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String uuid = userService.getUuidFromAuthenticatedUser();  // 사용자 UUID 가져오기

	    try {
	        // 1. 사용자 grade 삭제
	    	GradeDAO_4 dao = sqlSession.getMapper(GradeDAO_4.class);
			dao.deleteGrade(uuid); 

	        // 2. 포인트 이력 삭제
			PtHisttbDAO_4 dao2 = sqlSession.getMapper(PtHisttbDAO_4.class);
			dao2.deletePtHis(uuid);

	        // 3. 사용자 point 삭제
	        PointDAO_4 dao3 = sqlSession.getMapper(PointDAO_4.class);
	        dao3.deletePoint(uuid); 

	        // 4. 사용자 mileage 삭제 
	        MileageDAO_4 dao4 = sqlSession.getMapper(MileageDAO_4.class);
	        dao4.deleteMileage(uuid);
	        

	    } catch (Exception e) {
	        log.error("예외 발생: ", e);
	        throw new RuntimeException("포인트 업데이트 중 문제가 발생했습니다.", e);
	    }
	}


	@Override
	public List<PthistDTO> getPointHistoryForDays(int days, int pageSize,int offset) {//포인트 이력 목록조회(필터링:기간별)
		log.info("getPointHistoryForDays 서비스임플");
		
		// 1. 로그인 여부 확인
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    
	    if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
	        throw new RuntimeException("로그인된 사용자가 없습니다.");
	    }
	    
	    // 2. 로그인된 사용자 정보 가져오기
//		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String uuid = userService.getUuidFromAuthenticatedUser();  // 사용자 UUID 가져오기

	    try { 
			PtHisttbDAO_4 dao = sqlSession.getMapper(PtHisttbDAO_4.class);
			return dao.getUserPtHis(uuid,days,pageSize,offset);
			
	    } catch (Exception e) {
	        log.error("예외 발생: ", e);
	        throw new RuntimeException("포인트 이력조회 중 문제가 발생했습니다.", e);
	    }
	}

	
	@Override
	public int getTotalCountFiltered(int days) {//포인트 이력 목록 갯수(필터링:기간별)
		log.info("getTotalCountFiltered 서비스임플");
		
		// 1. 로그인 여부 확인
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    
	    if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
	        throw new RuntimeException("로그인된 사용자가 없습니다.");
	    }
	    
	    // 2. 로그인된 사용자 정보 가져오기
//		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String uuid = userService.getUuidFromAuthenticatedUser();  // 사용자 UUID 가져오기

	    try { 
			PtHisttbDAO_4 dao = sqlSession.getMapper(PtHisttbDAO_4.class);
			return dao.getTotalCountFiltered(uuid,days);
			
	    } catch (Exception e) {
	        log.error("예외 발생: ", e);
	        throw new RuntimeException("포인트 이력조회 중 문제가 발생했습니다.", e);
	    }
		
	}

	@Override
	public List<PthistDTO> getPointHistoryBetween(Timestamp startDate, Timestamp endDate, int pageSize,int offset) {//포인트 이력 목록조회(필터링:특정기간)
		log.info("getPointHistoryBetween 서비스임플");
		
		// 1. 로그인 여부 확인
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    
	    if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
	        throw new RuntimeException("로그인된 사용자가 없습니다.");
	    }
	    
	    // 2. 로그인된 사용자 정보 가져오기
//		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String uuid = userService.getUuidFromAuthenticatedUser();  // 사용자 UUID 가져오기

	    try { 
			PtHisttbDAO_4 dao = sqlSession.getMapper(PtHisttbDAO_4.class);
			return dao.getUserPtHisBetween(uuid,startDate,endDate,pageSize,offset);
			
	    } catch (Exception e) {
	        log.error("예외 발생: ", e);
	        throw new RuntimeException("포인트 이력조회 중 문제가 발생했습니다.", e);
	    }
	}


	@Override
	public int getTotalCountBetween(Timestamp startDate, Timestamp endDate) {
		log.info("getTotalCountFiltered 서비스임플");
		
		// 1. 로그인 여부 확인
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    
	    if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
	        throw new RuntimeException("로그인된 사용자가 없습니다.");
	    }
	    
	    // 2. 로그인된 사용자 정보 가져오기
//		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String uuid = userService.getUuidFromAuthenticatedUser();  // 사용자 UUID 가져오기

	    try { 
			PtHisttbDAO_4 dao = sqlSession.getMapper(PtHisttbDAO_4.class);
			return dao.getTotalCountBetween(uuid,startDate,endDate);
			
	    } catch (Exception e) {
	        log.error("예외 발생: ", e);
	        throw new RuntimeException("포인트 이력조회 중 문제가 발생했습니다.", e);
	    }
		
	}


	@Override
	public String getGrade() {//멤버십 등급 조회
//		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String uuid = userService.getUuidFromAuthenticatedUser();  // 사용자 UUID 가져오기

	    try {
	        // 1. 사용자 grade 조회
	    	GradeDAO_4 dao = sqlSession.getMapper(GradeDAO_4.class);
			String grade = dao.getUserGrade(uuid); 
			log.info("getMembership 서비스임플 getUserGrade=>"+grade);
			return grade;
	    } catch (Exception e) {
	        log.error("예외 발생: ", e);
	        throw new RuntimeException("포인트 이력조회 중 문제가 발생했습니다.", e);
	    }
		
	}


	
	


	
	
}
