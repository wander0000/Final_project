<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<body>
	<div class="selectit">
	    <img src="${pageContext.request.contextPath}/images/rating/grade_${minfo.ratingno}.png" alt="">
	    <input type="hidden" id="movienm" value="${minfo.movienm }">
	    <a href="#none">${minfo.movienm }</a>
	</div>
	<div class="listtime">
		<c:forEach items="${detailinfo }" var="info">
			<li>
		        <a href="#none" onclick="seatFrom('${info.areano }', '${info.theaterno }', '${info.movieno }', '${info.viewday }','${info.roomno }','${info.starttime }', '${info.pricetype }')">
		            <dl>
		            	<c:if test="${info.pricetype == 1 }">
		            		<span class="txt ic">조조</span>
		            	</c:if>
		                <dd class="time">
		                	<fmt:formatDate value="${info.starttime }" pattern="hh : mm" var="time"/>
		                    ${time }
		                    <div class="tooltip"></div>
		                </dd>
		                <dd class="seat">
		                    ${info.selecseat } / ${info.totalseat }
		                </dd>
		                <dd class="hall">
		                    ${info.roomno }
		                </dd>
		            </dl>
		        </a>
		    </li>
		</c:forEach>
	</div>
</body>
</html>