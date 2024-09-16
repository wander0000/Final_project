<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MyMVP_예매관리</title>
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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage/ticket.css">
	<script src="${pageContext.request.contextPath}/js/mypage/ticket.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/header.jsp" %>
    <section class="section">
       <%@ include file="mypageHeader.jsp" %>
        <div class="mainContainer">
            <div class="mainContainerContent">
                <h3 class="menuTitle">
                    예매관리
                </h3>         
                <div class="mainContent">
                    <div class="tabConDiv">
                        <div class="tabCon booking active" data-tab="booking">예매 안내</div>
                        <div class="tabCon ticketCancel" data-tab="ticketCancel">예매 취소</div>
                    </div>
                   
                    <div class="contentDiv">

                        <div class="contentCon booking active">
                            <div class="bookingList">
                                <div class="filterBtn">
                                    <div class="leftFilter">
                                        <div class="filterTitle">기간별 조회</div>
											<button class="filter" data-days="15">15일</button>
										   	<button class="filter" data-days="30">1개월</button>
										   	<button class="filter" data-days="60">2개월</button>
										   	<button class="filter" data-days="90">3개월</button>
                                    </div>
                                    <div class="rightFilter">
                                        <div class="filterTitle">월별 조회</div>
                                        <select id="orderBy" name="orderBy" class="custom-select">
                                            <option disabled selected>선택</option>
                                            <option value="예매일">예매일</option>
                                            <option value="상영일">상영일</option>
                                            <!-- <option value="bookingday" <c:if test="${pageMaker.cri.orderBy == 'bookingday'}">selected='selected'</c:if>>예매일</option> -->
                                            <!-- <option value="showingday" <c:if test="${pageMaker.cri.orderBy == 'showingday'}">selected='selected'</c:if>>상영일</option> -->
                                        </select>
                                        <select id="yearSelect" name="year" class="custom-select">
                                            <option disabled selected>연도</option>
                                            <!-- JavaScript로 동적으로 옵션 추가 예정 --> 
                                        </select>
                                        <select id="monthSelect" name="month" class="custom-select">
                                            <option disabled selected>월</option>
                                            <option value="1">1월</option>
                                            <option value="2">2월</option>
                                            <option value="3">3월</option>
                                            <option value="4">4월</option>
                                            <option value="5">5월</option>
                                            <option value="6">6월</option>
                                            <option value="7">7월</option>
                                            <option value="8">8월</option>
                                            <option value="9">9월</option>
                                            <option value="10">10월</option>
                                            <option value="11">11월</option>
                                            <option value="12">12월</option>
                                        </select>
                                        <button class="submitBtn" type="submit">조회</button>
                                    </div><!-- rightFilter 끝 -->
                                </div>
                                <div class="listTable">
                                    <div class="titleRowBook">
                                        <div class="conTitle">예매번호</div>
                                        <div class="conTitle">영화명</div>
                                        <div class="conTitle">상영일시</div>
                                        <div class="conTitle">매수</div>
                                        <div class="conTitle">취소가능일</div>
                                        <div class="conTitle">상태</div>
                                        <div class="conTitle">입장권QR</div>
                                    </div>
                                    <div class="contentRowBookList" id="ticketListContent">
										<!-- 여기서 JavaScript로 데이터를 동적으로 채움 -->
                                    </div><!-- contentRowBookList 끝 -->
                                </div><!-- listTable 끝 -->
								<div id="pagination" class="pagination">
									<!-- 여기서 JavaScript로 데이터를 동적으로 채움 -->
								</div>
                            </div><!-- bookingList 끝 -->
                            <div class="useGuide">
                                <div class="guideTitle">
                                    <span class="icon">
                                        <i class="fa-solid fa-circle-info"></i>
                                    </span>
                                    <p>예매 안내</p>
                                </div>
                                <div class="guideCon">
                                    <p>[예매 안내].</p>
                                    <p> · 만 4세(48개월) 이상부터는 영화티켓을 반드시 구매하셔야 입장 가능합니다.</p>
                                    <p> · 예매 변경은 불가능하며, 취소 후 재 예매를 하셔야만 합니다.</p>
                                    <p> · 메가박스 모바일앱을 이용할 경우 티켓출력없이 모바일티켓을 통해 바로 입장하실 수 있습니다.</p>
                                    <p>[티켓교환 안내]</p>
                                    <p> · 극장의 무인발권기(KIOSK)를 통해 예매번호 또는 고객확인번호(생년월일+휴대폰번호)를 입력하여 편리하게 티켓을 발권하실 수 있습니다.</p>
                                    <p> · 무인발권기 이용이 어려우신경우, 티켓교환권을 출력하여 매표소에 방문하시면 티켓을 발권하실 수 있습니다.</p>
                                    <p>   (티켓교환권 출력이 어려운경우 예매번호와 신분증을 지참하여 매표소에 방문하시면 티켓을 발권하실 수 있습니다) </p>
                                </div>
                            </div><!-- useGuide 끝 -->
                        </div><!-- contentCon coupon 끝 -->  


                        <div class="contentCon ticketCancel">
                           
                            <div class="cancelList">
                                <p>상영일 기준 7일간 취소내역을 확인하실 수있습니다.</p>
                                <div class="listTable">
                                    <div class="titleRowCancel">
                                        <div class="conTitle">취소일시</div>
                                        <div class="conTitle">영화명</div>
                                        <div class="conTitle">상영일시</div>
                                        <div class="conTitle">취소금액</div>
                                    </div>
									<div id="cancelticketList">
										<!-- 여기서 JavaScript로 데이터를 동적으로 채움 -->
									</div>
                                   
                                </div><!-- listTable 끝 -->
                            </div><!-- couponList 끝 -->
                            <div class="useGuide">
                                <div class="guideTitle">
                                    <span class="icon">
                                        <i class="fa-solid fa-circle-info"></i>
                                    </span>
                                    <p>예매취소 안내</p>
                                </div>
                                <div class="guideCon">
                                    <p>· 온라인(홈페이지/모바일) 예매 취소는 상영시간 20분전까지 입니다.</p>
                                    <p>· 위탁 예매 사이트 이용 시 취소 및 환불 규정은 해당 사이트 규정을 따릅니다.</p>
                                    <p>· 발권된 티켓은 상영시간 전까지 현장 방문 시에만 취소가 가능합니다.</p>
                                </div>
                            </div><!-- useGuide 끝 -->
                        </div><!-- contentCon discount 끝 -->  



                    </div>  <!-- contentDiv 끝 -->  
                </div><!-- mainContent -->

                
            </div><!-- mainContainerContent -->
        </div> <!-- mainContainer -->
    </section>
	<%@ include file="/WEB-INF/views/footer.jsp" %>
</body>
</html>
