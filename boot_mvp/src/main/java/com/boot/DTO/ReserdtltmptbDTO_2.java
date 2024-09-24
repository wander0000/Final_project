package com.boot.DTO;

import java.security.Timestamp;
import java.sql.Date;

import lombok.Data;

@Data
public class ReserdtltmptbDTO_2 {
	private String uuid;
	private int idno;
	private int reserveno;
	private int areano;
	private int theaterno;
	private String movieno;
	private Date viewday;
	private int roomno;
	private Timestamp starttime;
	private String seat;
	private Timestamp adate;
}