package com.boot.Service;

import java.util.ArrayList;
import java.util.List;

import com.boot.DTO.GenreDTO;
import com.boot.DTO.MovietbDTO;

public interface MovieService {
	
	// TMDB 영화 데이터 받아서 입력	
	void insertMovie();
    
	// TMDB 영화 출력
    public ArrayList<MovietbDTO> MovieList();    
    
    // 현재 상영 영화
    public ArrayList<MovietbDTO> MoviePlayingList();
    
    // 상영 예정작
    public ArrayList<MovietbDTO> MovieUpcomingList();
    
    // 무비 장르 검색
    public ArrayList<MovietbDTO> GenreList(String genre);
    
    // 무비 장르
    public List<GenreDTO> Genre();
    
    // 영화상세정보
    public MovietbDTO movieInfo(String movieno);
}
