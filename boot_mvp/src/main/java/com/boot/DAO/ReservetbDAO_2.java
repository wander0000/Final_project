package com.boot.DAO;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservetbDAO_2 {
	public void insertReserve(HashMap<String, String> param);
}
