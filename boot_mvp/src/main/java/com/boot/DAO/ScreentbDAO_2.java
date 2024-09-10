package com.boot.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.DTO.ScreentbDTO;

@Mapper
public interface ScreentbDAO_2 {
	public ArrayList<ScreentbDTO> selecthdr(HashMap<String, String> param); //영화 선택 영역
	public ArrayList<ScreentbDTO> selecthdrbase();
	public ArrayList<ScreentbDTO> selectdtl(HashMap<String, String> param); //영화 상영관 선택 영역
	public ScreentbDTO selectmovieinfo(@RequestParam HashMap<String, String> param); //최종적으로 선택한 영화
	public void Seat_deduction(HashMap<String, String> param); //선택한 좌석 수만큼 차감
}