<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MyMVP_회원탈퇴</title>
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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage/withdraw.css">
	<script src="${pageContext.request.contextPath}/js/mypage/withdraw.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/header.jsp" %>
	<section class="section">
	     <%@ include file="mypageHeader.jsp" %>
	       <div class="mainContainer">
	           <div class="mainContainerContent">
	               <div class="menuTitle">
	                   회원 탈퇴
	               </div>         
	               <div class="withdrawContent">
	                   <div class="contentTitle">
	                       <h3 class="title maincolor">MVP</h3>
	                       <h3 class="title">회원 탈퇴</h3>
	                   </div>
	                   <p>회원탈퇴를 할 경우 모든 정보가 삭제되며, 포인트도 사라지게 됩니다.</p>
	                   <p class="maincolor">탈퇴시 3일 이내 동일 회원정보로 재가입이 불가합니다.</p>
	                   <button class="withdrawButton maincolor" type="submit">회원탈퇴</button>
	               </div><!-- withdrawContent -->

	               <!-- pop 영역 -->
	               <div class="popUp">
	                   <div class="popBg">
	                       <div class="popCon disF flexD">
								<div class="popH">
					                <div class="popM">
					                    <h4 class="popTitle">정말로 탈퇴하시겠습니까?</h4>
					                    <h5 class="popAlert">모든 정보가 영구적으로 삭제됩니다..</h5>
					                </div>
					                <div class="mlauto">
					                    <span class="icon cancel fs24">
					                        <i class="fa-solid fa-xmark"></i>
					                    </span>
					                </div>
					            </div> <!-- popH 끝 -->
	                           <div class="popB">
	                               <form class="inputBox" method="post" id="withdrawForm">
	                                   <div class="inputCon">
										   <!-- CSRF Token -->
	                                       <input type="hidden" id="token" name="${_csrf}" value="${_csrf.token}"/>
										   <div class="check_font" id="del_check"></div>  
										   <input type="text" id="confirmationText" required placeholder="모든 정보가 영구적으로 삭제됨을 확인했습니다." />
	                                   </div>
	                                   <div class="sendButton">
	                                       <button class="submit" type="submit">예</button>
	                                       <button class="cancel" type="reset">아니오</button>
	                                   </div>
	                               </form>
	                           </div>    
	                       </div>
	                   </div> 
	               </div><!-- pop 영역 끝-->
	               </div><!-- mainContainerContent -->
	       </div> <!-- mainContainer -->
	   </section>
	   <%@ include file="/WEB-INF/views/footer.jsp" %>
</body>
</html>
