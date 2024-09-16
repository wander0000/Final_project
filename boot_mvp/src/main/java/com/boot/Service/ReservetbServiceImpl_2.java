package com.boot.Service;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.DAO.ReservetbDAO_2;

import lombok.extern.slf4j.Slf4j;

@Service("ReservetbService_2")
@Slf4j
public class ReservetbServiceImpl_2 implements ReservetbService_2 {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertReserve(HashMap<String, String> param) {
		ReservetbDAO_2 dao = sqlSession.getMapper(ReservetbDAO_2.class);
		dao.insertReserve(param);
	}
}
