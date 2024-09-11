package com.boot.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoxOfficeDTO {
	private String movieno; // 영화번호
	private String movienm; // 영화이름
	private String directornm;  // 감독이름
	private Date releaseday; // 개봉일
	private String openday; // 개봉일
	
	private String ratingno; // 관람등급	
	private int times; // 상영시간	
	private String intro; // 영화소개	
	private String moviebackimg; // 영화 큰이미지 url
	private String moviepostimg; // 영화 포스터 url
	private String movieyoutube; // 예고편 youtube url
	private String steelcut; // 예고편 youtube url
	private String genre; // 장르
	private String actor; // 배우
	private String salesAcc; // 누적 매출액
	private int audiAcc; // 누적관객수
	private int ranking; // 순위
	private String status; // 개봉상태
	private String typeNm; // 영화유형(ex 장편)
	private String showType; // 2D, 4D 이런타입	
}



  
 

