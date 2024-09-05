package com.boot.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import com.boot.DTO.MovietbDTO;

@Mapper
public interface MovietbDAO_2 {
	public ArrayList<MovietbDTO> selectAll(); // 영화 조회
	public String getTitle(HashMap<String, String> param); //영화 제목 조회
}
