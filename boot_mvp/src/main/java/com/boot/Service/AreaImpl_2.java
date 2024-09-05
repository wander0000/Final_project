package com.boot.Service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.DAO.AreatbDAO_2;
import com.boot.DTO.AreatbDTO;

import lombok.extern.slf4j.Slf4j;

@Service("AreaService_2")
@Slf4j
public class AreaImpl_2 implements AreaService_2 {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public ArrayList<AreatbDTO> selectAll() {
		log.info("area_selectAll");
		AreatbDAO_2 dao = sqlSession.getMapper(AreatbDAO_2.class);

		return dao.selectAll();
	}

	@Override
	public ArrayList<AreatbDTO> datedual(String dates) {
		log.info("datedual");
		AreatbDAO_2 dao = sqlSession.getMapper(AreatbDAO_2.class);
		return dao.datedual(dates);
	}
}
