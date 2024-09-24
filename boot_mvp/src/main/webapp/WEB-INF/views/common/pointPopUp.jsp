<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta charset="UTF-8">
<title>MVP_Coupon</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ticketing/point.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
</head>
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
        use_point(); // 엔터키를 눌렀을 때 실행할 함수
    }
}

function use_point() {
	var point = Number($("#point").val());
	var maxonhqt = Number($("#maxonhqt").val());
	
	if(point > maxonhqt) {
		alert('현재 보유한 포인트보다 더 많은 포인트를 입력하였습니다.');
		return false;
	} else if(point % 10 != 0) {
		alert('포인트의 최소 사용 단위는 10입니다.');
		return false;
	} else if(point == 0 || point == '') {
		alert('사용할 포인트를 입력하세요.');
		 $('#point').focus();
		return false;
	} else {
		window.opener.document.getElementById('point').value = point;
		
		cancel_button = window.opener.document.getElementById("P_cancel");
		$(cancel_button).addClass("showbtn");
		
		window.opener.calc_point(); //현재 창(자식)에서 부모 창의 스크립트 함수 처리
		closePopup();
	}
}

function use_all() {
	window.opener.document.getElementById('point').value = Number($("#maxonhqt").val());
	
	cancel_button = window.opener.document.getElementById("P_cancel");
	$(cancel_button).addClass("showbtn");
	
	window.opener.calc_point(); //현재 창(자식)에서 부모 창의 스크립트 함수 처리
	closePopup();
}


</script>
<body>
<form method="post" name="registform" action="">
	<input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<div class="container">
        <section class="section">
        	<div class="title">
        		<h5>${title}</h5>
        	</div>
        	<div class="point-container">
        		<input type="hidden" id="maxonhqt" value="${points.onhqt }">
        		<input type="text" class="texts" id="point" name="point" placeholder="총 사용 가능 포인트 ${points.onhqt }" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" onkeydown="enter_regist();">
        	</div>
            <div class="btn_box">
            	<button type="button" class="mainbtn" onclick="use_all()">모두사용</button>
	            <button type="button" class="mainbtn" onclick="use_point()">사용하기</button>
	            <button type="button" class="mainbtn" onclick="closePopup();">닫기</button>
           	</div>
        </section>
    </div>
</form>
</body>
</html>