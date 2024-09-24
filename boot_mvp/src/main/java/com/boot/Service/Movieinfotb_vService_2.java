package com.boot.Service;

import java.util.ArrayList;
import java.util.HashMap;

import com.boot.DTO.Movieinfotb_vDTO;

public interface Movieinfotb_vService_2 {
	public ArrayList<Movieinfotb_vDTO> selectAll();
	public Movieinfotb_vDTO getTitleRating(HashMap<String, String> param); //영화 제목, 등급 조회
}
