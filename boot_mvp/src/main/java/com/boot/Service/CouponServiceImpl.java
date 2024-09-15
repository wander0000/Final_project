package com.boot.Service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boot.DAO.CouponDAO;
import com.boot.DAO.UsertbDAO_4;
import com.boot.DTO.CouponDTO;
import com.boot.DTO.UsertbDTO;
import com.boot.Security.CustomUserDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("CouponService")
public class CouponServiceImpl implements CouponService {
	@Autowired
	private SqlSession sqlSession;
	
	//생일쿠폰 발행
	@Override
	public void issueBirthdayCoupons() {
		
		//1. 오늘 생일인 사람 조회하기
		UsertbDAO_4 dao = sqlSession.getMapper(UsertbDAO_4.class);
		List<UsertbDTO> birthdayUsers= dao.getUsersWithTodayBirthday();
		
		//2. 생일 쿠폰 발행
		CouponDAO dao2 = sqlSession.getMapper(CouponDAO.class);
		// 유저들에게 쿠폰 발급
		if(birthdayUsers.size() != 0) {
			for (int i=0; i < birthdayUsers.size(); i++) {
				CouponDTO coupon = new CouponDTO();
				coupon.setType("C");//타입 : C:쿠폰, D:할인권
				coupon.setPeriod(30);//유효기간 30일
				coupon.setReason("생일 축하 쿠폰");//지급사유
				coupon.setRefno(1);//쿠폰 레퍼런스 타입
				coupon.setUuid(birthdayUsers.get(i).getUuid());//사용자 uuid
				
				// 쿠폰 발급
				dao2.insertCoupon(coupon);
			}
		}
	}
	
	
	//쿠폰/할인권 목록조회
	@Override
	public List<CouponDTO> getCouponList(int pageSize,int offset, String type, String acrec) {
		log.info("쿠폰서비스 임플 getCouponList 접근");
		log.info("쿠폰서비스 임플 getCouponList type:"+type+"acrec"+acrec);
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String uuid = userDetails.getUuId();  // 사용자 ID 가져오기

	    try { 
	    	CouponDAO dao = sqlSession.getMapper(CouponDAO.class);
			return dao.getCouponList(uuid,pageSize,offset,type,acrec);
			
	    } catch (Exception e) {
	        log.error("예외 발생: ", e);
	        throw new RuntimeException("쿠폰 목록 조회 중 문제가 발생했습니다", e);
	    }
	}
	
	
	//쿠폰/할인권 목록 count
	@Override
	public int getTotalCountCoupon(String type, String acrec) {
		log.info("쿠폰서비스 임플 getTotalCountCoupon 접근");
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String uuid = userDetails.getUuId();  // 사용자 ID 가져오기
		
		try { 
			CouponDAO dao = sqlSession.getMapper(CouponDAO.class);
			return dao.getTotalCountCoupon(uuid,type, acrec);
			
		} catch (Exception e) {
			log.error("예외 발생: ", e);
			throw new RuntimeException("쿠폰 목록 조회 중 문제가 발생했습니다", e);
		}
	}

	// 미등록 쿠폰 사용가능으로
    @Transactional // 트랜잭션 관리를 위해 사용
    @Override
    public void generateCoupon(String couponno) {
        log.info("쿠폰서비스 임플 generateCoupon 접근");

        // 사용자 정보 가져오기
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String uuid = userDetails.getUuId();  // 사용자 ID 가져오기

        // DAO 연결
        CouponDAO dao = sqlSession.getMapper(CouponDAO.class);

        // 쿠폰이 이미 등록되어 있는지 확인
        String status = dao.checkCouponRegistered(couponno); // 쿠폰이 이미 등록되어 있는지 확인

        // 중복 쿠폰 처리
        if ("A".equals(status)) { 
            throw new RuntimeException("이미 등록된 쿠폰/할인권입니다.");
        } else if ("D".equals(status)) {
            throw new RuntimeException("이미 사용완료된 쿠폰/할인권입니다.");
        } else {
            try {
                // 미등록 쿠폰을 등록
                dao.generateCoupon(couponno, uuid);
            } catch (Exception e) {
                log.error("쿠폰 등록 중 예외 발생: ", e);
                throw new RuntimeException("쿠폰 등록 중 문제가 발생했습니다.", e);
            }
        }
    }
    
    
    
    
}
	
	

