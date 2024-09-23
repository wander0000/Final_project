package com.boot.Service;

import java.util.List;

import com.boot.DTO.MovietbDTO;
import com.boot.DTO.ReviewDTO;
import com.boot.DTO.WatchedMovieDTO;

public interface MoviestoryService {
	// 본영화+관람편 조회
	public List<WatchedMovieDTO> getWatchedMovies(int pageSize,int offset);
	// 본영화 영화 갯수 조회
	public int getTotalCountWatchedMovies();
	// 좋아요한 영화 목록 조회
	public List<MovietbDTO> getlikedMovies(int pageSize,int offset);
	// 좋아요한 영화 갯수 조회
	public int getTotalCountlikedMovies();
	// 관람편 목록 조회
	public List<ReviewDTO> getUserReviews(int pageSize,int offset);
	// 관람편 갯수 조회
	public int getTotalCountUserReviews();

}
