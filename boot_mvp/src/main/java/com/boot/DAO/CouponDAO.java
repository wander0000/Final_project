package com.boot.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.boot.DTO.CouponDTO;
import com.boot.DTO.ReservetbDTO;

@Mapper
public interface CouponDAO {
	//쿠폰 목록조회
	public List<CouponDTO> getCouponList(@Param("uuid") String uuid,@Param("pageSize") int pageSize, @Param("offset") int offset,
										@Param("type") String type, @Param("acrec") String acrec); 
	//쿠폰 목록 count
	public int getTotalCountCoupon(@Param("uuid") String uuid,  @Param("type") String type, @Param("acrec") String acrec); 
	//쿠폰 등록(타입,기한,지급사유,쿠폰종류 사용자 등록)
	public void insertCoupon(CouponDTO coupon);
	//미등록 쿠폰 사용가능으로
	public void generateCoupon(@Param("couponno") String couponno, @Param("uuid") String uuid);
	// 쿠폰 상태확인 (A:사용가능, D:사용완료,N:마등록)
	public String checkCouponRegistered(@Param("couponno") String couponno);
	// 방금 등록된 쿠폰번호 조회하기(쿠폰 등록 후 같은 트랜젝션 안에서 실행해야)
	public String selectLastCoupon(@Param("uuid") String uuid);
}
