package com.boot.DTO;

import java.security.Timestamp;
import java.sql.Date;

import lombok.Data;

@Data
public class ReserdtltbDTO {
	private String uuid;
	private String reservenum;
	private int reserveno;
	private int areano;
	private int theaterno;
	private String movieno;
	private Date viewday;
	private int roomno;
	private Timestamp starttime;
	private int priceno;
	private int price;
	private String seat;
	private Timestamp adate;
	private Timestamp mdate;
	private String fldaa;
	private String fldab;
	private String fldac;
	private String fldad;
	private int fldna;
	private int fldnb;
	private int fldnc;
	private int fldnd;
}
