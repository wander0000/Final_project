package com.boot.Service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.DAO.TheatertbDAO_2;
import com.boot.DTO.TheatertbDTO;

import lombok.extern.slf4j.Slf4j;

@Service("TheaterService_2")
@Slf4j
public class TheaterImpl_2 implements TheaterService_2 {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public ArrayList<TheatertbDTO> theater(HashMap<String, String> param) {
		TheatertbDAO_2 dao = sqlSession.getMapper(TheatertbDAO_2.class);
		
		return dao.theater(param);
	}
	
}
