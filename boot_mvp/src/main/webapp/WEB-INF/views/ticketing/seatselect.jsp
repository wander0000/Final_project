<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ticketing/seatselect.css">
<script src="${pageContext.request.contextPath}/js/ticketing/ticketing_fn.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
</head>
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
                        <div class="tab">
                            <a href="">01.상영시간</a>
                        </div>
                        <div class="tab active">
                            <a href="">02.인원/좌석</a>
                        </div>
                        <div class="tab">
                            <a href="">03.결제</a>
                        </div>
                        <div class="tab">
                            <a href="">04.결제완료</a>
                        </div>                    
                    </div>
                    <h5 class="sideTxt">
                        <a href="#">·인원은 최대 8명까지 선택 가능합니다.</a>
                    </h5>
                </div> <!-- tabDiv 끝-->
                <div class="selectDiv">
                    <div class="movieInfo">
                        <img src="img/Alien.jpg" alt="">
                            <div class="movieR">
                                <div class="movieRT">
                                    <img src="img/grade_15.png" alt="#">
                                    <h5 class="movieName">
                                        <a href="#none">에이리언:로물루스</a>
                                    </h5>
                                </div>
                                <div class="movieRM">
                                    <h5 class="movieDate"><a href="#none">24.08.21</a></h5>
                                    <h5 class="movieDay"><a href="#none">(수)</a></h5>
                                    <h5 class="movieTime"><a href="#none">&nbsp;| 10:10 ~ 12:44</a></h5>
                                </div>
                                <div class="movieRB">
                                    <h5 class="movieLocation">
                                        <a href="#none">센텀시티</a>
                                    </h5>
                                </div>
                            </div> <!-- movieR 끝-->
                    </div> <!-- movieInfo 끝-->
                    <div class="selectInfo">
                        <div class="selectWrap adult">
                            <h5 class="title">성인</h5>
                            <span class="btn_num">
                                <button type="button" class="btn_mins">-</button>
                                    <div class="txt_num">0</div>
                                    <!-- <input type="number" value="0"> -->
                                <button type="button" class="btn_plus">+</button>
                            </span>
                        </div>
                        <div class="selectWrap student">
                            <h5 class="title">청소년</h5>
                            <div class="count">
                                <button type="button" class="down">-</button>
                                <div class="number">
                                    <button type="button" class="now">0</button>
                                    <ul class="num-choice">
                                        <li>
                                            <button type="button" class="btn on">0</button>
                                        </li>
                                    </ul>
                                </div>
                                <button type="button" class="up">+</button>
                            </div>
                        </div>
                        <div class="selectWrap eld">
                            <h5 class="title">경로</h5>
                            <input type="number">
                        </div>
                        <div class="selectWrap disabled">
                            <h5 class="title">장애인</h5>
                            <input type="number">
                        </div>
                    </div>
                </div> <!-- selectDiv 끝-->
                <div class="screenDiv">
                    <h5 class="screenTitle">
                        <a href="#">SCREEN</a>
                    </h5>
                    <div class="screenWrap">
                        <div class="sitWrap">
                            <div class="sitCol">
                                <h5 class="sitNum">A</h5>
                                <div class="sitDiv">
                                    <input type="text" class="sit" value="1" disabled>
                                    <input type="text" class="sit" value="2" disabled>
                                    <input type="text" class="sit" value="3" disabled>
                                    <input type="text" class="sit" value="4" disabled>
                                    <input type="text" class="sit" value="5" disabled>
                                    <input type="text" class="sit" value="6" disabled>
                                    <input type="text" class="sit" value="7" disabled>
                                    <input type="text" class="sit" value="8" disabled>
                                    <input type="text" class="sit" value="9" disabled>
                                    <input type="text" class="sit" value="10" disabled>
                                    <input type="text" class="sit" value="11" disabled>
                                    <input type="text" class="sit" value="12" disabled>
                                    <input type="text" class="sit" value="13" disabled>
                                    <input type="text" class="sit" value="14" disabled>
                                </div> <!-- sitDiv-->                               
                            </div>  <!-- sitCol -->    
                            <div class="sitCol">
                                <h5 class="sitNum">A</h5>
                                <div class="sitDiv">
                                    <input type="text" class="sit" value="1" disabled>
                                    <input type="text" class="sit" value="2" disabled>
                                    <input type="text" class="sit" value="3" disabled>
                                    <input type="text" class="sit" value="4" disabled>
                                    <input type="text" class="sit" value="5" disabled>
                                    <input type="text" class="sit" value="6" disabled>
                                    <input type="text" class="sit" value="7" disabled>
                                    <input type="text" class="sit" value="8" disabled>
                                    <input type="text" class="sit" value="9" disabled>
                                    <input type="text" class="sit" value="10" disabled>
                                    <input type="text" class="sit" value="11" disabled>
                                    <input type="text" class="sit" value="12" disabled>
                                    <input type="text" class="sit" value="13" disabled>
                                    <input type="text" class="sit" value="14" disabled>
                                </div> <!-- sitDiv-->                               
                            </div>  <!-- sitCol --> 
                            <div class="sitCol">
                                <h5 class="sitNum">A</h5>
                                <div class="sitDiv">
                                    <input type="text" class="sit" value="1" disabled>
                                    <input type="text" class="sit" value="2" disabled>
                                    <input type="text" class="sit" value="3" disabled>
                                    <input type="text" class="sit" value="4" disabled>
                                    <input type="text" class="sit" value="5" disabled>
                                    <input type="text" class="sit" value="6" disabled>
                                    <input type="text" class="sit" value="7" disabled>
                                    <input type="text" class="sit" value="8" disabled>
                                    <input type="text" class="sit" value="9" disabled>
                                    <input type="text" class="sit" value="10" disabled>
                                    <input type="text" class="sit" value="11" disabled>
                                    <input type="text" class="sit" value="12" disabled>
                                    <input type="text" class="sit" value="13" disabled>
                                    <input type="text" class="sit" value="14" disabled>
                                </div> <!-- sitDiv-->                               
                            </div>  <!-- sitCol --> 
                            <div class="sitCol">
                                <h5 class="sitNum">A</h5>
                                <div class="sitDiv">
                                    <input type="text" class="sit" value="1" disabled>
                                    <input type="text" class="sit" value="2" disabled>
                                    <input type="text" class="sit" value="3" disabled>
                                    <input type="text" class="sit" value="4" disabled>
                                    <input type="text" class="sit" value="5" disabled>
                                    <input type="text" class="sit" value="6" disabled>
                                    <input type="text" class="sit" value="7" disabled>
                                    <input type="text" class="sit" value="8" disabled>
                                    <input type="text" class="sit" value="9" disabled>
                                    <input type="text" class="sit" value="10" disabled>
                                    <input type="text" class="sit" value="11" disabled>
                                    <input type="text" class="sit" value="12" disabled>
                                    <input type="text" class="sit" value="13" disabled>
                                    <input type="text" class="sit" value="14" disabled>
                                </div> <!-- sitDiv-->                               
                            </div>  <!-- sitCol --> 
                            <div class="sitCol">
                                <h5 class="sitNum">A</h5>
                                <div class="sitDiv">
                                    <input type="text" class="sit" value="1" disabled>
                                    <input type="text" class="sit" value="2" disabled>
                                    <input type="text" class="sit" value="3" disabled>
                                    <input type="text" class="sit" value="4" disabled>
                                    <input type="text" class="sit" value="5" disabled>
                                    <input type="text" class="sit" value="6" disabled>
                                    <input type="text" class="sit" value="7" disabled>
                                    <input type="text" class="sit" value="8" disabled>
                                    <input type="text" class="sit" value="9" disabled>
                                    <input type="text" class="sit" value="10" disabled>
                                    <input type="text" class="sit" value="11" disabled>
                                    <input type="text" class="sit" value="12" disabled>
                                    <input type="text" class="sit" value="13" disabled>
                                    <input type="text" class="sit" value="14" disabled>
                                </div> <!-- sitDiv-->                               
                            </div>  <!-- sitCol --> 
                            <div class="sitCol">
                                <h5 class="sitNum">A</h5>
                                <div class="sitDiv">
                                    <input type="text" class="sit" value="1" disabled>
                                    <input type="text" class="sit" value="2" disabled>
                                    <input type="text" class="sit" value="3" disabled>
                                    <input type="text" class="sit" value="4" disabled>
                                    <input type="text" class="sit" value="5" disabled>
                                    <input type="text" class="sit" value="6" disabled>
                                    <input type="text" class="sit" value="7" disabled>
                                    <input type="text" class="sit" value="8" disabled>
                                    <input type="text" class="sit" value="9" disabled>
                                    <input type="text" class="sit" value="10" disabled>
                                    <input type="text" class="sit" value="11" disabled>
                                    <input type="text" class="sit" value="12" disabled>
                                    <input type="text" class="sit" value="13" disabled>
                                    <input type="text" class="sit" value="14" disabled>
                                </div> <!-- sitDiv-->                               
                            </div>  <!-- sitCol --> 
                            <div class="sitCol">
                                <h5 class="sitNum">A</h5>
                                <div class="sitDiv">
                                    <input type="text" class="sit" value="1" disabled>
                                    <input type="text" class="sit" value="2" disabled>
                                    <input type="text" class="sit" value="3" disabled>
                                    <input type="text" class="sit" value="4" disabled>
                                    <input type="text" class="sit" value="5" disabled>
                                    <input type="text" class="sit" value="6" disabled>
                                    <input type="text" class="sit" value="7" disabled>
                                    <input type="text" class="sit" value="8" disabled>
                                    <input type="text" class="sit" value="9" disabled>
                                    <input type="text" class="sit" value="10" disabled>
                                    <input type="text" class="sit" value="11" disabled>
                                    <input type="text" class="sit" value="12" disabled>
                                    <input type="text" class="sit" value="13" disabled>
                                    <input type="text" class="sit" value="14" disabled>
                                </div> <!-- sitDiv-->                               
                            </div>  <!-- sitCol --> 
                            <div class="sitCol">
                                <h5 class="sitNum">A</h5>
                                <div class="sitDiv">
                                    <input type="text" class="sit" value="1" disabled>
                                    <input type="text" class="sit" value="2" disabled>
                                    <input type="text" class="sit" value="3" disabled>
                                    <input type="text" class="sit" value="4" disabled>
                                    <input type="text" class="sit" value="5" disabled>
                                    <input type="text" class="sit" value="6" disabled>
                                    <input type="text" class="sit" value="7" disabled>
                                    <input type="text" class="sit" value="8" disabled>
                                    <input type="text" class="sit" value="9" disabled>
                                    <input type="text" class="sit" value="10" disabled>
                                    <input type="text" class="sit" value="11" disabled>
                                    <input type="text" class="sit" value="12" disabled>
                                    <input type="text" class="sit" value="13" disabled>
                                    <input type="text" class="sit" value="14" disabled>
                                </div> <!-- sitDiv-->                               
                            </div>  <!-- sitCol --> 
                            <div class="sitCol">
                                <h5 class="sitNum">A</h5>
                                <div class="sitDiv">
                                    <input type="text" class="sit" value="1" disabled>
                                    <input type="text" class="sit" value="2" disabled>
                                    <input type="text" class="sit" value="3" disabled>
                                    <input type="text" class="sit" value="4" disabled>
                                    <input type="text" class="sit" value="5" disabled>
                                    <input type="text" class="sit" value="6" disabled>
                                    <input type="text" class="sit" value="7" disabled>
                                    <input type="text" class="sit" value="8" disabled>
                                    <input type="text" class="sit" value="9" disabled>
                                    <input type="text" class="sit" value="10" disabled>
                                    <input type="text" class="sit" value="11" disabled>
                                    <input type="text" class="sit" value="12" disabled>
                                    <input type="text" class="sit" value="13" disabled>
                                    <input type="text" class="sit" value="14" disabled>
                                </div> <!-- sitDiv-->                               
                            </div>  <!-- sitCol --> 
                            <div class="sitCol">
                                <h5 class="sitNum">A</h5>
                                <div class="sitDiv">
                                    <input type="text" class="sit" value="1" disabled>
                                    <input type="text" class="sit" value="2" disabled>
                                    <input type="text" class="sit" value="3" disabled>
                                    <input type="text" class="sit" value="4" disabled>
                                    <input type="text" class="sit" value="5" disabled>
                                    <input type="text" class="sit" value="6" disabled>
                                    <input type="text" class="sit" value="7" disabled>
                                    <input type="text" class="sit" value="8" disabled>
                                    <input type="text" class="sit" value="9" disabled>
                                    <input type="text" class="sit" value="10" disabled>
                                    <input type="text" class="sit" value="11" disabled>
                                    <input type="text" class="sit" value="12" disabled>
                                    <input type="text" class="sit" value="13" disabled>
                                    <input type="text" class="sit" value="14" disabled>
                                </div> <!-- sitDiv-->                               
                            </div>  <!-- sitCol --> 
                            <div class="sitCol">
                                <h5 class="sitNum">A</h5>
                                <div class="sitDiv">
                                    <input type="text" class="sit" value="1" disabled>
                                    <input type="text" class="sit" value="2" disabled>
                                    <input type="text" class="sit" value="3" disabled>
                                    <input type="text" class="sit" value="4" disabled>
                                    <input type="text" class="sit" value="5" disabled>
                                    <input type="text" class="sit" value="6" disabled>
                                    <input type="text" class="sit" value="7" disabled>
                                    <input type="text" class="sit" value="8" disabled>
                                    <input type="text" class="sit" value="9" disabled>
                                    <input type="text" class="sit" value="10" disabled>
                                    <input type="text" class="sit" value="11" disabled>
                                    <input type="text" class="sit" value="12" disabled>
                                    <input type="text" class="sit" value="13" disabled>
                                    <input type="text" class="sit" value="14" disabled>
                                </div> <!-- sitDiv-->                               
                            </div>  <!-- sitCol --> 
                            <div class="sitCol">
                                <h5 class="sitNum">A</h5>
                                <div class="sitDiv">
                                    <input type="text" class="sit" value="1" disabled>
                                    <input type="text" class="sit" value="2" disabled>
                                    <input type="text" class="sit" value="3" disabled>
                                    <input type="text" class="sit" value="4" disabled>
                                    <input type="text" class="sit" value="5" disabled>
                                    <input type="text" class="sit" value="6" disabled>
                                    <input type="text" class="sit" value="7" disabled>
                                    <input type="text" class="sit" value="8" disabled>
                                    <input type="text" class="sit" value="9" disabled>
                                    <input type="text" class="sit" value="10" disabled>
                                    <input type="text" class="sit" value="11" disabled>
                                    <input type="text" class="sit" value="12" disabled>
                                    <input type="text" class="sit" value="13" disabled>
                                    <input type="text" class="sit" value="14" disabled>
                                </div> <!-- sitDiv-->                               
                            </div>  <!-- sitCol -->                                                                     
                        </div>
                    </div> <!-- screenWrap 끝 -->
                </div> <!-- screenDiv 끝 -->
                <div class="guideWrap">
                    <div class="guide">
                        <img src="img/ic_seat_type9.png" alt="">
                        <a href="#none">장애인석</a>
                    </div>
                    <div class="guide">
                        <img src="img/ic_seat_type11.png" alt="">
                        <a href="#none">출입구</a>
                    </div>
                    <div class="guide">
                        <img src="img/ic_seat_type1.png" alt="">
                        <a href="#none">선택좌석</a>
                    </div>
                    <div class="guide">
                        <img src="img/ic_seat_type3.png" alt="">
                        <a href="#none">예매인석</a>
                    </div>
                    <div class="guide">
                        <img src="img/ic_seat_type4.png" alt="">
                        <a href="#none">선택불가</a>
                    </div>
                </div><!-- guideWrap end -->
                <div class="botWrap">
                    <a href="#none">총 합계 0원</a>
                    <button type="button" class="paybtn">결 제</button>
                </div>
            </div> <!-- sectionCon 끝-->
        </section>
    </div>
</body>
</html>