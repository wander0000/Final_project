package com.boot.DAO;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReserdtltmptbDAO_2 {
	public int getCnt(HashMap<String, String> param); //uuid 기준 (count + 1) 가져오기
	public void inserttmp(HashMap<String, String> param); //선택 좌석 임시 테이블에 등록
	public void deletetmp(HashMap<String, String> param); //선택 좌석 임시 테이블에서 삭제
	public int getidno(HashMap<String, String> param);
	public int getCount(HashMap<String, String> param); //선택 좌석 임시 테이블에 있는지 확인
	public int getCountUUID(HashMap<String, String> param); //해당 계정으로 선택한 임시 좌석이 있는지 확인
	public void deletetmpAll(HashMap<String, String> param); //uuid 기준 임시좌석 테이블 삭제
}
