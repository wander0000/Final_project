package com.boot.DTO;

import java.security.Timestamp;

import lombok.Data;

@Data
public class PricetbDTO {
	private int pricetype;
	private String typenm;
	private String pricenm;
	private int nowprice;
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
