<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<script type="text/javascript" src="https://cdn.iamport.kr/v1/iamport.js"></script>
<body>
	<button type="button" class="pay" onclick="tosspay()">결제하기</button>
</body>
<script src="${pageContext.request.contextPath}/js/ticketing/api/payment.js"></script>
</html>