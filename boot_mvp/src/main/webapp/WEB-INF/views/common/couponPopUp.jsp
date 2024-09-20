<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta charset="UTF-8">
<title>MVP_Coupon</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ticketing/coupon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
</head>
<script>
window.onload = function() {
    $("#coupon").focus();
};
</script>
<script>
function getCsrfToken() {
    return $("#csrf").val();
}

function closePopup() {
	window.close();
}

function enter_regist() {
	if (event.key == "Enter" || event.keyCode == 13) {
        event.preventDefault(); // 폼 제출 방지 (옵션)
        regist_coupone(); // 엔터키를 눌렀을 때 실행할 함수
    }
}

function regist_coupone() {
	var couponno = $("#couponno").val();
	var selectedCoupon = $('input[name="selectedCoupon"]:checked').val();
	var type = $("#type").val();
	var pid = $("#pid").val();
	var cancel_button = '';
	
	if(selectedCoupon == 'none') {
		window.opener.document.getElementById(pid).value = '';
		window.opener.document.getElementById('refno').value = 0;
		cancel_button = window.opener.document.getElementById(type+"_cancel");
		window.opener.document.getElementById('couponno').value = 0;
		$(cancel_button).removeClass("showbtn");
		closePopup();
	} else {
		$.ajax({
			type: "post",
			url: "/ticketing/couponMatch",
			data: { couponno: selectedCoupon, type: type },
			headers: {
				'X-CSRF-TOKEN': getCsrfToken() // CSRF 토큰 추가
			},
			success: function(data) {
				// console.log('JSON: ' + JSON.stringify(data));
				
				if(data.result == 'T') {
					window.opener.document.getElementById(pid).value = data.benefit;
					window.opener.document.getElementById('refno').value = data.refno;
					window.opener.document.getElementById('couponno').value = selectedCoupon;
					
					cancel_button = window.opener.document.getElementById(type+"_cancel");
					$(cancel_button).addClass("showbtn");
					
					alert(data.msg);
					
					window.opener.show_pay(); //현재 창(자식)에서 부모 창의 스크립트 함수 처리
					closePopup();
				} else {
					alert(data.msg);
					return false;
				}
			}, error: function(xhr, status, error) {
	            // 오류가 발생했을 때 처리할 내용
	            console.error("AJAX 요청 실패:", xhr.status, status, error);
	        }
		});
	}
}
</script>
<body>
<form method="post" name="registform" action="">
	<input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<input type="hidden" id="type" name="type" value="${type }">
	<input type="hidden" id="pid" name="pid" value="${pid }">
	<div class="container">
        <section class="section">
        	<div class="title">
        		<h5>${title}</h5>
        	</div>
        	<div class="coupons">
        		<%-- <input type="text" class="coupontext" id="couponno" name="couponno" placeholder="쿠폰 및 할인권 입력..." onkeydown="enter_regist();"> --%>
        		<div class="coupon-container">
       				<input type="radio" id="coupon-non" name="selectedCoupon" value="none">
       				<label class="couponbox" for="coupon-non">적용 안함</label>
       			</div>
        		<c:forEach items="${coupons }" var="coupon">
        			<div class="coupon-container">
        				<input type="radio" id="coupon-${coupon.couponno}" name="selectedCoupon" value="${coupon.couponno}">
        				<label class="couponbox" for="coupon-${coupon.couponno}">${coupon.benefit}</label>
        			</div>
        		</c:forEach>
        	</div>
            <div class="btn_box">
	            <button type="button" class="mainbtn" onclick="regist_coupone()">등록하기</button>
	            <button type="button" class="mainbtn" onclick="closePopup();">닫기</button>
           	</div>
        </section>
    </div>
</form>
</body>
</html>