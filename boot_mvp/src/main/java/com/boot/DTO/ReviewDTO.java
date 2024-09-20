package com.boot.DTO;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private String uuid;  // 중복 확인 후 가입 진행
    private String uuid2;  // 중복 확인 후 가입 진행
    private String movieno; // 영화번호
    private int star; // 별점
    private String review; // 리뷰
    
    private String userid; // 조인으로 가져올 아이디
    private String movienm; // 조인으로 가져올 영화명
    private Date viewday; // 조인으로 가져올 관람일
    
    private int limit;
    private int page;
    private int startpage;
    private int endpage;
    private int maxpage;
    private int offset;
}