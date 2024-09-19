package com.boot.Service;

import java.util.ArrayList;
import java.util.HashMap;

import com.boot.DTO.CouponDTO;

public interface CouponService_2 {
	public int checkCoupon(HashMap<String, String> param);
	public ArrayList<CouponDTO> getALLCoupon(HashMap<String, String> param);
	public CouponDTO getCoupon(HashMap<String, String> param);
	public void usingCoupon(HashMap<String, String> param);
}
