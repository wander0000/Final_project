package com.boot.Service;

import java.util.List;

public interface AttendenceService {
	// 출석 체크(DB정보저장, 포인트 발급), 반환값은 팝업띄울지 말지 결정
	public boolean checkAttendance();
	//한달 출석 만근자 확인
	public List<String> checkMonthlyAttendance();
}