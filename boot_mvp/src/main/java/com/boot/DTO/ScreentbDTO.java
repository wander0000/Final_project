package com.boot.DTO;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class ScreentbDTO {
	private int areano;
	private int theaterno;
	private String movieno;
	private Date viewday;	
	private int roomno;
	private Timestamp starttime;
	private Timestamp endtime;
	private int pricetype;
	private int selecseat;
	private int totalseat;
	private int adate;
	private int mdate;
	private int fldaa;
	private int fldab;
	private int fldac;
	private int fldad;
	private int fldna;
	private int fldnb;
	private int fldnc;
	private int fldnd;
	
	//add columns
	private String movienm;
	private String theaternm;
	private String ratingno;
	private String weekday;
	private String moviepostimg;
	private int nowprice;
}
