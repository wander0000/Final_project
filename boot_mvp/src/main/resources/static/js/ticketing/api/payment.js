function getCsrfToken() {
    return $("#csrf").val();
}

function creatNum() {
	// 현재 날짜와 시간 객체 생성
	var now = new Date();
	
	// 날짜 및 시간 포맷팅
	var formatter1 = (date) => {
	    var month = date.getMonth() + 1; // 월 (0-11), 1을 더해줌
	    var day = date.getDate(); // 일 (1-31)
	    return `${month.toString().padStart(2, '0')}${day.toString().padStart(2, '0')}`;
	};
	
	var formatter2 = (date) => {
	    var hours = date.getHours(); // 시 (0-23)
	    var minutes = date.getMinutes(); // 분 (0-59)
	    return `${hours.toString().padStart(2, '0')}${minutes.toString().padStart(2, '0')}`;
	};
	
	var reservenum_1 = formatter1(now); // MMdd 포맷
	var reservenum_2 = formatter2(now); // HHmm 포맷
	
	// 밀리초 단위의 현재 시간
	var millis = Date.now(); // 현재 시간의 밀리초
	var millisStr = millis.toString(); // 밀리초를 문자열로 변환
	var num_3 = millisStr.slice(-4); // 문자열의 마지막 4자리 추출
	
	// 임의의 숫자를 생성
	var reservenum_4 = parseInt(reservenum_1) + parseInt(reservenum_2) + millis;
	var num_4 = reservenum_4.toString().slice(-3); // 문자열의 마지막 3자리 추출
	
	// 예매 번호 생성
	var reservenum = `${reservenum_1}-${reservenum_2}-${num_3}-${num_4}`;
	
	return reservenum; 
}

function kakaopay() {
	//console.log(reservenum);
	var reservenum = creatNum();
	var couponno = $("#couponno").val();
	var t_calc = $("#t_calc").val();
	var t_point = $("#t_point").val();
	
	var IMP = window.IMP;
	IMP.init('imp81407155'); // 포트원 계정 상점 고유 ID
	IMP.request_pay({		
		pg : 'kakaopay.TC0ONETIME',
		pay_method : 'card',
		merchant_uid : 'movie_ticket_' + reservenum,   //주문번호
		name : 'MVP',                                  //상품명
		amount : $('#t_calc').val(),                    //가격
		//customer_uid : buyer_name + new Date().getTime(),  //해당 파라미터값이 있어야 빌링 키 발급 시도
		buyer_email : 'test123@naver.com',             //구매자 이메일
		buyer_name : 'KW',                           //구매자 이름
		buyer_tel : 'hp',                                    //전화번호
		buyer_addr : 'addr',	                             //주소
		buyer_postcode : '123-456'                           //우편번호 
	},function(rsp) {
		alert('rsp: ' + JSON.stringify(rsp)); 
		if(rsp.success) {
			var msg = "결제 완료";
            msg += '고유ID : ' + rsp.imp_uid;                //아임포트 uid는 실제 결제 시 결제 고유번호를 서버와 비교해서 결제처리하는데 필요없긴함.
            msg += '// 상점 거래ID : ' + rsp.merchant_uid;
            msg += '// 결제 금액 : ' + rsp.paid_amount;
            msg += '// 카드 승인번호 : ' + rsp.apply_num;
            
			if(rsp.success) {
	            $.ajax({
	            	type : 'post',
	            	url : '/ticketing/reserve',
	            	data : { "reservenum" : reservenum, "couponno": couponno, "t_calc": t_calc, "t_point": t_point },
					headers: {
						'X-CSRF-TOKEN': getCsrfToken() // CSRF 토큰 추가
					}, success: function() {
						document.location.href="/ticketing/paycompleted";
					}, errer: function() {
						console.log('Ajax 에러...');
					}
	            });
			}
        }else{
        	var msg = "결제 실패"
        	msg += "에러 내용" + rsp.error_msg;
        }
	});
}

function tosspay(){
	var reservenum = creatNum();
	var couponno = $("#couponno").val();
	var t_calc = $("#t_calc").val();
	var t_point = $("#t_point").val();
	
	var IMP = window.IMP;
	IMP.init('imp81407155');
	IMP.request_pay({		
		pg : 'tosspay_v2.tosstest', //pg : 'uplus.tlgdacomxpay',
		pay_method : 'tosspay',
		merchant_uid : 'movie_ticket_' +reservenum,   //주문번호
		name : 'MVP',                                  //상품명
		amount : $('#t_calc').val(),                 //가격
		//m_redirect_url: "/ticketing/paycompleted", //모바일에서 결제 완료 후 리다이렉트될 경로
		//customer_uid : buyer_name + new Date().getTime(),  //해당 파라미터값이 있어야 빌링 키 발급 시도
		buyer_email : 'test123@naver.com',             //구매자 이메일
		buyer_name : 'KW',                           //구매자 이름
		buyer_tel : 'hp',                                    //전화번호
		buyer_addr : 'addr',	                             //주소
		buyer_postcode : '123-456'                           //우편번호 
	}, function(rsp){
		//alert('rsp: ' + JSON.stringify(rsp));
		if(rsp.imp_uid != '') {
			var msg = "결제 완료";
            msg += '고유ID : ' + rsp.imp_uid;                //아임포트 uid는 실제 결제 시 결제 고유번호를 서버와 비교해서 결제처리하는데 필요없긴함.
            msg += '// 상점 거래ID : ' + rsp.merchant_uid;
            msg += '// 결제 금액 : ' + rsp.paid_amount;
            msg += '// 카드 승인번호 : ' + rsp.apply_num;
			
			if(!rsp.error_code) {
	            $.ajax({
	            	type : 'post',
	            	url : '/ticketing/reserve',
	            	data : { "reservenum" : reservenum, "couponno": couponno, "t_calc": t_calc, "t_point": t_point },
					headers: {
						'X-CSRF-TOKEN': getCsrfToken() // CSRF 토큰 추가
					}, success: function() {
						document.location.href="/ticketing/paycompleted";					
					}, errer: function() {
						console.log('Ajax 에러...');	
					}
	            });
			}
        }else{
        	var msg = "결제 실패"
        	msg += "에러 내용" + rsp.error_msg;
        }
		//console.log('msg: ' + msg);
	});
}

function cardpay(){
	var reservenum = creatNum();
	var couponno = $("#couponno").val();
	var t_calc = $("#t_calc").val();
	var t_point = $("#t_point").val();
	
	var IMP = window.IMP;
	IMP.init('imp81407155');
	IMP.request_pay({		
		pg : 'html5_inicis.INIpayTest', //pg : 'uplus.tlgdacomxpay',
		pay_method : 'inipay',
		merchant_uid : 'movie_ticket_' +reservenum,   //주문번호
		name : 'MVP',                                  //상품명
		amount : $('#t_calc').val(),                 //가격
		//m_redirect_url: "/ticketing/paycompleted", //모바일에서 결제 완료 후 리다이렉트될 경로
		//customer_uid : buyer_name + new Date().getTime(),  //해당 파라미터값이 있어야 빌링 키 발급 시도
		buyer_email : 'test123@naver.com',             //구매자 이메일
		buyer_name : 'KW',                           //구매자 이름
		buyer_tel : 'hp',                                    //전화번호
		buyer_addr : 'addr',	                             //주소
		buyer_postcode : '123-456'                           //우편번호 
	}, function(rsp){
		//alert('rsp: ' + JSON.stringify(rsp));
		if(rsp.imp_uid != '' ) {
			var msg = "결제 완료";
            msg += '고유ID : ' + rsp.imp_uid;                //아임포트 uid는 실제 결제 시 결제 고유번호를 서버와 비교해서 결제처리하는데 필요없긴함.
            msg += '// 상점 거래ID : ' + rsp.merchant_uid;
            msg += '// 결제 금액 : ' + rsp.paid_amount;
            msg += '// 카드 승인번호 : ' + rsp.apply_num;

			if(rsp.success) {
	            $.ajax({
	            	type : 'post',
	            	url : '/ticketing/reserve',
	            	data : { "reservenum" : reservenum, "couponno": couponno, "t_calc": t_calc, "t_point": t_point },
					headers: {
						'X-CSRF-TOKEN': getCsrfToken() // CSRF 토큰 추가
					}, success: function() {
						document.location.href="/ticketing/paycompleted";
					}, error: function() {
						console.log('Ajax 에러...');
					}
	            });
			}
        }else{
        	var msg = "결제 실패"
        	msg += "에러 내용" + rsp.error_msg;
        }
		//console.log('msg: ' + msg);
	});
}


/*
참고 코드
function kakaopay(){
	if(inputH.value == null || inputH.value == 'none' || inputH.value == ""){
		alert("결제 개월 수를 선택 해 주세요");
		return;
	}
	
	console.log($('#userid'));
	var IMP = window.IMP;
	IMP.init('imp81407155');
	IMP.request_pay({		
		pg : 'kakaopay.TC0ONETIME',
		pay_method : 'card',
		merchant_uid : 'merchant_' + new Date().getTime(),   //주문번호
		name : 'GOOTTFLEX',                                  //상품명
		amount : $('.amountValue').val(),                    //가격
		//customer_uid : buyer_name + new Date().getTime(),  //해당 파라미터값이 있어야 빌링 키 발급 시도
		buyer_email : $('.sessionuserID').val(),             //구매자 이메일
		buyer_name : 'KW',                           //구매자 이름
		buyer_tel : 'hp',                                    //전화번호
		buyer_addr : 'addr',	                             //주소
		buyer_postcode : '123-456'                           //우편번호 
	},function(data){
		if(data.success){
			var msg = "결제 완료";
            msg += '고유ID : ' + data.imp_uid;                //아임포트 uid는 실제 결제 시 결제 고유번호를 서버와 비교해서 결제처리하는데 필요없긴함.
            msg += '// 상점 거래ID : ' + data.merchant_uid;
            msg += '// 결제 금액 : ' + data.paid_amount;
            msg += '// 카드 승인번호 : ' + data.apply_num;
            
            $.ajax({
            	type : 'post',
            	url : '/paySuccess',
            	data : {"ID" : data.buyer_email, "amount" : data.paid_amount},
            });
        }else{
        	var msg = "결제 실패"
        	msg += "에러 내용" + rsp.error_msg;
        }
		alert(msg);
		document.location.href="/video/list";
	});
}
*/
