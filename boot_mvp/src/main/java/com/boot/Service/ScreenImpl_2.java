package com.boot.Service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.DAO.ScreentbDAO_2;
import com.boot.DTO.ScreentbDTO;

import lombok.extern.slf4j.Slf4j;

@Service("ScreenService_2")
@Slf4j
public class ScreenImpl_2 implements ScreenService_2 {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public ArrayList<ScreentbDTO> selecthdr(HashMap<String, String> param) {
		ScreentbDAO_2 dao = sqlSession.getMapper(ScreentbDAO_2.class);
		return dao.selecthdr(param);
	}

	@Override
	public ArrayList<ScreentbDTO> selecthdrbase() {
		ScreentbDAO_2 dao = sqlSession.getMapper(ScreentbDAO_2.class);
		return dao.selecthdrbase();
	}

	@Override
	public ArrayList<ScreentbDTO> selectdtl(HashMap<String, String> param) {
		ScreentbDAO_2 dao = sqlSession.getMapper(ScreentbDAO_2.class);
		return dao.selectdtl(param);
	}

	@Override
	public ScreentbDTO selectmovieinfo(HashMap<String, String> param) {
		ScreentbDAO_2 dao = sqlSession.getMapper(ScreentbDAO_2.class);
		return dao.selectmovieinfo(param);
	}
}
