package com.boot.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.boot.DTO.ReserdtltbDTO;

@Mapper
public interface ReserdtltbDAO_2 {
	public void insertReserdtl(HashMap<String, String> param); // 데이터 추가
	public int selected_count(HashMap<String, String> param); //선택한 영화 상영 시간에 선택된 좌석 갯수
	public ArrayList<ReserdtltbDTO> selected_seat(HashMap<String, String> param); //선택한 영화 상영 시간에 선택된 좌석 확인
}
