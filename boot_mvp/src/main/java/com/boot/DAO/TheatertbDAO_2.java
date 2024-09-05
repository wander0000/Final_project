package com.boot.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.boot.DTO.TheatertbDTO;

@Mapper
public interface TheatertbDAO_2 {
	public ArrayList<TheatertbDTO> theater(HashMap<String, String> param);
}
