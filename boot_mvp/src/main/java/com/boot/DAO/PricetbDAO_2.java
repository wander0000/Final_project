package com.boot.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PricetbDAO_2 {
	public ArrayList<Integer> selectprice(HashMap<String, String> param); //해당 영화의 대한 가격표
}