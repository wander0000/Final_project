package com.boot.DAO;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.boot.DTO.GenreDTO;

@Mapper
public interface GenreDAO_3 {

	public ArrayList<GenreDTO> getAllGenres();  // 장르 리스트 가져오기
	
}
