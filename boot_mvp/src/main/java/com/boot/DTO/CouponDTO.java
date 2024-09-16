package com.boot.DTO;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponDTO {
	private String couponno; //쿠폰번호
	private String acrec; //사용유무 A:사용가능 D:사용완료 N:미등록 
	private Timestamp endDate; //
	private String reason; //지급사유
	private int refno; //쿠폰타입(쿠폰 레퍼런스테이블에서 가져오는 FK)
	private String uuid; //사용자 uuid
	private Timestamp adate; //등록일자
	private Timestamp mdate; //수정일자
	private String type; //쿠폰 or 할인권
	private String name; //쿠폰 이름(주중관람권, 10% 할인권)
	private String benefit; //해택(주중 상영 영화 관람 ..) 
	private int period; //유효기간
	
}