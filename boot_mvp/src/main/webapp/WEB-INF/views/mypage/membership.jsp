<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MyMVP_멤버십</title>
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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage/membership.css">
	<script src="${pageContext.request.contextPath}/js/mypage/membership.js"></script>
</head>
<body>
    <section class="section">
		<%@ include file="mypageHeader.jsp" %>
        <div class="mainContainer">
            <div class="mainContainerContent">
                <h3 class="menuTitle">
                    멤버십
                </h3>         
                <div class="mainContent">
                    <div class="tabConDiv">
                        <div class="tabCon membershipInfo active" data-tab="membershipInfo">멤버십 안내</div>
                        <div class="tabCon pointInfo" data-tab="pointInfo">포인트 적립 및 이용안내</div>
                        <div class="tabCon pointUsege" data-tab="pointUsege">포인트 이용 내역</div>
                    </div>
                   
                    <div class="contentDiv">

                        <div class="contentCon membershipInfo active">
                            <div class="membershipList">
                                <div class="membershipCard">
                                    <div class="cardTop">
                                        <span>
                                            <img class="welcome" src="${pageContext.request.contextPath}/images/welcome.svg" alt="#">
                                        </span>
                                        <div class="grade">Welcome</div>
                                    </div>
                                    <div class="cardM">
                                        <p>매표시 3% 포인트 적립</p>
                                        <p>원하는 영화를 포인트로 관람</p>
                                        <p>출석하면 매일 10 P / 한달 출석시 할인권 제공</p>
                                        <p>생일축하쿠폰 지급(주중관람관 1매)</p>
                                    </div>
                                    <div class="cardB" style="display: none;">
                                        <p> · 주중 관람권 10매</p>
                                        <p> · 주중/주말 관람권 2매</p>
                                    </div><!-- cardB 끝 -->
                                </div><!-- membershipCard 끝 -->
                                <div class="membershipCard">
                                    <div class="cardTop">
                                        <span>
                                            <img class="firend" src="${pageContext.request.contextPath}/images/friend.svg" alt="#">
                                        </span>
                                        <div class="grade">Friend</div>
                                    </div>
                                    <div class="cardM">
                                        <p>매표시 5% 포인트 적립</p>
                                        <p>원하는 영화를 포인트로 관람</p>
                                        <p>출석하면 매일 10 P / 한달 출석시 할인권 제공</p>
                                        <p>생일축하쿠폰 지급(주중관람관 1매)</p>
                                    </div>
                                    <div class="cardB">
                                        <p> · 주중 관람권 3매</p>
                                        <p> · 주중/주말 관람권 2매</p>
                                    </div><!-- cardB 끝 -->
                                </div><!-- membershipCard 끝 -->
                                <div class="membershipCard">
                                    <div class="cardTop">
                                        <span>
                                            <img class="vip" src="${pageContext.request.contextPath}/images/vip.svg" alt="#">
                                        </span>
                                        <div class="grade">VIP</div>
                                    </div>
                                    <div class="cardM">
                                        <p>매표시 7% 포인트 적립</p>
                                        <p>원하는 영화를 포인트로 관람</p>
                                        <p>출석하면 매일 10 P / 한달 출석시 할인권 제공</p>
                                        <p>생일축하쿠폰 지급(주중관람관 2매)</p>
                                    </div>
                                    <div class="cardB">
                                        <p> · 주중 관람권 6매</p>
                                        <p> · 주중/주말 관람권 2매</p>
                                    </div><!-- cardB 끝 -->
                                </div><!-- membershipCard 끝 -->
                                <div class="membershipCard">
                                    <div class="cardTop">
                                        <span>
                                            <img class="mvp" src="${pageContext.request.contextPath}/images/mvp.svg" alt="#">
                                        </span>
                                        <div class="grade">MVP</div>
                                    </div>
                                    <div class="cardM">
                                        <p>매표시 10% 포인트 적립</p>
                                        <p>원하는 영화를 포인트로 관람</p>
                                        <p>출석하면 매일 10 P / 한달 출석시 할인권 제공</p>
                                        <p>생일축하쿠폰 지급(주중관람관 2매)</p>
                                    </div>
                                    <div class="cardB">
                                        <p> · 주중 관람권 10매</p>
                                        <p> · 주중/주말 관람권 2매</p>
                                    </div><!-- cardB 끝 -->
                                </div><!-- membershipCard 끝 -->
                            </div><!-- membershipList 끝 -->
                            <div class="useGuide">
                                <div class="guideTitle">
                                    <p>등급 선정 및 유지 기준</p>
                                </div>
                                <div class="guideCon">
                                    <div class="row1">
                                        <div class="firstColumn"></div>
                                        <div class="gridBox">
                                            <button class="gradeBtn" type="menu">Welcome</button>
                                            <button class="gradeBtn" type="menu">Friend</button>
                                            <button class="gradeBtn" type="menu">VIP</button>
                                            <button class="gradeBtn" type="menu">MVP</button>
                                        </div>
                                    </div>
                                    <div class="row2">
                                        <div class="standard firstColumn">선정</div>
                                        <div class="gridBox">
                                            <div class="standard">가입시</div>
                                            <div class="standard">7800마일리지 달성</div>
                                            <div class="standard">Friend등급 1년 유지시</div>
                                            <div class="standard">VIP등급 3년 유지시</div>
                                        </div>
                                    </div>
                                    <div class="row3">
                                        <div class="mileage firstColumn">유지(연)</div>
                                        <div class="gridBox">
                                            <div class="mileage">-</div>
                                            <div class="mileage">7800마일리지</div>
                                            <div class="mileage">9600마일리지</div>
                                            <div class="mileage">12000마일리지</div>
                                        </div>
                                    </div>
                                </div>
                            </div><!-- useGuide 끝 -->
                        </div><!-- contentCon membershipInfo 끝 -->  


                        <div class="contentCon pointInfo">
                           
                            <div class="cardCon">
                                <div class="cardDetail">
                                    <div class="iconBox">
                                        <img src="${pageContext.request.contextPath}/images/stars.svg" alt="#">
                                    </div>
                                    <div class="rightCon">
                                        <div class="cardTitle">포인트 적립</div>
                                        <div class="cardBody">매표시  3~10% 적립(멤버십에 따라 차등)</div>
                                    </div>
                                </div><!-- cardDetail 끝 -->
                                <div class="cardDetail">
                                    <div class="iconBox">
                                        <img src="${pageContext.request.contextPath}/images/check.svg" alt="#">
                                    </div>
                                    <div class="rightCon">
                                        <div class="cardTitle">출석포인트</div>
                                        <div class="cardBody">매일 10 Point / 한달 출석시 할인권 제공</div>
                                    </div>
                                </div><!-- cardDetail 끝 -->
                                <div class="cardDetail">
                                    <div class="iconBox">
                                        <img src="${pageContext.request.contextPath}/images/cake.svg" alt="#">
                                    </div>
                                    <div class="rightCon">
                                        <div class="cardTitle">생일축하쿠폰</div>
                                        <div class="cardBody">회원이라면 누구나 영화관람권을 선물로!</div>
                                    </div>
                                </div><!-- cardDetail 끝 -->
                                <div class="cardDetail">
                                    <div class="iconBox">
                                        <img src="${pageContext.request.contextPath}/images/gift.svg" alt="#">
                                    </div>
                                    <div class="rightCon">
                                        <div class="cardTitle">포인트 사용</div>
                                        <div class="cardBody">원하는 영화를 포인트로 관람할 수 있어요!</div>
                                    </div>
                                </div><!-- cardDetail 끝 -->
                            </div><!-- cardCon 끝 -->
                            <div class="useGuide">
                                <div class="guideTitle">
                                    <span class="icon">
                                        <i class="fa-solid fa-circle-info"></i>
                                    </span>
                                    <p>세부사항 안내</p>
                                </div>
                                <div class="guideCon point">
                                    <div class="details">
                                        <p>① 영화 예매 시</p>
                                        <p>MVP 온라인(모바일, 홈페이지)을 통한 티켓 구매 시 또는 MVP 전 극장에서 멤버십 회원이 티켓을 구매하면서 MVP 회원 바코드를 제시한 경우, 유료영화관람금액(실 결제 금액)에 영화 예매 시점에 해당하는 MVP 포인트를 적립할 수 있습니다.</p>
                                        <p>포인트 적립은 유료 구매 시에만 가능하며, 상영일 익일에 적립 및 내역 확인 가능합니다.</p>
                                        <p>② 공통</p>
                                        <p>MVP 포인트 적립은 구매 당시에만 가능하며, 사후 적립은 불가합니다. 적립된 포인트의 유효기간은 적립월 기준 2년간이며, 적립된 MVP 포인트가 1,000포인트 이상이 될 경우 전국MVP에서 매표 시 1포인트를 1원으로 현금처럼 사용할 수 있습니다. MVP 온라인 예매는 1,000포인트 이상 10포인트 단위로 사용가능하며, 극장에서 티켓을 구매 시에는 500포인트 단위로 사용 가능합니다.</p>
                                    </div>
                                    <div class="details">
                                        <p>MVP E 포인트 적립 규모의 한도는 없으나, 예매/적립 횟수가 아래와 같이 제한되며 초과 시 MVP 포인트가 적립되지 않습니다.</p>
                                        <p>- 매표: 예매/적립 횟수 1일 10회(현장 및 온라인) 또는 예매/적립 매수 1일 24매(온라인 예매)</p>
                                        <p>  (2매씩 예매 시 10회 20매까지 가능, 8매씩 예매 시 3회 24매까지 가능하며, 단체 관람을 통해 할인을 받을 경우 포인트는 적립되지 않습니다.)</p>
                                    </div>
                                    <p>결제 시 사용한MVP 포인트, 쿠폰 할인 금액은MVP 포인트가 적립되지 않습니다.  일부 제휴 관람권의 적립은 제휴사와의 계약에 따라 적립률이 정해집니다. 세부 적립률은 제휴사의 서비스 약관 등을 통해 안내합니다. 일부 제휴 포인트의 경우 MVP 포인트와 중복 적립되지 않으며 적립이 제한될 경우 제휴사 홈페이지 등에서 안내합니다.</p>
                                </div>
                            </div><!-- useGuide 끝 -->
                        </div><!-- contentCon pointInfo 끝 -->  



                        <div class="contentCon pointUsege">
                           
                            <div class="cardBoxes">
                                <div class="cardBox left">
                                    <div class="cardUp">
                                        <div class="pointTitle">사용가능 포인트</div>                             
                                        <div class="pointTotal">${membership.point.onhqt} P</div>
                                    </div>
                                    <div class="cardDown">
                                        <div class="point1">
                                            <div>총 적립</div>
											<div>
												<c:choose>
										            <c:when test="${empty membership.point.recqt}">
										               - P
										            </c:when>
										            <c:otherwise>
										               ${membership.point.recqt} P
										            </c:otherwise>
										        </c:choose>
											</div>
                                        </div>                             
                                        <div class="point2">
                                            <div>총 사용</div>
											<div>
												<c:choose>
										            <c:when test="${empty membership.point.issqt}">
										               - P
										            </c:when>
										            <c:otherwise>
										               ${membership.point.issqt} P
										            </c:otherwise>
										        </c:choose>
											</div>
                                        </div>                             
                                    </div>
                                </div><!-- cardDetail 끝 -->
                                <div class="cardBox alignCnt">
                                    <div class="cardUp">
                                        <div class="pointTitle">멤버십 누적 마일리지</div>                             
                                        <div class="pointTotal">${membership.mileage.onhqt} M</div>
                                    </div>
                                    <div class="cardDown">
                                        <div class="point1">
                                            <div>당월 적립</div>
                                            <div>
												<c:choose>
										            <c:when test="${empty membership.mileage.recqt}">
										               - M
										            </c:when>
										            <c:otherwise>
										               ${membership.mileage.recqt} M
										            </c:otherwise>
										        </c:choose>
											</div>
                                        </div>                             
										<div class="point2">
										    <div>승급까지 남은 포인트</div>
										    <div>
										        <c:choose>
										            <c:when test="${membership.grade == 'Welcome'}">
										                ${7800 - membership.mileage.onhqt} M
										            </c:when>
										            <c:when test="${membership.grade == 'Friend'}">
										                ${9600 - membership.mileage.onhqt} M
										            </c:when>
										            <c:when test="${membership.grade == 'VIP'}">
										                ${12000 - membership.mileage.onhqt} M
										            </c:when>
										            <c:otherwise>
										            	- M  
										            </c:otherwise>
										        </c:choose>
										    </div>
										</div>
                                    </div>
                                </div><!-- cardDetail 끝 -->
                                <div class="cardBox">
                                    <div class="cardU">
                                        <div class="pointTitle">현재 등급</div>                             
                                        <div class="pointTotal">${membership.grade}</div>
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
                                </div><!-- cardDetail 끝 -->
                            </div><!-- cardBox 끝 -->
                            <div class="usegeList">
                                <div class="filterBtn">
                                    <div class="leftFilter">
                                        <div class="filterTitle">기간별 조회</div>
										<button class="filter" data-days="15">15일</button>
									   	<button class="filter" data-days="30">1개월</button>
									   	<button class="filter" data-days="60">2개월</button>
									   	<button class="filter" data-days="90">3개월</button>
                                    </div>
                                    <div class="rightFilter">
                                        <div class="customDateWrapper">
											<label for="startDate">시작일:</label>
                                            <input class="startDate" type="date">
                                        </div>
                                        <div class="customDateWrapper">
											<label for="endDate">종료일:</label>
                                            <input class="endDate" type="date">
                                        </div>
                                        <button class="submitBtn">조회</button>
                                    </div><!-- rightFilter 끝 -->
                                </div>
                                <div class="listTable">
                                    <div class="titleRow">
                                        <div class="conTitle">일자</div>
                                        <div class="conTitle">구분</div>
                                        <div class="conTitle">내용</div>
                                        <div class="conTitle">포인트</div>
                                    </div>
									<div id="pointHistoryContent">
										<!-- 여기서 JavaScript로 데이터를 동적으로 채움 -->
									</div>
                                </div><!-- listTable 끝 -->
								<div id="pagination" class="pagination">
									<!-- 여기서 JavaScript로 데이터를 동적으로 채움 -->
								</div>
                            </div><!-- usegeList 끝 -->





                        </div><!-- contentCon pointUsege 끝 -->  



                    </div>  <!-- contentDiv 끝 -->  
                </div><!-- mainContent -->

                
            </div><!-- mainContainerContent -->
        </div> <!-- mainContainer -->
    </section>
</body>
</html>
