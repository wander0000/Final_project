package com.boot.Service;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PreDestroy;

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
	
	private boolean deldata = false;
	
	@Override
	public int getCnt(HashMap<String, String> param) {
		ReserdtltmptbDAO_2 dao = sqlSession.getMapper(ReserdtltmptbDAO_2.class);
		return dao.getCnt(param);
	}

	@Override
	public void inserttmp(HashMap<String, String> param) {
		ReserdtltmptbDAO_2 dao = sqlSession.getMapper(ReserdtltmptbDAO_2.class);
		dao.inserttmp(param);
	}

	@Override
	public void deletetmp(HashMap<String, String> param) {
		log.info("@# deletetmp");
		ReserdtltmptbDAO_2 dao = sqlSession.getMapper(ReserdtltmptbDAO_2.class);
		log.info("@# deletetmp param: " + param);
		dao.deletetmp(param); //최근에 생성된 임시 좌석 선택 데이터 삭제 
		
		if(dao.getCount(param) == 0) {//최종적으로 생성된 데이터까지 모두 삭제 되는 순간
			deldata = true;
		}
	}
	
	public boolean checkDelStatus() {
        return deldata;
    }

    public void resetDelStatus() {
    	deldata = false;
    }
	/*
	@PreDestroy
	public void shutdown() {
	    scheduler.shutdown();
	}
	*/

	@Override
	public void deletetmpAll(HashMap<String, String> param) {
		log.info("@# deletetmpAll");
		ReserdtltmptbDAO_2 dao = sqlSession.getMapper(ReserdtltmptbDAO_2.class);
		dao.deletetmpAll(param);
	}
}