package com.boot.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.DAO.ReviewDAO;
import com.boot.DTO.ReviewDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReviewServiceimpl implements ReviewService{	
	
	@Autowired
    private SqlSession sqlSession;


	@Override
	public ArrayList<ReviewDTO> selectReview(HashMap<String, String>param)  
	{
		ReviewDAO dao = sqlSession.getMapper(ReviewDAO.class);
		ArrayList<ReviewDTO> dto = dao.selectReview(param);
		
		return dto;
	}
	
	@Override 
	public ArrayList<ReviewDTO> selectReviewPaging(ReviewDTO reviewdto) 
	{
		ReviewDAO dao = sqlSession.getMapper(ReviewDAO.class);
		ArrayList<ReviewDTO> dto = dao.selectReviewPaging(reviewdto);
		
		return dto;
	}

	@Override
	public void insertReview(HashMap<String, String>param) 
	{
		ReviewDAO dao = sqlSession.getMapper(ReviewDAO.class);
		dao.insertReview(param);
	}

	@Override
	public void updateReview(HashMap<String, String>param) 
	{
		ReviewDAO dao = sqlSession.getMapper(ReviewDAO.class);
		dao.updateReview(param);
	}

	@Override
	public void deleteReview(HashMap<String, String> param) 
	{
		ReviewDAO dao = sqlSession.getMapper(ReviewDAO.class);
		dao.deleteReview(param);		
	}

	@Override
	public int findMovieno(HashMap<String, String> param) 
	{
		ReviewDAO dao = sqlSession.getMapper(ReviewDAO.class);
		return dao.findMovieno(param);		
	}

	@Override
//	public int countReview(HashMap<String, String> param) 
	public int countReview(ReviewDTO reviewdto) 
	{
		ReviewDAO dao = sqlSession.getMapper(ReviewDAO.class);
//		return dao.countReview(param);
		return dao.countReview(reviewdto);
	}

	@Override
	public Integer countstar(HashMap<String, String> param) 
	{
		ReviewDAO dao = sqlSession.getMapper(ReviewDAO.class);
		return dao.countstar(param);
	}


	
}







