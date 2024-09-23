<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Header</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/benefit.css">
    <!-- import font-awesome, line-awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/line-awesome/1.3.0/line-awesome/css/line-awesome.min.css">
    <!-- import pretendard font -->
    <link rel="stylesheet" as="style" crossorigin href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.8/dist/web/variable/pretendardvariable.css"/>
    <!-- import js -->
    <script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
    <script src="js/index.js"></script>  


</head>
<body>
	<%@ include file="/WEB-INF/views/header.jsp" %>

    <!-- <header>
        <div class="headerInner">
            <h5 class="logo">
                <a href="#">
                    <img src="images/logo.svg" alt="#">
                </a>
            </h5>
            <ul class="menu">
                <li><a href="#">채용</a></li>
                <li><a href="#">인재풀</a></li>
                <li><a href="#">기업정보</a></li>
                <li><a href="#">커뮤니티</a></li>
                <li><a href="#">공지사항</a></li>
            </ul>
            <ul class="rightMenu">
                <li><a href="#">로그인</a></li>
                <li><a href="#">회원가입</a></li>
            </ul>
        </div>            
    </header> -->


        <section> <!-- section 시작-->
            <div class="wrap">     
                <div class="boonWrap">
                    <div class="boonlist">
                        <div class="boonl1">
                            <div class="boonimg">
                                <img src="../images/kakao.jpg" alt="" class="imgimg">
                            </div>
                            <div class="bo">
                                <div class="boontitle">
                                    <h5 class="bobo">카카오페이</h5>
                                </div>
                                <div class="booncontent">
                                    <pre class="bobobo">
제공혜택 : 
일반 영화 3천원 할인 (월 1회 이용가능)</pre>
                                </div>
                            </div>
                        </div>
                    </div> <!-- boonlist 끝 -->
                    <div class="boonlist">
                        <div class="boonl1">
                            <div class="boonimg">
                                <img src="../images/naver.jpg" alt="" class="imgimg">
                            </div>
                            <div class="bo">
                                <div class="boontitle">
                                    <h5 class="bobo">네이버페이</h5>
                                </div>
                                <div class="booncontent">
                                    <pre class="bobobo">
제공혜택 : 
일반 영화 2천원 할인 (월 2회 이용가능)</pre>
                                </div>
                            </div>
                        </div>
                    </div> <!-- boonlist 끝 -->


					<%@ include file="/WEB-INF/views/footer.jsp" %>

                </div> <!--loginWrap 끝-->
            </div> <!-- wrap 끝 -->
        </section> <!--section 끝 -->
    </div> <!-- logimg 끝-->


</body>
</html>

