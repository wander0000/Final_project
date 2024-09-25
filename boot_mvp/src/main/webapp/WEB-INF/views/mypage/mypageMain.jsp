<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MyMVP_메인</title>
    <link rel="stylesheet" type="text/css" href='https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/static/pretendard.css'>  
    <!-- google font -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />    
    <!-- import font-awesome, line-awesome -->
    <!-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/board_detail.css"> -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/line-awesome/1.3.0/line-awesome/css/line-awesome.min.css">
	<!-- swiper-->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css">
    <!-- import js -->
    <script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage/default.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage/mypageMain.css">
	<script src="${pageContext.request.contextPath}/js/mypage/mypageMain.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/header.jsp" %>
    <section class="section">
		<%@ include file="mypageHeader.jsp" %>
        <div class="mainContainer">
            <div class="mainContainerContent">
                <h3 class="menuTitle">
                    My MVP
                </h3>         
                <div class="mainContent">
                   
                    <div class="contentDiv">
                           
						<div class="topBoxes">
						    <div class="cardBox one">
						        <div class="cardUp">
						            <div class="font20 fff">안녕하세요!</div>
						            <div class="font24 fff">${userName} 님</div>
						        </div>
						        <div class="cardDown">
						            <a href="${pageContext.request.contextPath}/mypage/userInfo" class="font14">회원정보수정</a>
						        </div>
						    </div><!-- cardBox one 끝 -->
						    <div class="cardBox two">
						        <div class="cardU">
						            <div class="font16 fff">현재 등급</div>
						            <div class="font16 mColor">${membership.grade}</div>
						        </div>
						        <div class="locatiionCon">
						            <div class="alertBox">
						                <c:choose>
						                    <c:when test="${membership.grade == 'Welcome'}">
						                        다음 Friend 등급까지 ${7800 - membership.mileage.onhqt}M 남았어요!
						                    </c:when>
						                    <c:when test="${membership.grade == 'Friend'}">
						                        다음 VI P등급까지 ${9600 - membership.mileage.onhqt}M 남았어요!
						                    </c:when>
						                    <c:when test="${membership.grade == 'VIP'}">
						                        다음 MVP 등급까지${12000 - membership.mileage.onhqt}M 남았어요!
						                    </c:when>
						                    <c:otherwise>
						                        연 12000M를 획득하시면 MVP등급이 유지됩니다.
						                    </c:otherwise>
						                </c:choose>
						            </div>
						            <div class="navigator">
						                <div class="circle">
						                    <div class="gradeCircle ${membership.grade == 'Welcome' ? 'active' : ''}"></div>
						                    <div>Welcome</div>
						                </div>
						                <div class="circle">
						                    <div class="gradeCircle ${membership.grade == 'Friend' ? 'active' : ''}"></div>
						                    <div>Friend</div>
						                </div>
						                <div class="circle">
						                    <div class="gradeCircle ${membership.grade == 'VIP' ? 'active' : ''}"></div>
						                    <div>VIP</div>
						                </div>
						                <div class="circle">
						                    <div class="gradeCircle ${membership.grade == 'MVP' ? 'active' : ''}"></div>
						                    <div>MVP</div>
						                </div>
						                <div class="line"></div>
						            </div>
						        </div>
						    </div><!-- cardBox two 끝 -->
						    <div class="cardBox three">
						        <div class="threeCon">
						            <div class="font20 fff">나의 쿠폰</div>
						            <div class="font20 mColor">${couponCount}</div>
						        </div>
						        <div class="threeCon">
						            <div class="font20 fff">나의 할인권</div>
						            <div class="font20 mColor">${discntCount}</div>
						        </div>
						        <div class="threeCon">
						            <div class="font20 fff">나의 포인트</div>
						            <div class="font20 mColor">${membership.point.onhqt}</div>
						        </div>
						    </div><!-- cardBox three 끝 -->
						</div><!-- topBoxes 끝 -->
						<div class="bottomBoxes"><!-- bottomBoxes 끝 -->
						    <div class="cardBox btLeft">
						        <div class="font16 fff">나의 예매내역</div>
						        <div class="listTable">
						            <div class="titleRow fff">
						                <div class="font20">영화명</div>
						                <div class="font20">상영일시</div>
						                <div class="font20">상영관</div>
						                <div class="font20">매수</div>
						                <div class="font20">상태</div>
						            </div>
						            <div id="ticketListContent">
						                <!-- 여기서 JavaScript로 데이터를 동적으로 채움 -->
						            </div>
						        </div><!-- listTable 끝 -->
								<div class="moreInfo">
						       		<a class="font16" href="${pageContext.request.contextPath}/mypage/ticket">더보기</a>
								</div>
						    </div><!-- btLeft 끝 -->
						    <div class="cardBox btRight">
						        <div class="font16 fff">나의 출석현황</div>
						        <div class="calendar font20">
						            <div class="calTitle fff">${currentYear}년 ${currentMonth}월</div>
						            <div class="weekdays fff">
						                <span class="mColor">S</span><span>M</span><span>T</span><span>W</span><span>T</span><span>F</span><span>S</span>
						            </div>
									<div class="days">
									    <!-- 빈 칸 추가: firstDayPosition 이전까지 빈칸 생성 -->
									    <c:forEach var="i" begin="1" end="${firstDayPosition}">
									        <div class="day empty"></div>
									    </c:forEach>

									    <!-- 실제 날짜 표시 -->
									    <c:forEach var="day" begin="1" end="${lastDayOfMonth}">
									        <div class="day" data-day="${day}">${day}</div>
									    </c:forEach>
									</div>
						        </div><!-- calendar 끝 -->
						    </div><!-- btRight 끝 -->
						</div><!-- bottomBoxes 끝 -->   
						
						<!-- csrf.token -->            
						<input type="hidden" id="token" name="${_csrf}" value="${_csrf.token}">
		  
							  



                    </div>  <!-- contentDiv 끝 -->  
                </div><!-- mainContent -->

                
            </div><!-- mainContainerContent -->
        </div> <!-- mainContainer -->
    </section>
	<%@ include file="/WEB-INF/views/footer.jsp" %>
</body>
</html>
