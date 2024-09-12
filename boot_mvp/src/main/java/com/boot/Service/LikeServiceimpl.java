package com.boot.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.DAO.LikeDAO;
import com.boot.DAO.ReviewDAO;
import com.boot.DTO.ReviewDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LikeServiceimpl implements LikeService{	
	
	@Autowired
    private SqlSession sqlSession;

	@Override
	public void insertLike(HashMap<String, String>param) 
	{
		LikeDAO dao = sqlSession.getMapper(LikeDAO.class);
		dao.insertLike(param);
	}

	@Override
	public void deleteLike(HashMap<String, String> param) 
	{
		LikeDAO dao = sqlSession.getMapper(LikeDAO.class);
		dao.deleteLike(param);		
	}

	@Override
	public int isLike(HashMap<String, String> param) 
	{
		LikeDAO dao = sqlSession.getMapper(LikeDAO.class);
		return dao.isLike(param);		
	}

	@Override
	public Integer numLike(HashMap<String, String> param) 
	{
		LikeDAO dao = sqlSession.getMapper(LikeDAO.class);
		return dao.numLike(param);
	}


	
}







