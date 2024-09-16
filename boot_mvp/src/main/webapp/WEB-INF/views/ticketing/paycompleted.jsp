<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta charset="UTF-8">
<title>예매 - MVP</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ticketing/paycompleted.css">
<script src="${pageContext.request.contextPath}/js/ticketing/ticketing_fn.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
</head>
<body>
<form method="post" name="paycomform" action="/main">
	<div class="container">
        <jsp:include page="../header.jsp"/>
        <section class="section">
            <div class="sectionCon">
                <div class="tabDiv">
                    <div class="tabWrap">
                        <div class="tab">
                            <a href="#none">01.상영시간</a>
                        </div>
                        <div class="tab">
                            <a href="#none">02.인원/좌석</a>
                        </div>
                        <div class="tab">
                            <a href="#none">03.결제</a>
                        </div>
                        <div class="tab active">
                            <a href="#none">04.결제완료</a>
                        </div>
                    </div>
                </div> <!-- tabDiv 끝-->
            </div> <!-- sectionCon 끝-->
            <div class="showWrap">
                <div class="movieDiv">
                    <div class="poster">
                        <img src="${movieinfo.moviepostimg }" alt="">
                    </div>
                    <div class="movieInfo">
                        <div class="movietitle">
                            <img src="${pageContext.request.contextPath}/images/rating/grade_${movieinfo.ratingno}.png" alt="">
                            <a href="#none">${movieinfo.movienm }</a>
                        </div>
                        <div class="infodetail">
                        	<fmt:formatDate value="${movieinfo.viewday }" pattern="yy.MM.dd" var="day"/>
                           	<fmt:formatDate value="${movieinfo.starttime }" pattern="HH:mm" var="start"/>
							<fmt:formatDate value="${movieinfo.endtime }" pattern="HH:mm" var="end"/>
                            <a href="#none" class="afont">일시</a>
                            <a href="#none" class="afont">${day }(${movieinfo.weekday }) | ${start } ~ ${end }</a>
                            <a href="#none" class="afont">상영관</a>
                            <a href="#none" class="afont">${movieinfo.theaternm } ${movieinfo.roomno }관</a>
                            <a href="#none" class="afont">인원</a>
                            <a href="#none" class="afont">
                            	<c:if test="${adult > 0 }">
                            		성인&nbsp;${adult}<br>
                            	</c:if>
                            	<c:if test="${youth > 0 }">
                            		청소년&nbsp;${youth}<br>
                            	</c:if>
                            	<c:if test="${old > 0 }">
                            		경로&nbsp;${old}<br>
                            	</c:if>
                            	<c:if test="${disable > 0 }">
                            		장애인&nbsp;${disable}<br>
                            	</c:if>
                            </a>
                            <a href="#none" class="afont">좌석</a>
                            <a href="#none" class="afont">${seats }</a>
                        </div> <!-- infodetail -->
                    </div> <!-- movieInfo end -->
                </div> <!-- movie end -->
                <div class="textDiv">
                    <a href="#none" class="atext">해당 영화의 결제가 완료 되었습니다.</a>
                </div>
                <div class="buttonDiv">
                	<input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <button type="button" class="movebtn" onclick="move_form('/main')">메인 화면</button>
                    <button type="button" class="movebtn" onclick="move_form('/ticketing/movieselect')">다른 영화 살펴보기</button>
                </div>
            </div> <!-- showWrap end -->
        </section>
    </div>
    <jsp:include page="../footer.jsp"/>
</form>
</body>
</html>