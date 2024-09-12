<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta charset="UTF-8">
<title>MVP</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/error/error.css">
</head>
<body>
<form method="post" name="errorform" action="/main">
	<input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<div class="container">
        <section class="section">
            <h5>잘못된 접근입니다...</h5>
            <button type="submit" class="mainbtn">돌아가기</button>
        </section>
    </div>
</form>
</body>
</html>