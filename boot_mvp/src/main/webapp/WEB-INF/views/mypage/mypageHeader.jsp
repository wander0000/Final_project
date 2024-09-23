<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MyMVP</title>
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
</head>
<style>

	.section
	{
	    display: flex;
	    flex-direction: column;
	    gap: 0 40px;
		margin-top: 90px;
		margin-bottom: 120px;
	}
	.subHeader 
	{
		background: #1A1B24;
		border-bottom: 1px solid #1A1B24;
    /*	osition: fixed;*/
		top: 90px;
	    height: 40px;
		width:100%
	}
	
	.headerSubInner
	{
		display: flex;
		align-items: center;
		min-width: 1600px !important;
		width: 90% !important;
		justify-content: start;
		margin-left: 131px;
	}

	.submenuList 
	{
	    display: flex;
	    gap: 0 20px;
	    color: rgba(153, 162, 185, 0.3);
	    font-size: 16px;
	    font-weight: 600;
	/*    margin-left: 131px;-*/
		color: #99A2B9;
		font-size: 16px;
		font-weight: 400;
		cursor: pointer;
	}


	a.active
	{
	    color: var(--sub-gray);
	    text-decoration: none;
	}
	
</style>
<body>
    
        <div class="subHeader">
			<div class="headerSubInner">
	            <ul class="submenuList">
	                <li class="submenu"><a href="${pageContext.request.contextPath}/mypage">My MVP</a></li>
	                <li class="submenu"><a href="${pageContext.request.contextPath}/mypage/ticket">예매관리</a></li>
	                <li class="submenu"><a href="${pageContext.request.contextPath}/mypage/membership">멤버십</a></li>
	                <li class="submenu"><a href="${pageContext.request.contextPath}/mypage/movieStory">무비스토리</a></li>
	                <li class="submenu"><a href="${pageContext.request.contextPath}/mypage/coupon">쿠폰/할인권</a></li>
	                <li class="submenu"><a href="${pageContext.request.contextPath}/mypage/userInfo">회원정보</a></li>
	                <li class="submenu"><a href="${pageContext.request.contextPath}/mypage/withdraw">회원탈퇴</a></li>
	            </ul>
			</div>
        </div>
		
</body>
</html>
<script>
	
	/*
       2024-09-01 서연주 
       선택한 메뉴 색상변경(클래스에 active추가)
   */
	// 모든 링크를 선택합니다.
	const links = document.querySelectorAll('.submenu a');

	// 페이지 로드 시 현재 URL과 일치하는 링크에 active 클래스 추가
	window.addEventListener('DOMContentLoaded', () => {
	    // 현재 경로에서 contextPath를 제외한 부분을 가져옵니다.
	    const currentPath = window.location.pathname.replace(`${pageContext.request.contextPath}`, '');
	    console.log("현재 경로:", currentPath);
	    
	    links.forEach(link => {
	        // 각 링크의 href에서 contextPath를 제외한 경로만 가져옵니다.
	        const linkPath = link.getAttribute('href').replace(`${pageContext.request.contextPath}`, '');
	        console.log("링크 경로:", linkPath);

	        // 현재 경로와 링크 경로가 일치하면 active 클래스 추가
	        if (currentPath === linkPath) {
	            link.classList.add('active');
	        }
	    });
	});

	// 각 링크에 클릭 이벤트를 추가합니다.
	
</script>
