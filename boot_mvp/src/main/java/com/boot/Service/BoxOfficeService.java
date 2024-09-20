package com.boot.Service;

import java.util.ArrayList;

import com.boot.DTO.BoxOfficeDTO;

public interface BoxOfficeService {
	
	// boxOffice 영화 데이터 삭제
    void deleteBoxOffice();	
    
    // boxOffice 영화 데이터 받아서 입력
    void insertBoxOffice();
    
    // boxOffice 영화 출력
    public ArrayList<BoxOfficeDTO> BoxOfficeList();    
	
}
