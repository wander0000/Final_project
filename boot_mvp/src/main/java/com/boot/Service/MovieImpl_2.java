package com.boot.Service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.DAO.MovietbDAO_2;
import com.boot.DTO.MovietbDTO;

import lombok.extern.slf4j.Slf4j;

@Service("movieService_2")
@Slf4j
public class MovieImpl_2 implements MovieService_2 {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public ArrayList<MovietbDTO> selectAll() {
		MovietbDAO_2 dao = sqlSession.getMapper(MovietbDAO_2.class);
		
		return dao.selectAll();
	}

	@Override
	public MovietbDTO getTitleRating(HashMap<String, String> param) {
		MovietbDAO_2 dao = sqlSession.getMapper(MovietbDAO_2.class);
		return dao.getTitleRating(param);
	}
}