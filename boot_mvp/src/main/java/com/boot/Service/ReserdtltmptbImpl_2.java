package com.boot.Service;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.DAO.ReserdtltmptbDAO_2;

import lombok.extern.slf4j.Slf4j;

@Service("ReserdtltmptbService_2")
@Slf4j
public class ReserdtltmptbImpl_2 implements ReserdtltmptbService_2 {
	
	@Autowired
	private SqlSession sqlSession;
	
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	@Override
	public int getCnt(HashMap<String, String> param) {
		ReserdtltmptbDAO_2 dao = sqlSession.getMapper(ReserdtltmptbDAO_2.class);
		return dao.getCnt(param);
	}

	@Override
	public void inserttmp(HashMap<String, String> param) {
		ReserdtltmptbDAO_2 dao = sqlSession.getMapper(ReserdtltmptbDAO_2.class);
		dao.inserttmp(param);
		
		//10분 해당 임시 데이터 삭제
		scheduler.schedule(() -> deletetmp(param), 10, TimeUnit.MINUTES);
	}

	@Override
	public void deletetmp(HashMap<String, String> param) {
		log.info("@# deletetmp");
		ReserdtltmptbDAO_2 dao = sqlSession.getMapper(ReserdtltmptbDAO_2.class);
		dao.deletetmp(param);
	}
}