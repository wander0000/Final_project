package com.boot.Service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.DAO.Movieinfotb_vDAO_2;
import com.boot.DAO.MovietbDAO_2;
import com.boot.DTO.Movieinfotb_vDTO;

import lombok.extern.slf4j.Slf4j;

@Service("Movieinfotb_vService_2")
@Slf4j
public class Movieinfotb_vImpl_2 implements Movieinfotb_vService_2 {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public ArrayList<Movieinfotb_vDTO> selectAll() {
		Movieinfotb_vDAO_2 dao = sqlSession.getMapper(Movieinfotb_vDAO_2.class);
		return dao.selectAll();
	}

	@Override
	public Movieinfotb_vDTO getTitleRating(HashMap<String, String> param) {
		Movieinfotb_vDAO_2 dao = sqlSession.getMapper(Movieinfotb_vDAO_2.class);
		return dao.getTitleRating(param);
	}
}