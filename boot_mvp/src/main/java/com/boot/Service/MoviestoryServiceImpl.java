package com.boot.Service;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.boot.DAO.MovieStoryDAO;
import com.boot.DTO.MovietbDTO;
import com.boot.DTO.ReviewDTO;
import com.boot.DTO.WatchedMovieDTO;
import com.boot.Security.CustomUserDetails;
import com.boot.Security.CustomUserDetailsService;

import lombok.extern.slf4j.Slf4j;

@Service("MoviestoryService")
@Slf4j
public class MoviestoryServiceImpl implements MoviestoryService {
	@Autowired
	private SqlSession sqlSession;
	

	@Autowired
	private CustomUserDetailsService userService;
	
	
	@Override
	public List<WatchedMovieDTO> getWatchedMovies(int pageSize, int offset) {
//		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String uuid = userService.getUuidFromAuthenticatedUser();  // 사용자 UUID 가져오기

	    try {
	    	MovieStoryDAO dao = sqlSession.getMapper(MovieStoryDAO.class);
	    	return dao.getWatchedMovies(uuid,pageSize,offset); 
	    } catch (Exception e) {
	        log.error("예외 발생: ", e);
	        throw new RuntimeException("목록 조회 중 문제가 발생했습니다.", e);
	    }
	}

	@Override
	public int getTotalCountWatchedMovies() {
//		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String uuid = userService.getUuidFromAuthenticatedUser();  // 사용자 UUID 가져오기
    	
	    try {
	    	MovieStoryDAO dao = sqlSession.getMapper(MovieStoryDAO.class);
	    	return dao.getTotalCountWatchedMovies(uuid); 
	    } catch (Exception e) {
	        log.error("예외 발생: ", e);
	        throw new RuntimeException("목록 조회 중 문제가 발생했습니다.", e);
	    }
	}

	@Override
	public List<MovietbDTO> getlikedMovies(int pageSize, int offset) {
//		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String uuid = userService.getUuidFromAuthenticatedUser();  // 사용자 UUID 가져오기

	    try {
	    	MovieStoryDAO dao = sqlSession.getMapper(MovieStoryDAO.class);
	    	return dao.getlikedMovies(uuid,pageSize,offset); 
	    } catch (Exception e) {
	        log.error("예외 발생: ", e);
	        throw new RuntimeException("목록 조회 중 문제가 발생했습니다.", e);
	    }
	}

	@Override
	public int getTotalCountlikedMovies() {
//		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String uuid = userService.getUuidFromAuthenticatedUser();  // 사용자 UUID 가져오기

	    try {
	    	MovieStoryDAO dao = sqlSession.getMapper(MovieStoryDAO.class);
	    	return dao.getTotalCountlikedMovies(uuid); 
	    } catch (Exception e) {
	        log.error("예외 발생: ", e);
	        throw new RuntimeException("목록 조회 중 문제가 발생했습니다.", e);
	    }
	}

	@Override
	public List<ReviewDTO> getUserReviews(int pageSize, int offset) {
//		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String uuid = userService.getUuidFromAuthenticatedUser();  // 사용자 UUID 가져오기

	    try {
	    	MovieStoryDAO dao = sqlSession.getMapper(MovieStoryDAO.class);
	    	return dao.getUserReviews(uuid,pageSize,offset); 
	    } catch (Exception e) {
	        log.error("예외 발생: ", e);
	        throw new RuntimeException("목록 조회 중 문제가 발생했습니다.", e);
	    }
	}

	@Override
	public int getTotalCountUserReviews() {
//		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String uuid = userService.getUuidFromAuthenticatedUser();  // 사용자 UUID 가져오기

	    try {
	    	MovieStoryDAO dao = sqlSession.getMapper(MovieStoryDAO.class);
	    	return dao.getTotalCountUserReviews(uuid); 
	    } catch (Exception e) {
	        log.error("예외 발생: ", e);
	        throw new RuntimeException("목록 조회 중 문제가 발생했습니다.", e);
	    }
	}


	
	


	
	
}
