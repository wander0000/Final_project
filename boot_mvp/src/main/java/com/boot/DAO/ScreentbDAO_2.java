package com.boot.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.boot.DTO.ScreentbDTO;

@Mapper
public interface ScreentbDAO_2 {
	public ArrayList<ScreentbDTO> selecthdr(HashMap<String, String> param); //영화 선택 영역
	public ArrayList<ScreentbDTO> selecthdrbase();
	public ArrayList<ScreentbDTO> selectdtl(HashMap<String, String> param); //영화 상영관 선택 영역
}