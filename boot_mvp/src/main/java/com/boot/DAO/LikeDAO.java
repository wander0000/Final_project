package com.boot.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.boot.DTO.ReviewDTO;

@Mapper
public interface LikeDAO 
{	
	// 좋아요 입력
	public void insertLike(HashMap<String, String>param);
	
	// 좋아요 삭제
	public void deleteLike(HashMap<String, String> param);
	
	// 좋아요 중복 확인
	public int isLike(HashMap<String, String>param);	
	
	// 해당 영화 좋아요 갯수
	public Integer numLike(HashMap<String, String>param);	
}



