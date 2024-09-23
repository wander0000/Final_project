package com.boot.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AttendanceDAO {
	//유저의 오늘 출석 기록 확인
	public int checkTodayAttendance(@Param("uuid") String uuid); 
	// 출석 기록 추가
	public void insertAttendance(@Param("uuid") String uuid); 
	// 출석 만근자 uuid 조회
	public List<String> checkMonthlyAttendance();
	//유저개인의 출석현황 
	public List<Integer> checkUserAttendance(@Param("uuid") String uuid);
}