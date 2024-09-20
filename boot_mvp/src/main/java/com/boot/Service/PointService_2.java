package com.boot.Service;

import java.util.HashMap;

import com.boot.DTO.PointDTO;

public interface PointService_2 {
	public PointDTO getAllPoint(HashMap<String, String> param);
	public void Call_movie_payment_points(HashMap<String, String> param);
}
