<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- 
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ticketing/style.css">
<script src="${pageContext.request.contextPath}/js/ticketing/ticketing_fn.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
--%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<input type="hidden" id="areano_m" value="${areano }">
	<input type="hidden" id="theaterno_m" value="${theaterno }">
	<c:forEach items="${movie }" var="movie">
		<div class="boxtit">
	        <img src="${pageContext.request.contextPath}/images/rating/grade_${movie.ratingno }.png" alt="">
	        <input type="hidden" id="selected_${movie.movieno}">
	        <a href="#none" onclick="movieevent('${movie.movieno}', '${movie.movienm }');">${movie.movienm }</a>
		</div>
	</c:forEach>
</body>
</html>