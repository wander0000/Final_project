<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MyMVP_회원정보</title>
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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage/userInfo.css">
	<script src="${pageContext.request.contextPath}/js/mypage/userInfo.js"></script>
</head>
<body>
    <section class="section">
    <%@ include file="mypageHeader.jsp" %>
        <div class="mainContainer">
            <div class="mainContainerContent">
                <h3 class="menuTitle">
                    회원 정보
                </h3>         
                <div class="mainContent">
                    <div class="leftCon">
                        <div class="imgWrap">
                            <span class="icon userImg">
                                <!-- <i class="fa-regular fa-circle-user"></i> -->
                                <svg xmlns="http://www.w3.org/2000/svg" height="100px" viewBox="0 -960 960 960" width="100px" fill="#5f6368"><path d="M222-255q63-44 125-67.5T480-346q71 0 133.5 23.5T739-255q44-54 62.5-109T820-480q0-145-97.5-242.5T480-820q-145 0-242.5 97.5T140-480q0 61 19 116t63 109Zm257.81-195q-57.81 0-97.31-39.69-39.5-39.68-39.5-97.5 0-57.81 39.69-97.31 39.68-39.5 97.5-39.5 57.81 0 97.31 39.69 39.5 39.68 39.5 97.5 0 57.81-39.69 97.31-39.68 39.5-97.5 39.5Zm.66 370Q398-80 325-111.5t-127.5-86q-54.5-54.5-86-127.27Q80-397.53 80-480.27 80-563 111.5-635.5q31.5-72.5 86-127t127.27-86q72.76-31.5 155.5-31.5 82.73 0 155.23 31.5 72.5 31.5 127 86t86 127.03q31.5 72.53 31.5 155T848.5-325q-31.5 73-86 127.5t-127.03 86Q562.94-80 480.47-80Zm-.47-60q55 0 107.5-16T691-212q-51-36-104-55t-107-19q-54 0-107 19t-104 55q51 40 103.5 56T480-140Zm0-370q34 0 55.5-21.5T557-587q0-34-21.5-55.5T480-664q-34 0-55.5 21.5T403-587q0 34 21.5 55.5T480-510Zm0-77Zm0 374Z"/></svg>
                            </span>
                            <!-- <img src="images/people.svg" alt="#" class="resumeImage"> -->
                            <button class="imgRegist" type="submit">이미지 등록</button>
                        </div>
                        <p>개인정보가 포함된 이미지 등록은 자제하여 주시기 바랍니다.</p>
                    </div>
                    <div class="rightCon">
                        <div class="rightInfo">
                            <div class="infoDetail">
                                <div class="th">이름</div>
                                <div class="td">김영화</div>
                            </div>
                            <div class="infoDetail">
                                <div class="th">아이디</div>
                                <div class="td">MVP202408</div>
                            </div>
                            <div class="infoDetail">
                                <div class="th">휴대폰번호</div>
                                <div class="td">010-1111-2222</div>
                                <button class="modifyBtn" type="submit">휴대폰번호 변경</button>
                            </div>
                            <div class="infoDetail">
                                <div class="th">선호장르</div>
                                <div class="td">드라마, 군사, 미스터리, 전쟁, 로맨스, 분단</div>
                                <button class="modifyBtn" type="submit">선호장르 변경</button>
                            </div>
                        </div><!-- rightInfo 끝 -->
                        <div class="leftInfo">
                            <div class="infoDetail">
                                <div class="th">이메일</div>
                                <div class="td">moviePick@mail.com</div>
                                <button class="modifyBtn" type="submit">이메일 변경</button>
								<!-- 팝업창 (숨겨진 상태로 시작) -->
								<div class="popUp">
									<div class="popBg">
										<div class="popCon">
											<div class="popH">
				                                <span class="icon cancel fs24">
				                                    <i class="fa-solid fa-xmark"></i>
				                                </span>
				                            </div>
											<div class="popM">
											    <h4 class="popTitle">이메일 변경</h4>
											    <h5 class="popAlert">변경하려는 내용을 입력하세요.</h5>
											</div>
											<div class="popB">
												<form id="emailForm" method="Fetch">
													<div class="inputCon">
														<!-- CSRF Token -->
					                                    <input type="hidden" id="token" name="${_csrf}" value="${_csrf.token}"/>  
												        <input type="email" id="email" name="newemail" required placeholder="변경할 이메일">
											        </div>
													<div class="sendButton">
														<button type="cancel" onclick="closePopup()">취소</button>
											        	<button type="submit" class="updateBtn">수정</button>
													</div>
											    </form>
											</div>   
										</div>
									</div>
								</div>
                            </div>
                            <div class="infoDetail">
                                <div class="th">비밀번호</div>
                                <div class="td">****************</div>
                                <button class="modifyBtn pwBtn" type="submit">비밀번호 변경</button>
                            </div>
                            <div class="infoDetail">
                                <div class="th">생년월일</div>
                                <div class="td">2000-08-27</div>
                                <button class="modifyBtn" type="submit">생년월일 변경</button>
                            </div>
                            <div class="infoDetail">
                                <div class="th">환불계좌</div>
                                <div class="td">환불계좌가 등록되지 않았습니다.</div>
                                <button class="modifyBtn" type="submit">환불계좌 등록</button>
                            </div>
                        </div><!-- leftInfo 끝 -->
                    </div><!-- rightCon 끝 -->
                </div><!-- withdrawContent -->

                
                </div><!-- mainContainerContent -->
        </div> <!-- mainContainer -->
    </section>
</body>
</html>
