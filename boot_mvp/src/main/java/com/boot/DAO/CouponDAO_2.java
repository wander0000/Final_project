package com.boot.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.boot.DTO.CouponDTO;

@Mapper
public interface CouponDAO_2 {
	public int checkCoupon(HashMap<String, String> param);
	public ArrayList<CouponDTO> getALLCoupon(HashMap<String, String> param); //사용가능한 전체 쿠폰
	public CouponDTO getCoupon(HashMap<String, String> param); //선택한 쿠폰 내용
	public void usingCoupon(HashMap<String, String> param); //사용한 쿠폰 기록
}
