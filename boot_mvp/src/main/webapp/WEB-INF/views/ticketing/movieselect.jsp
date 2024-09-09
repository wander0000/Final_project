<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
<meta charset="UTF-8">
<title>예매 - MVP</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ticketing/movieselect.css">
<script src="${pageContext.request.contextPath}/js/ticketing/ticketing_fn.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<!-- 스와이퍼 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
<!-- 스와이퍼 -->
</head>
<script>
//페이지가 완전히 로드된 후 실행되는 함수
window.onload = function() {
    // 첫 번째 a 태그를 찾기
    var firstLink = document.querySelector('.areascroll .Div_tab a');
    if (firstLink) {
        // 첫 번째 a 태그의 클릭 이벤트를 트리거
        firstLink.click();
    }
    //movieevent('N');
};
</script>
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
                        <div class="tab active">
                            <a href="#none">01.상영시간</a>
                        </div>
                        <div class="tab">
                            <a href="#none">02.인원/좌석</a>
                        </div>
                        <div class="tab">
                            <a href="#none">03.결제</a>
                        </div>
                        <div class="tab">
                            <a href="#none">04.결제완료</a>
                        </div>
                    </div>
                </div> <!-- tabDiv 끝-->
            </div> <!-- sectionCon 끝-->
            <div class="showWrap">
                <div class="selecWrapshow">
                    <div class="show">
                        <a href="#none" id="showtheater">상영관</a>
                    </div>
                    <div class="show">
                        <a href="#none" id="showtitle">영화 선택</a>
                    </div>
                    <div class="show">
                        <a href="#none" id="showdate">일자 선택</a>
                    </div>
                </div>
                <div class="selectWrap">
                    <div class="areaWrap">                        
                        <div class="areaDiv">
                            <div class="areabox">
                                <div class="areanav">
                                    <a href="#none">전체</a>
                                </div>
                                <div class="areascroll">
                                	<c:forEach items="${area}" var="area">
	                                	<div class="Div_tab">
	                                		<input type="hidden" name="areano" value="${area.areano }">
	                                        <a href="#none" onclick="area_event('${area.areano }')">${area.areanm }(${area.cnt})</a>
	                                    </div>
                                	</c:forEach>
                                </div>
                            </div>
                            <div class="theaterbox">
                                <div class="theaternav">
                                    <a href="#none">상영관</a>
                                </div>
                                <div id="theaterscroll" class="theaterscroll">
									<!-- theater_ajax.jsp 호출 영역 -->
                                </div>
                            </div>
                        </div> <!--areaDiv end -->
                    </div> <!-- areabox end -->
                    <div class="movieWrap">
                        <div class="movieDiv">
                            <div class="movienav">
                                <a href="#none">영화 선택</a>
                            </div>
                            <div id="moviebox" class="moviebox">
                            	<!-- movie_ajax.jsp 호출 영역 -->
                            	<c:forEach items="${movie }" var="movie">
                               		<div class="boxtit">
								        <img src="${pageContext.request.contextPath}/images/rating/grade_15.png" alt="">
								        <a href="#none" onclick="movies();">${movie.movienm }</a>
									</div>
                               	</c:forEach>
                            </div> <!-- moivebox -->
                        </div> <!-- movieDiv end-->
                    </div> <!-- theaterWrap end -->
                    <div class="reservedateWrap">
                        <div class="reservedateDiv">
			                <div class="reservedatenav">
			                	<%-- 
			                	<div class="arrowPos">
                                    <button type="button" class="arrow" onclick="preve();"><</button>
                                </div>
                                --%>
                                <input type="hidden" id="areano_date">
                                <input type="hidden" id="theaterno_date">
                                <input type="hidden" id="movieno_date">
                                <input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>    
                                <div class="swiper mySwiper">
                                	<div class="swiper-button-prev"></div>
                                	<div class="swiper-wrapper">
		                                <c:forEach items="${date }" var="date" varStatus="idx">
		                                <div class="swiper-slide">
		                                	<fmt:formatDate value="${date.date }" pattern="dd" var="day"/>
		                                	<div class="item active">
			                                	<a href="#none" onclick="date_event('${date.date }');">
			                                		<label for="radio${idx.index }">
								                    	<input type="radio" id="radio${idx.index }" name="radiodate" style="display: none;">
									                    <div class="dayno">${day }</div>
					                                	<c:if test="${idx.index == 0 }">
					                                		<em>오늘</em>
					                                	</c:if>
				                                	</label>
			                                	</a>
		                                	</div>
		                                </div> <!-- swiper-slide end -->
		                                </c:forEach>
	                                </div>
	                                <div class="swiper-button-next"></div>
                                </div>
                                <%-- 
                                <div class="arrowPos">
                                    <button type="button" class="arrow" onclick="next();">></button>
                                </div>
                                --%>     	
                            </div>
                            <div class="grouptime">
                                <div id="groupbox" class="groupbox">
                                    <!-- date_ajax.jsp 호출 -->
                                </div> <!-- groupbox end -->
                            </div> <!-- grouptime end -->
                        </div> <!-- reservedateDiv end -->
                    </div> <!-- reservedateWrap end -->
                </div> <!-- selecWrap_end -->
            </div> <!-- showWrap -->
        </section>
    </div>
</body>
<script>    
var swiper = new Swiper(".mySwiper",     
{            
    slidesPerView: 8, // 화면에 보이는 슬라이드 수
    spaceBetween: 1, // 슬라이드 사이의 간격
    slidesPerGroup: 8, // 한 번에 이동할 슬라이드 수
    loop: false, // 루프 여부 설정
    
    navigation : 
    {
        nextEl : '.swiper-button-next', // 다음 버튼 클래스명
        prevEl : '.swiper-button-prev', // 이번 버튼 클래스명
    }
});
</script>
</html>