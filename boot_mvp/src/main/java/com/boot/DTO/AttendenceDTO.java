package com.boot.DTO;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendenceDTO {
	private int id; //PK
	private String uuid ; //사용자 uuid
	private Date adate; //출석날짜(시간포함안함)
	private int points ; //지급포인트
	
}