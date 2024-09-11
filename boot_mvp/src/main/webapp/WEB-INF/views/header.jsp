<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>헤더</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
<link rel="stylesheet" type="text/css" href='https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/static/pretendard.css'> 
<!-- import font-awesome, line-awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/line-awesome/1.3.0/line-awesome/css/line-awesome.min.css">
<!-- google font -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
<style>
    header,.subheader,.subadress {display: flex; justify-content: center; align-items: center; flex-direction: column; 	position: fixed; top: 0; width: 100vw; z-index: 9; background: #0F1015;}
    .headerInner,.headerSubInner, .headeradressInner { height: 90px; display: flex; align-items: center; min-width: 1600px!important; width: 90%!important; justify-content: start;}
    .logo { margin-right: 60px;}
    .menuWrap { display: flex; align-items: center; gap: 0 40px;}
    .menu, .menu a { color: #99A2B9; font-size: 16px; font-weight: 400; cursor:pointer;}
    .rightWrap { display: flex; align-items: center; gap: 0 20px; margin-left: auto;}
    .inputWrap { display: flex; align-items: center; position: relative;}
    .inputWrap input.searchInput { width: 250px; height: 36px; background: rgba(217,217,217,0.1); border:0; border-radius: 6px; padding-left: 12px; color: #99A2B9; }
    .inputWrap span.searchIcon { color: #99A2B9; position: absolute; right:12px; cursor: pointer; }
    .rightWrap button#login { height: 36px; background: rgba(217,217,217,0.1); padding: 0 20px; border:0; border-radius: 6px; color: #99A2B9; cursor: pointer;}
    .rightWrap button.login:hover { background: rgba(243,185,49,0.2); color: #F3B931;}
	.subheader { background: #1A1B24; border-bottom: 1px solid #1A1B24; display: none; position:fixed; top:90px;}
	.headerSubInner{ height: 40px; }
	.headerSubInner .menuWrap{ margin-left: 131px; display:none;}
	.dpF{ display: flex!important;}
</style>  
<!-- import js -->
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
</head>
<body>
    <header class="header" id="header">
        <div class="headerInner">
            <h5 class="logo">
                <a href="../main">
                    <img src="${pageContext.request.contextPath}/images/MVP.svg" alt="#">
                </a>
            </h5>
            <ul class="menuWrap">
				<li class="menu" data-target="movies">영화</li>
				<li class="menu" data-target="tickets">예매</li>
				<li class="menu">혜택</li>
                <!--<li class="menu">
                    <a href="">파트너 구하기</a>
                </li>-->
            </ul>
            <div class="rightWrap">
                <div class="inputWrap">
                    <input class="searchInput" type="search">
                    <span class="material-symbols-outlined searchIcon">search</span>
                </div>
                <button class="login" id="login" onclick="location.href='../login'">로그인</button>
            </div>
        </div>		
    </header>  
	<div class="subheader">
		<div class="headerSubInner">
			<ul class="menuWrap sub-menu" id="movies-menu">
				<li class="menu">
					<a href="../movie/movie">영화</a>	
				</li>
				<li class="menu">
					<a href="../movie/movieGenre">장르별 영화</a>	
				</li>
			</ul>
			<ul class="menuWrap sub-menu" id="tickets-menu">
				<li class="menu">
					<a href="#">예매1</a>	
				</li>
				<li class="menu">
					<a href="#">예매2</a>	
				</li>
			</ul>
		</div>
	</div> 
</body>
</html>
<script>
    var login = document.getElementById('login');
    login.addEventListener('mouseover',function(){
        login.setAttribute('class','active');
    });
    login.addEventListener('mouseout',function(){
        login.removeAttribute('class');
    });  
	
	// header subheader 작동
	var headerMenu = document.querySelectorAll('.headerInner .menuWrap .menu');
	var subHeader = document.querySelector('.subheader');
	var subMenus = document.querySelectorAll('.sub-menu');
	var currentTarget = null; // 현재 활성화된 메뉴를 추적

	headerMenu.forEach(function(menu) {
	    menu.addEventListener('click', function(event) {
	        var target = menu.getAttribute('data-target');
	        var targetMenu = document.getElementById(target + '-menu');

	        // Toggle subHeader visibility
	        if (currentTarget === target) 
			{
	            // 현재 메뉴가 다시 클릭된 경우, subHeader 숨기기
	            subHeader.classList.remove('dpF');
	            currentTarget = null; // 현재 활성화된 메뉴 초기화
	        } 
			else 
			{
	            // 다른 메뉴가 클릭된 경우, subHeader 표시 및 메뉴 업데이트
	            subHeader.classList.add('dpF');
	            subMenus.forEach(function(subMenu) 
				{
	                if (subMenu === targetMenu) 
					{
	                    subMenu.classList.add('dpF'); // 활성화된 메뉴 표시
	                } else {
	                    subMenu.classList.remove('dpF'); // 비활성화된 메뉴 숨기기
	                }
	            });
	            currentTarget = target; // 현재 활성화된 메뉴 업데이트
	        }
	    });
	});
	


	    
</script>