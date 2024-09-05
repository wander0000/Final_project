package com.boot.Service;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.DAO.UsertbDAO_4;

import lombok.extern.slf4j.Slf4j;

@Service("UserService_4")
@Slf4j
public class UserImpl_4 implements UserService_4 {
	@Autowired
	private SqlSession sqlSession;


	@Override
//	@Transactional  // 트랜잭션이 적용된 메서드
	public void updateEmail(String email, String userid) {
		log.info("updateEmail 서비스임플");
		log.info("updateEmail email========>"+email);
		UsertbDAO_4 dao = sqlSession.getMapper(UsertbDAO_4.class);
		
		dao.updateEmail(email, userid);
	}

	
	
}
