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
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ticketing/style.css">
<script src="${pageContext.request.contextPath}/js/ticketing/ticketing_fn.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
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
                    <button class="login">로그인</button>
                </div>
            </div>            
        </header>
        <section class="section">
            <div class="sectionCon">
                <div class="tabDiv">
                    <div class="tabWrap">
                        <div class="tab active">
                            <a href="">01.상영시간</a>
                        </div>
                        <div class="tab">
                            <a href="">02.인원/좌석</a>
                        </div>
                        <div class="tab">
                            <a href="">03.결제</a>
                        </div>
                        <div class="tab">
                            <a href="">04.결제완료</a>
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
                        <a href="#none">에이리언: 로물루스</a>
                    </div>
                    <div class="show">
                        <a href="#none">24.08.21(수)</a>
                    </div>
                </div>
                <div class="selectWrap">
                    <div class="areaWrap">                        
                        <div class="areaDiv">
                            <div class="areabox">
                                <div class="areanav">
                                    <a href="#">전체</a>
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
									<!-- theater_ajax.jsp 호출 -->
                                </div>
                            </div>
                        </div> <!--areaDiv end -->
                    </div> <!-- areabox end -->
                    <div class="movieWrap">
                        <div class="movieDiv">
                            <div class="movienav">
                                <a href="">영화 선택</a>
                            </div>
                            <div class="moivebox">
                                <div class="boxtit">
                                    <img src="img/grade_all.png" alt="">
                                    <a href="">이오 카피타노</a>
                                </div>
                                <div class="boxtit">
                                    <img src="img/grade_all.png" alt="">
                                    <a href="">물은 바다를 향해 흐른다</a>
                                </div>
                                <div class="boxtit">
                                    <img src="img/grade_all.png" alt="">
                                    <a href="">필사의 추력</a>
                                </div>
                                <div class="boxtit">
                                    <img src="img/grade_all.png" alt="">
                                    <a href="">1923 간토대학살</a>
                                </div>
                                <div class="boxtit">
                                    <img src="img/grade_19.png" alt="">
                                    <a href="">마야</a>
                                </div>
                                <div class="boxtit active">
                                    <img src="img/grade_15.png" alt="">
                                    <a href="">에이리언: 로물루스</a>
                                </div>
                            </div> <!-- moivebox -->
                        </div> <!-- movieDiv end-->
                    </div> <!-- theaterWrap end -->
                    <div class="reservedateWrap">
                        <div class="reservedateDiv">
                        	<ul class="carousel">
		                        <div class="state">
		                            <div class="reservedatenav">
		                            <%-- 
		                                <div class="arrowPos">
		                                    <button class="arrow"><</button>
		                                </div>                
		                            --%>
		                            <c:forEach items="${date }" var="date">
										<div class="owl-item active">
			                                <li class=item>
			                                	<a href="#none">
			                                		<label for="radio${day }">
			                                			<input type="radio" id="radio0" name="radio">
			                                			<fmt:formatDate value="${date }" pattern="dd" var="day"/>
				                                		<div class="dayno">${day }</div>
				                                		<em>오늘</em>
			                                		</label>
			                                	</a>
			                                </li>
		                                </div>
		                            </c:forEach>
		                            <%-- 
		                                <div class="owl-item active">
			                                <li class=item>
			                                	<a href="#none">
			                                		<label for="radio0">
			                                			<input type="radio" id="radio0" name="radio">
				                                		<div class="dayno">3</div>
				                                		<em>오늘</em>
			                                		</label>
			                                	</a>
			                                </li>
		                                </div>
		                                <div class="owl-item active">
			                                <li class=item>
			                                	<a href="#none">
			                                		<label for="radio1">
			                                			<input type="radio" id="radio1" name="radio">
			                                			<div class="dayno">4</div>
			                                		</label>
			                                	</a>
			                                </li>
		                                </div>
		                                <div class="owl-item active">
			                                <li class=item>
			                                	<a href="#none">
			                                		<label for="radio2">
			                                			<input type="radio" id="radio2" name="radio">
			                                			<div class="dayno">5</div>
			                                		</label>
			                                	</a>
			                                </li>
		                                </div>
		                                <div class="owl-item active">
			                                <li class=item>
			                                	<a href="#none">
			                                		<label for="radio3">
			                                			<input type="radio" id="radio3" name="radio">
			                                			<div class="dayno">6</div>
			                                		</label>
			                                	</a>
			                                </li>
		                                </div>
		                                <div class="owl-item active">
			                                <li class=item>
			                                	<a href="#none">
			                                		<div class="dayno">7</div>
			                                	</a>
			                                </li>
		                                </div>
		                                <div class="owl-item active">
			                                <li class=item>
			                                	<a href="#none">
			                                		<div class="dayno">8</div>
			                                	</a>
			                                </li>
		                                </div>
		                                <div class="owl-item active">
			                                <li class=item>
			                                	<a href="#none">
			                                		<div class="dayno">9</div>
			                                	</a>
			                                </li>
		                                </div>
		                                <div class="owl-item active">
			                                <li class=item>
			                                	<a href="#none">
			                                		<div class="dayno">10</div>
			                                	</a>
			                                </li>
		                                </div>
		                                <div class="owl-item">
			                                <li class=item>
			                                	<a href="#none">
			                                		<div class="dayno">11</div>
			                                	</a>
			                                </li>
		                                </div>
		                                <div class="owl-item">
			                                <li class=item>
			                                	<a href="#none">
			                                		<div class="dayno">12</div>
			                                	</a>
			                                </li>
		                                </div>
		                                <div class="owl-item">
			                                <li class=item>
			                                	<a href="#none">
			                                		<div class="dayno">13</div>
			                                	</a>
			                                </li>
		                                </div>
		                                <div class="owl-item">
			                                <li class=item>
			                                	<a href="#none">
			                                		<div class="dayno">14</div>
			                                	</a>
			                                </li>
		                                </div>
		                                <div class="owl-item">
			                                <li class=item>
			                                	<a href="#none">
			                                		<div class="dayno">15</div>
			                                	</a>
			                                </li>
		                                </div>
		                                <div class="owl-item">
			                                <li class=item>
			                                	<a href="#none">
			                                		<div class="dayno">16</div>
			                                	</a>
			                                </li>
		                                </div>
		                                <div class="owl-item">
			                                <li class=item>
			                                	<a href="#none">
			                                		<div class="dayno">17</div>
			                                	</a>
			                                </li>
		                                </div>
		                                --%>
		                            <%-- 
		                                <div class="arrowPos">
		                                    <button class="arrow">></button>
		                                </div>
		                            --%>
		                            </div>
	                            </div> <!-- state end -->
                            </ul>
                            <div class="grouptime">
                                <div class="groupbox">
                                    <div class="selectit">
                                        <img src="img/grade_15.png" alt="">
                                        <a href="">에이리언: 로물루스</a>
                                    </div>
                                    <div class="listtime">
                                        <li>
                                            <a role="button" href="#none">
                                                <dl>
                                                    <dd class="time">
                                                        10 : 10
                                                        <div class="tooltip"></div>
                                                    </dd>
                                                    <dd class="seat">
                                                        194 / 210
                                                    </dd>
                                                    <dd class="hall">
                                                        9관
                                                    </dd>
                                                </dl>
                                            </a>
                                        </li>
                                        <li>
                                            <a role="button" href="#none">17 : 10</a>
                                        </li>
                                        <li>
                                            <a role="button" href="#none">17 : 10</a>
                                        </li>
                                            <li>
                                            <a role="button" href="#none">17 : 10</a>
                                        </li>
                                            <li>
                                            <a role="button" href="#none">17 : 10</a>
                                        </li>
                                        <li>
                                            <a role="button" href="#none">17 : 10</a>
                                        </li>
                                        <li>
                                            <a role="button" href="#none">17 : 10</a>
                                        </li>
                                    </div><!-- listtime end -->
                                </div> <!-- groupbox end -->
                            </div> <!-- grouptime end -->
                        </div> <!-- reservedateDiv end -->
                    </div> <!-- reservedateWrap end -->
                </div> <!-- selecWrap_end -->
            </div> <!-- showWrap -->
        </section>
    </div>
</body>
</html>