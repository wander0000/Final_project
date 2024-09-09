package com.boot.Service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service("ReservetbService_2")
@Slf4j
public class ReservetbServiceImpl_2 implements ReservetbService_2 {
	
	@Autowired
	private SqlSession sqlSession;
}
