package com.boot.Service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.DAO.ReserdtltbDAO_2;
import com.boot.DTO.ReserdtltbDTO;

import lombok.extern.slf4j.Slf4j;

@Service("ReserdtltbService_2")
@Slf4j
public class ReserdtltbServiceImpl_2 implements ReserdtltbService_2 {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insertReserdtl(HashMap<String, String> param) {
		ReserdtltbDAO_2 dao = sqlSession.getMapper(ReserdtltbDAO_2.class);
		dao.insertReserdtl(param);
	}

	@Override
	public int selected_count(HashMap<String, String> param) {
		ReserdtltbDAO_2 dao = sqlSession.getMapper(ReserdtltbDAO_2.class);
		return dao.selected_count(param);
	}

	@Override
	public ArrayList<ReserdtltbDTO> selected_seat(HashMap<String, String> param) {
		ReserdtltbDAO_2 dao = sqlSession.getMapper(ReserdtltbDAO_2.class);
		return dao.selected_seat(param);
	}
}