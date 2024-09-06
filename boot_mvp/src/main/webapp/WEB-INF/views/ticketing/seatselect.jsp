<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta charset="UTF-8">
<title>예매 - MVP</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ticketing/seatselect.css">
<script src="${pageContext.request.contextPath}/js/ticketing/ticketing_fn.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
</head>
<body>
	<div class="container">
        <header class="header">
            <div class="headerInner">
                <h5 class="logo">
                    <a href="#">
                        MVP
                    </a>
                </h5>
                <ul class="menuList">
                    <li class="menu">메뉴1</li>
                    <li class="menu">메뉴2</li>
                    <li class="menu">메뉴3</li>
                    <li class="menu">메뉴4</li>
                    <li class="menu">메뉴5</li>
                </ul>
                <div class="rightWrap">
                    <div class="inputWrap">
                        <input type="text" class="search">                    
                    </div>
                    <button type="button" class="login">로그인</button>
                </div>
            </div>            
        </header>
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
                        <div class="tab active">
                            <a href="#none">03.결제</a>
                        </div>
                        <div class="tab">
                            <a href="#none">04.결제완료</a>
                        </div>
                    </div>
                    <h5 class="sideTxt">
                        <a href="#">·인원은 최대 8명까지 선택 가능합니다.</a>
                    </h5>
                </div> <!-- tabDiv 끝-->
                <div class="selectDiv">
                    <div class="movieInfo">
                        <img src="${movieinfo.moviepostimg }" alt="">
                            <div class="movieR">
                                <div class="movieRT">
                                    <img src="${pageContext.request.contextPath}/images/rating/grade_${movieinfo.ratingno }.png" alt="">
                                    <h5 class="movieName">
                                        <a href="#none">${movieinfo.movienm }</a>
                                    </h5>
                                </div>
                                <div class="movieRM">
                                	<%-- 각각 날짜 변환 --%>
                                	<fmt:formatDate value="${movieinfo.viewday }" pattern="yy.MM.dd" var="day"/>
                                	<fmt:formatDate value="${movieinfo.starttime }" pattern="HH:mm" var="start"/>
                                    <fmt:formatDate value="${movieinfo.endtime }" pattern="HH:mm" var="end"/>
                                    <%-- 각각 날짜 변환 --%>
                                    <h5 class="movieDate"><a href="#none">${day }</a></h5>
                                    <h5 class="movieDay"><a href="#none">(${movieinfo.weekday })</a></h5>
                                    <h5 class="movieTime"><a href="#none">&nbsp;| ${start } ~ ${end }</a></h5>
                                </div>
                                <div class="movieRB">
                                    <h5 class="movieLocation">
                                        <a href="#none">${movieinfo.theaternm }</a>
                                    </h5>
                                </div>
                            </div> <!-- movieR 끝-->
                    </div> <!-- movieInfo 끝-->
                    <div class="selectInfo">
                        <div class="selectWrap adult">
                            <h5 class="title">성인</h5>
                            <div class="count">
                                <button type="button" class="down" onclick="down('adult')">-</button>
                                <div class="number">
                                    <button type="button" class="now" data-count="0" id="adult">0</button>
                                    <ul class="num-choice">
                                        <li>
                                            <button type="button" class="btn">0</button>
                                        </li>
                                    </ul>
                                </div>
                                <button type="button" class="up" onclick="up('adult')">+</button>
                            </div>
                        </div>
                        <div class="selectWrap student">
                            <h5 class="title">청소년</h5>
                            <div class="count">
                                <button type="button" class="down" onclick="down('youth')">-</button>
                                <div class="number">
                                    <button type="button" class="now" data-count="0" id="youth">0</button>
                                    <ul class="num-choice">
                                        <li>
                                            <button type="button" class="btn">0</button>
                                        </li>
                                    </ul>
                                </div>
                                <button type="button" class="up" onclick="up('youth')">+</button>
                            </div>
                        </div>
                        <div class="selectWrap eld">
                            <h5 class="title">경로</h5>
                            <div class="count">
                                <button type="button" class="down" onclick="down('old')">-</button>
                                <div class="number">
                                    <button type="button" class="now" data-count="0" id="old">0</button>
                                    <ul class="num-choice">
                                        <li>
                                            <button type="button">0</button>
                                        </li>
                                    </ul>
                                </div>
                                <button type="button" class="up" onclick="up('old')">+</button>
                            </div>
                        </div>
                        <div class="selectWrap disabled">
                            <h5 class="title">장애인</h5>
                            <div class="count">
                                <button type="button" class="down" onclick="down('disable')">-</button>
                                <div class="number">
                                    <button type="button" class="now" data-count="0" id="disable">0</button>
                                    <ul class="num-choice">
                                        <li>
                                            <button type="button">0</button>
                                        </li>
                                    </ul>
                                </div>
                                <button type="button" class="up" onclick="up('disable')">+</button>
                            </div>
                        </div>
                    </div>
                </div> <!-- selectDiv 끝-->
                <div class="screenDiv">
                    <h5 class="screenTitle">
                        <a href="#">SCREEN</a>
                    </h5>
                    <div class="screenWrap">
                        <div class="sitWrap">
	                        <c:forEach items="${seatline }" var="seat">
	                            <div class="sitCol">
	                            	<div class="sitNum">
	                            		<h5>${seat}</h5>
	                            	</div>
	                                <div class="sitDiv">
	                                	<c:forEach begin="1" end="14" var="num">
	                                		<a href="#none" class="sit" id="${seat}${num}" data-seatnum="${seat}${num}" onclick="select_seat('${seat}${num}')">${num }</a>
	                                		<%-- <input type="text" class="sit" value="${num }" disabled> --%>
	                                	</c:forEach>
	                                </div> <!-- sitDiv-->      
	                            </div>  <!-- sitCol -->    
                            </c:forEach>   
                        </div>
                    </div> <!-- screenWrap 끝 -->
                </div> <!-- screenDiv 끝 -->
                <div class="guideWrap">
                    <div class="guide">
                        <img src="${pageContext.request.contextPath}/images/seat/ic_seat_type9.png" alt="">
                        <a href="#none">장애인석</a>
                    </div>
                    <div class="guide">
                        <img src="${pageContext.request.contextPath}/images/seat/ic_seat_type11.png" alt="">
                        <a href="#none">출입구</a>
                    </div>
                    <div class="guide">
                    	<img src="${pageContext.request.contextPath}/images/seat/ic_seat_type3.png" alt="">
                        <a href="#none">선택좌석</a>
                    </div>
                    <div class="guide">
                        <img src="${pageContext.request.contextPath}/images/seat/ic_seat_type4.png" alt="">
                        <a href="#none">예매완료</a>
                    </div>
                    <div class="guide">
                        <img src="${pageContext.request.contextPath}/images/seat/ic_seat_type1.png" alt="">
                        <a href="#none">선택불가</a>
                    </div>
                </div><!-- guideWrap end -->
                <div class="botWrap">
                	<input type="hidden" id="price" value="${movieinfo.nowprice }">
                	<input type="hidden" id="calc" name="calc">
                	<input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <a href="#none" id="calcshow">총 합계 0원</a>
                    <input type="hidden" id="next" value="N">
                    <button type="button" class="paybtn" onclick="payment()">결 제</button>
                </div>
            </div> <!-- sectionCon 끝-->
        </section>
    </div>
</body>
</html>