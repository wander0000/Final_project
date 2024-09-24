function getCsrfToken() {
    return $("#csrf").val();
}

function dis_pay_button() {
	alert('결제 방법을 선택하세요.');
	return false;
}

function cancel_coupon(gubun, ids) {
	var btn = $("#"+gubun+"_cancel");
	
	$(btn).removeClass("showbtn");
	$("#"+ids).val('');
	
	$("#couponno").val('none');
	$("#t_"+ids).val('-0');
	
	$("#refno").val(0);
	$("#d_pay").val('- 0');
	
	c_calc_point();
}

function cancel_point(){
	var btn = $("#P_cancel");
	
	$(btn).removeClass("showbtn");
	
	$("#t_point").val('0');
	$("#up_pay").val('0');
	$("#point").val('');
	
	show_pay();
}

function switchbutton(page) { //결제 방법에 따른 결제 버튼 변환
	$.ajax({
		type: 'post',
		url: page+"_ajax",
		headers: {
			'X-CSRF-TOKEN': getCsrfToken() // CSRF 토큰 추가
		},
		success: function(response) {
			//console.log("서버에서 반환된 HTML:", response);
			//show_pay();
			
			$("#paybutton").html(response);
		}, error: function(xhr, status, error) {
            // 오류가 발생했을 때 처리할 내용
            console.error("AJAX 요청 실패:", xhr.status, status, error);
        }
	});
}

//쿠폰 및 할인권 계산
function show_pay() {
	var coupon = $("#coupon").val();
	var discount = $("#discount").val();
	var refno = Number($("#refno").val());
	
	$("#t_coupon").val(coupon != '' ? coupon: '-0');
	$("#t_discount").val(discount  != '' ? discount: '-0');
	var calc = $("#calc").val();
	var t_calc = 0;
	var discount = (Number($("#pricetype").val()) == 1) ? 11000 : 15000;

	switch(refno) {
		case 0:
			t_calc = discount;
			
			$("#t_calc").val(t_calc);
			$("#t_pay").val(formatNumber(t_calc) + '원');
			break;
		case 1:
			$("#d_pay").val('주중 상영 영화관람');
			t_calc = ((Number(calc) - discount == 0) ? 0 : Number(calc) - discount);

			$("#t_calc").val(t_calc);
			$("#t_pay").val(formatNumber(t_calc) + '원');
			break;
		case 2:
			$("#d_pay").val('주말 상영 영화관람');
			t_calc = ((Number(calc) - discount == 0) ? 0 : Number(calc) - discount);
			
			$("#t_calc").val(t_calc);
			$("#t_pay").val(formatNumber(t_calc) + '원');
			break;
		case 3:
			$("#d_pay").val('상품 금액의 10% 할인');
			t_calc = Number(calc) * 0.9;
			
			$("#t_calc").val(t_calc);
			$("#t_pay").val(formatNumber(t_calc) + '원');
			break;
		case 4:
			$("#d_pay").val('상품 금액의 5% 할인');
			t_calc = Number(calc) * 0.95;
			
			$("#t_calc").val(t_calc);
			$("#t_pay").val(formatNumber(t_calc) + '원');
			break;
	}
	calc_point();
}

//사용할 포인트로 결제 금액 계산 -> 쿠폰 및 상품권 먼저 계산 후, 남은 금액에서 포인트로 차감
function calc_point() {
	var point = $("#point").val();
	var t_calc = $("#t_calc").val();
	
	$("#t_point").val(Number(point));
	$("#up_pay").val(Number(point) + "P");
	t_calc = Number(t_calc) - Number(point);
	
	$("#t_calc").val(t_calc);
	$("#t_pay").val(formatNumber(t_calc) + '원');
}

// 쿠폰 및 할인권 취소 시, 포인트로 결제 금액 계산
function c_calc_point() {
	var point = $("#point").val();
	var calc = $("#calc").val();
	
	$("#t_point").val(Number(point));
	$("#up_pay").val(Number(point) + "P");
	t_calc = Number(calc) - Number(point);
	
	$("#t_calc").val(t_calc);
	$("#t_pay").val(formatNumber(t_calc) + '원');
}

// 숫자를 천 단위 구분 기호를 추가하여 포맷팅하는 함수
function formatNumber(number) {
    return new Intl.NumberFormat().format(number);
}

function openPop(type) {
	if($("#refno").val() != 0) {
		alert('이미 할인권 또는 쿠폰을 등록하였습니다...');
		return false;	
	} else {
		window.open('/ticketing/couponPopUp/'+type, 'popupWindow', 'width=600,height=440,top=50,left=50,scrollbars=yes');
	}
}

function openPop2() {
	window.open('/ticketing/pointPopUp/', 'popupWindow', 'width=600,height=240,top=50,left=50,scrollbars=yes');
}