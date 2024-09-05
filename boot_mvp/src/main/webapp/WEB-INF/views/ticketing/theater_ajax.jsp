<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- 
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ticketing/style.css">
<script src="${pageContext.request.contextPath}/js/ticketing/ticketing_fn.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
--%>
</head>
<body>
	<input type="hidden" id="areano_t" value="${areano }">
	<c:forEach items="${theater }" var="theater">
		<div class="Div_tab">
			<a href="#none" onclick="theaterevent('${theater.theaterno}', '${theater.theaternm }')">${theater.theaternm }</a>
		</div>
	</c:forEach>
</body>
</html>