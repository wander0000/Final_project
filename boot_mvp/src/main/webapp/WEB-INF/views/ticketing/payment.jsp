<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta charset="UTF-8">
<title>예매 - MVP</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ticketing/payment.css">
<script src="${pageContext.request.contextPath}/js/ticketing/ticketing_fn.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
</head>
<body>
<form>
	<div class="container">
        <header class="header">
            <div class="headerInner">
                <h5 class="logo">
                    <a href="#none">
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
                </div> <!-- tabDiv 끝-->
            </div> <!-- sectionCon 끝-->
            <div class="showWrap">
                <div class="movieDiv">
                    <div class="movieinfo">
                        <div class="poster">
                            <img src="${movieinfo.moviepostimg }" alt="movie_post" width="250" height="357">
                        </div>
                        <div class="mtitle">
                            <img src="${pageContext.request.contextPath}/images/rating/grade_${movieinfo.ratingno}.png" alt="">
                            <a href="#none">${movieinfo.movienm }</a>
                        </div>
                        <div class="infodetail">
                        	<fmt:formatDate value="${movieinfo.viewday }" pattern="yy.MM.dd" var="day"/>
                           	<fmt:formatDate value="${movieinfo.starttime }" pattern="HH:mm" var="start"/>
							<fmt:formatDate value="${movieinfo.endtime }" pattern="HH:mm" var="end"/>
                            <a href="#none">일시</a>
                            <a href="#none">${day }(${movieinfo.weekday }) | ${start } ~ ${end }</a>
                            <a href="#none">상영관</a>
                            <a href="#none">${movieinfo.theaternm } ${movieinfo.roomno }관</a>
                            <a href="#none">인원</a>
                            <a href="#none">
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
                        </div>
                    </div>
                    <div class="seatinfo">
                        <div class="seatdetail">
                            <a href="#none">좌석</a>
                            <a href="#none">J7, J8</a>
                        </div>
                    </div>
                </div> <!-- movieDiv end -->
                <div class="discountDiv">
                    <div class="couponDiv">
                        <div class="coupontitle">
                            <a href="#none">쿠폰 할인</a>
                        </div>
                        <div class="couponarea">
                            <a href="#none" class="afont">쿠폰</a>
                            <button type="button" class="discountBtn">쿠폰</button>
                            <input type="text" id="coupon" value="-0 원" readonly>
                        </div>
                    </div> <!-- couponDiv end -->
                    <div class="pointDiv">
                        <div class="pointtitle">
                            <a href="#none">할인 / 포인트</a>
                        </div>
                        <div class="pointarea">
                            <a href="#none" class="afont">할인권</a>
                            <button type="button" class="discountBtn">할인권</button>
                            <input type="text" id="discount" value="-0 원" readonly>
                        </div>
                        <div class="pointarea">
                            <a href="#none" class="afont">포인트</a>
                            <button type="button" class="discountBtn">모두 사용</button>
                            <input type="text" id="point" value="0 P">
                        </div>
                    </div> <!-- pointDiv end -->
                    <div class="paymentDiv">
                        <div class="paymenttitle">
                            <a href="#none">최종 결제</a>
                        </div>
                        <div class="paymentbox">
                            <button type="button" class="discountBtn">신용 카드</button>
                            <button type="button" class="discountBtn">신용 카드</button>
                            <button type="button" class="discountBtn">신용 카드</button>
                            <button type="button" class="discountBtn">신용 카드</button>
                        </div>
                    </div> <!-- paymentDiv end -->
                </div> <!-- discountDiv end -->
                <div class="finalpay">
                    <div class="discountLists">
                        <div class="discountListarea">
                            <div class="discountList">
                                <a href="#none">쿠폰</a>
                                <input type="text" id="t_coupon" value="-0 원" readonly>
                            </div>
                            <div class="discountList">
                                <a href="#none">할인권</a>
                                <input type="text" id="t_discount" value="-0 원" readonly>
                            </div>
                            <div class="discountList">
                                <a href="#none">포인트</a>
                                <input type="text" id="t_point" value="-0 P" readonly>
                            </div>
                        </div>
                    </div>
                    <div class="totalpay">
                        <div class="showpay">
                            <a href="#none">상품금액</a>
                            <input type="text" id="p_pay" value="28,000 원" readonly>
                        </div>
                        <div class="showpay">
                            <a href="#none">할인금액</a>
                            <input type="text" id="d_pay" value="- 0" readonly>
                        </div>
                        <div class="showpay">
                            <a href="#none">결제금액</a>
                            <input type="text" id="t_pay" value="28,000 원" readonly>
                        </div>
                        <input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <button type="button" class="pay">결제하기</button>
                    </div>
                </div> <!-- finalpay-->
            </div> <!-- showWrap end -->
        </section>
    </div>
</form>
</body>
</html>