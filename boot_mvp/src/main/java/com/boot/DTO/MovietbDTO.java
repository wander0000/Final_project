package com.boot.DTO;

import java.sql.Date;

import lombok.Data;

@Data
public class MovietbDTO {
	private String movieno;
	private String movienm;
	private String movieen;
	private String directorno;
	private String directornm;
	private Date releaseday;
	private Date endday;
	private int moviestatus;
	private String ratingno;
	private int times;
	private String intro;
	private int popularity;
	private String moviebackimg;
	private String moviepostimg;
	private String movieyoutube;
	private String steelcut;
	
	//add columns
	private int areano;
}