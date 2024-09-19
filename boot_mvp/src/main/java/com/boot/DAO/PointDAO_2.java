package com.boot.DAO;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.boot.DTO.PointDTO;

@Mapper
public interface PointDAO_2 {
	public PointDTO getAllPoint(HashMap<String, String> param); // 로그인한 계정의 포인트
	public void Call_movie_payment_points(HashMap<String, String> param); //포인트 처리(포인트 추가 및 차감)
}