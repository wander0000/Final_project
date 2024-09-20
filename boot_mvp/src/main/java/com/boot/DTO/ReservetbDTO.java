package com.boot.DTO;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class ReservetbDTO {
	private String uuid;
	private String reservenum;
	private int areano;
	private int theaterno;
	private String movieno;
	private Date viewday;
	private int roomno;
	private Timestamp starttime;
	private int tmember;
	private int tprice;
	private Timestamp adate;
	private Timestamp mdate;
	private String movienm;
	private String status;
	private String fldac;
	private String fldad;
	private int fldna;
	private int fldnb;
	private int fldnc;
	private int fldnd;
	private String theaternm; //상영관이름 추가
}
