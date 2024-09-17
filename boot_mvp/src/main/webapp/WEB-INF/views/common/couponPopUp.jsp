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
	var coupon = $("#coupon").val();
	var pid = $("#pid").val();
	
	$.ajax({
		type: "post",
		url: "/ticketing/couponMatch",
		data: { coupon: coupon },
		headers: {
			'X-CSRF-TOKEN': getCsrfToken() // CSRF 토큰 추가
		},
		success: function(result) {
			if(result == 'T') {
				window.opener.document.getElementById(pid).value = '1000원'; /* 임시 값 */
				closePopup();
			} else {
				alert("존재하지 않는 쿠폰입니다...");
				return false;
			}
		}, error: function(xhr, status, error) {
            // 오류가 발생했을 때 처리할 내용
            console.error("AJAX 요청 실패:", xhr.status, status, error);
        }
	});
}
</script>
<body>
<form method="post" name="registform" action="">
	<input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<input type="hidden" id="gubun" name="gubun" value="${gubun }">
	<input type="hidden" id="pid" name="pid" value="${pid }">
	<div class="container">
        <section class="section">
        	<div class="title">
        		<h5>${title}</h5>
        	</div>
        	<div class="texts">
        		<input type="text" class="coupontext" id="coupon" name="coupon" placeholder="쿠폰 및 할인권 입력..." onkeydown="enter_regist();">
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