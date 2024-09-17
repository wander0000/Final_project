function getCsrfToken() {
    return $("#csrf").val();
}

function dis_pay_button() {
	alert('결제 방법을 선택하세요.');
	return false;
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
			show_pay();
			
			$("#paybutton").html(response);
		}, error: function(xhr, status, error) {
            // 오류가 발생했을 때 처리할 내용
            console.error("AJAX 요청 실패:", xhr.status, status, error);
        }
	});
}

function show_pay() {
	var coupon = $("#coupon").val();
	var discount = $("#discount").val();
	var point = $("#point").val();
	
	$("#t_coupon").val(coupon);
	$("#t_discount").val(discount);
	$("#t_point").val(point);
	
	var n_coupon = coupon.replace(/원/g, '').trim();
	var n_discount = discount.replace(/원/g, '').trim();
	var n_point = point.replace(/P/g, '').trim();
	var d_pay = Number(n_coupon) + Number(n_discount) + Number(n_point);
	
	$("#d_pay").val(d_pay+"원");
	
	var p_pay = $("#t_calc").val().replace(/원/g, '').trim();

	var t_calc = Number(p_pay) - d_pay;
	
	$("#t_calc").val(t_calc);
	$("#t_pay").val(formatNumber(t_calc) + '원');
}

// 숫자를 천 단위 구분 기호를 추가하여 포맷팅하는 함수
function formatNumber(number) {
    return new Intl.NumberFormat().format(number);
}

function openPop(gubun) {
	window.open('/ticketing/couponPopUp/'+gubun, 'popupWindow', 'width=600,height=150,top=50,left=50,scrollbars=yes');
}