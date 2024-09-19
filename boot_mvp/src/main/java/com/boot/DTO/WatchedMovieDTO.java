package com.boot.DTO;

import java.util.Date;

import lombok.Data;

@Data
public class WatchedMovieDTO {
	private String movieno;         // 영화 번호
    private String areano;       // 지역 번호
    private String theaterno;    // 상영관 번호
    private String theaternm;    // 상영관 이름
    private Date viewday; 		 // 관람일
    private String roomno;       // 관번호
    private int tmember;         // 총 인원
    private String review;       // 리뷰
    private int star;            // 별점
    private String moviepostimg; // 영화 포스터 url
    private String movienm;      // 영화 이름
}