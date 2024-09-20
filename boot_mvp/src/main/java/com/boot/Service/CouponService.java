package com.boot.Service;

import java.util.List;

import com.boot.DTO.CouponDTO;

public interface CouponService {
	//생일쿠폰 발행
	public void issueBirthdayCoupons();
	//쿠폰/할인권 목록조회
	public List<CouponDTO> getCouponList(int pageSize,int offset,String type, String acrec);
	//쿠폰/할인권 목록 count
	public int getTotalCountCoupon(String type, String acrec);
	//미등록 쿠폰 사용가능으로
	public void generateCoupon(String couponno);
	// 쿠폰 발급 > 쿠폰번호 반환
    public String issueCoupon(CouponDTO coupon);
}