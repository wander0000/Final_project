<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>푸터</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
<link rel="stylesheet" type="text/css" href='https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/static/pretendard.css'>  
<!-- google font -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
<style>
    footer { display: flex; justify-content: center; align-items: center; }
    .footerInner { display: flex; min-width: 1600px!important; width: 90%!important;height: 100%; flex-direction: column; padding: 0 0 60px; }
    .number a{ color: #99A2B9; }
    .time { margin-bottom: 14px; margin-top: 4px; } 
    .time a { font-weight: 600 !important;}
    .textWrap { display: flex; flex-direction: column; gap: 8px 0;}
    .textWrap > * a { color: #99A2B9; font-weight: 200;}
</style>  
<!-- import font-awesome, line-awesome -->
<!-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/board_detail.css"> -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/line-awesome/1.3.0/line-awesome/css/line-awesome.min.css">
<!-- import js -->
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
</head>
<body>
    <footer>
        <div class="footerInner">
            <h1 class="number">
                <a>1234-5678</a>
            </h1>
            <div class="textWrap">
                <h5 class="time">
                    <a>오전 9시 ~ 오후6시(토요일, 공휴일 휴무)</a>
                </h5> 
                <h5 class="address">
                    <a>부산광역시 부산진구 중앙대로 688 한준빌딩 2층</a>
                </h5> 
                <h5 class="ceo">
                    <a>대표이사 : MVP</a>
                </h5> 
                <h5 class="info">
                    <a>사업자등록번호 : 123-45-67890 / 통신판매업신고 : 9999-98765호</a>
                </h5> 
                <h5 class="email">
                    <a>이메일 : mvp@mvp.com</a>
                </h5> 
                <h5 class="right">
                    <a>Copyright ⓒ MVP ALL RIGHTS RESERVED.</a>
                </h5> 
            </div>            
        </div>
    </footer>
</body>
</html>