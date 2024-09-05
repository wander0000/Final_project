package com.boot.DAO;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.DTO.AreatbDTO;

@Mapper
public interface AreatbDAO_2 {
	public ArrayList<AreatbDTO> selectAll(); //지역 조회
	public ArrayList<AreatbDTO> datedual(String dates); // 오늘부터 이번달 말일까지
}
