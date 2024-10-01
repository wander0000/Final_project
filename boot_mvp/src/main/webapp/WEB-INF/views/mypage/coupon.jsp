<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MyMVP_쿠폰/할인권</title>
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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage/coupon.css">
	<script src="${pageContext.request.contextPath}/js/mypage/coupon.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/header.jsp" %>
    <section class="section">
       <%@ include file="mypageHeader.jsp" %>
        <div class="mainContainer">
            <div class="mainContainerContent">
                <h3 class="menuTitle">
                    쿠폰/할인권
                </h3>         
                <div class="mainContent">
                    <div class="tabConDiv">
                        <div class="tabCon coupon active" data-tab="coupon">쿠폰</div>
                        <div class="tabCon discount" data-tab="discount">할인권</div>
                    </div>
                   
                    <div class="contentDiv">

                        <div class="contentCon coupon active">
                            <div class="couponRegist">
                                <div class="conTitle">쿠폰등록</div>
								<form data-type="C" action="/generateCoupon" method="post">
									<div class="conBody">
										<!-- CSRF Token -->
	                                    <input type="hidden" id="token" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
								        <input class="inputReg" type="text" name="couponno" placeholder="쿠폰번호를 등록해주세요" required>
								        <button class="couponBtn">등록하기</button>
									</div>
							    </form>
                        	</div>
                            <div class="couponList">
                                <div class="filterBtn">
                                    <button class="filter" data-type="C" data-acrec="D" type="submit">사용완료</button>
                                    <button class="filter active" data-type="C" data-acrec="A" type="submit">미사용</button>
                                    <!-- <input type="button" value="사용완료"> -->
                                    <!-- <input type="button" value="미사용"> -->
                                </div>
                                <div class="listTable">
                                    <div class="titleRow">
                                        <div class="conTitle">쿠폰명</div>
                                        <div class="conTitle">혜택</div>
                                        <div class="conTitle">사용기간</div>
                                        <div class="conTitle">사용상태</div>
                                    </div>
                                    <div class="" id="couponListContent">
										<!-- 여기서 JavaScript로 데이터를 동적으로 채움 -->
                                    </div>
                                </div><!-- listTable 끝 -->
								<div id="pagination" class="pagination">
									<!-- 여기서 JavaScript로 데이터를 동적으로 채움 -->
								</div>
                            </div><!-- couponList 끝 -->
                            <div class="useGuide">
                                <div class="guideTitle">
                                    <span class="icon">
                                        <i class="fa-solid fa-circle-info"></i>
                                    </span>
                                    <p>쿠폰이용 안내</p>
                                </div>
                                <div class="guideCon">
                                    <p>· 등록하신 쿠폰은 영화 예매 시 결제수단 > 포인트/할인쿠폰 > 쿠폰에서 사용 가능합니다.</p>
                                    <p>· 특정 영화/상품/극장 전용 쿠폰일 경우, 지정 영화 및 상품, 극장에서 명시된 사용기간 내에만 사용이 가능합니다.</p>
                                    <p>· 매점 전용 쿠폰은 모바일앱을 통해 쿠폰을 매점에 제시 후 사용 가능합니다.</p>
                                    <p>· 온라인 전용으로 발행된 쿠폰의 경우에는 현장 사용이 불가능합니다.</p>
                                    <p>· 취소 시 현금 환불은 되지 않으며, 기존 쿠폰으로 유효기간 중에 재사용 하실 수 있습니다.</p>
                                    <p>· 기타 할인쿠폰에 대한 문의사항이 있으실 경우, 1544-3355 또는 홈페이지를 이용해 주시기 바랍니다.</p>
                                </div>
                            </div><!-- useGuide 끝 -->
                        </div><!-- contentCon coupon 끝 -->  


                        <div class="contentCon discount">
                            <div class="couponRegist">
                                <div class="conTitle" data-type="D">할인권등록</div>
								<form data-type="D" action="/generateCoupon" method="post">
									<div class="conBody">
										<!-- CSRF Token -->
	                                    <input type="hidden" id="token" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
								        <input class="inputReg" type="text" name="couponno" placeholder="쿠폰번호를 등록해주세요" required>
								        <button class="discntBtn">등록하기</button>
									</div>
							    </form>
                            </div>
                            <div class="couponList">
                                <div class="filterBtn">
                                    <button class="filter" data-type="D" data-acrec="D" type="submit">사용완료</button>
                                    <button class="filter" data-type="D" data-acrec="A" type="submit">사용가능</button>
                                </div>
                                <div class="listTable">
                                    <div class="titleRow">
                                        <div class="conTitle">할인권</div>
                                        <div class="conTitle">혜택</div>
                                        <div class="conTitle">사용기간</div>
                                        <div class="conTitle">사용상태</div>
                                    </div>
									<div class="" id="discntListContent">
										<!-- 여기서 JavaScript로 데이터를 동적으로 채움 -->
	                                   </div>
                                </div><!-- listTable 끝 -->
								<div id="pagination" class="pagination">
									<!-- 여기서 JavaScript로 데이터를 동적으로 채움 -->
								</div>
                            </div><!-- couponList 끝 -->
                            <div class="useGuide">
                                <div class="guideTitle">
                                    <span class="icon">
                                        <i class="fa-solid fa-circle-info"></i>
                                    </span>
                                    <p>쿠폰이용 안내</p>
                                </div>
                                <div class="guideCon">
                                    <p>· 등록하신 쿠폰은 영화 예매 시 결제수단 > 포인트/할인쿠폰 > 쿠폰에서 사용 가능합니다.</p>
                                    <p>· 특정 영화/상품/극장 전용 쿠폰일 경우, 지정 영화 및 상품, 극장에서 명시된 사용기간 내에만 사용이 가능합니다.</p>
                                    <p>· 매점 전용 쿠폰은 모바일앱을 통해 쿠폰을 매점에 제시 후 사용 가능합니다.</p>
                                    <p>· 온라인 전용으로 발행된 쿠폰의 경우에는 현장 사용이 불가능합니다.</p>
                                    <p>· 취소 시 현금 환불은 되지 않으며, 기존 쿠폰으로 유효기간 중에 재사용 하실 수 있습니다.</p>
                                    <p>· 기타 할인쿠폰에 대한 문의사항이 있으실 경우, 1544-3355 또는 홈페이지를 이용해 주시기 바랍니다.</p>
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
