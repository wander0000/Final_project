package com.boot.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.boot.DTO.Movieinfotb_vDTO;

@Mapper
public interface Movieinfotb_vDAO_2 {
	public ArrayList<Movieinfotb_vDTO> selectAll(); // 영화 조회
	public Movieinfotb_vDTO getTitleRating(HashMap<String, String> param); //영화 제목, 등급 조회
}