package com.boot.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ReviewCriteria {
	private int pageNum; // 페이지번호
	private int amount; // 페이지당 글 갯수	
    private String uuid;  // 중복 확인 후 가입 진행
    private String movieno; // 영화번호
    private int star; // 별점
    private String review; // 리뷰
    
    private String userid; // 조인으로 가져올 아이디
    
    public ReviewCriteria() 
    {
    	this(1, 10);
    }
	public ReviewCriteria(int pageNum, int amount) 
	{
		this.pageNum = pageNum;
		this.amount = amount;
	}    
}