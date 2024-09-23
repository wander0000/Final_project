package com.boot.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.boot.DTO.BoxOfficeDTO;
import com.boot.DTO.GenreDTO;
import com.boot.DTO.MovietbDTO;
import com.boot.DTO.SelecGenretbDTO;

//실행시 매퍼파일을 읽어 들이도록 지정
@Mapper
public interface MovieDAO { 	

	// TMDB 영화 데이터 받아서 입력
    void deleteMovie();
    
	// boxOffice 영화 데이터 받아서 입력
    void deleteBoxOffice();
	
	// TMDB 영화 데이터 받아서 입력
    void insertMovie(List<MovietbDTO> movieList);
    
    // boxOffice 영화 데이터 받아서 입력
    void insertBoxOffice(List<BoxOfficeDTO> boxOfficeList);
	
    // 추가: 영화 ID로 존재 여부를 확인하는 메서드
    boolean existsById(String movieId);
    
    // boxOffice 영화 출력
    public ArrayList<BoxOfficeDTO> BoxOfficeList();
    
    // TMDB 영화 출력
    public ArrayList<MovietbDTO> MovieList();
    
    // 현재 상영 영화
    public ArrayList<MovietbDTO> MoviePlayingList();
    
    // 상영 예정작
    public ArrayList<MovietbDTO> MovieUpcomingList();
    
    // 무비 장르 검색
    public ArrayList<MovietbDTO> GenreList(@Param("genre") String genre);
    
    // 무비 장르
    public List<GenreDTO> Genre();
    
    // 영화상세정보
    public MovietbDTO movieInfo(@Param("movieno") String movieno);
    
    // 유저 영화 장르찾기
    public ArrayList<SelecGenretbDTO> selectUserGenre(HashMap<String, Object> param);
    
    // 유저 영화 장르찾기
    public ArrayList<BoxOfficeDTO> selectMoviesByGenres(HashMap<String, Object> param);

}
















