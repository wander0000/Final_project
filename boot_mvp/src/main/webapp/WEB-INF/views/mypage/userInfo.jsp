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
                        <div class="leftInfo">
                            <div class="infoDetail">
                                <div class="th">이름</div>
                                <div class="td">${user.pname}</div>
                            </div>
                            <div class="infoDetail">
                                <div class="th">아이디</div>
                                <div class="td">${user.userid}</div>
                            </div>
                            <div class="infoDetail">
                                <div class="th">휴대폰번호</div>
                                <div class="td">${user.phone}</div>
                                <button class="modifyBtn" type="submit">휴대폰번호 변경</button>
								<!-- 팝업창 (숨겨진 상태로 시작) -->
								<div class="popUp">
									<div class="popBg">
										<div class="popCon">
											<div class="popH">
								                <div class="popM">
								                    <h4 class="popTitle">휴대폰번호 변경</h4>
								                    <h5 class="popAlert">변경할 내용 입력하세요.</h5>
								                </div>
								                <div class="mlauto">
								                    <span class="icon cancel fs24">
								                        <i class="fa-solid fa-xmark"></i>
								                    </span>
								                </div>
								            </div> <!-- popH 끝 -->
											<div class="popB">
												<form id="phoneForm" method="Fetch">
													<div class="inputCon">
														<!-- CSRF Token -->
					                                    <input type="hidden" id="token" name="${_csrf}" value="${_csrf.token}"/>  
														<div class="check_font" id="tel_check"></div>
												        <input class="replace" type="tel" id="phone" name="newphone" required placeholder="변경할 전화번호">
											        </div>
													<div class="sendButton">
														<button type="cancel" class="cancel">취소</button>
											        	<button type="submit" class="updateBtn">수정</button>
													</div>
											    </form>
											</div>   
										</div><!-- popCon 끝 -->
									</div><!-- popBg 끝 -->
								</div><!-- popUp 끝 -->
                            </div>
                            <div class="infoDetail">
                                <div class="th">선호장르</div>
                                <div class="td" id="selectedGenresText">${genreList}</div>
							    <input type="hidden" id="selectedGenresValue" />
                                <button class="modifyBtn" type="submit">선호장르 변경</button>
								<!-- 팝업창 (숨겨진 상태로 시작) -->
								<div class="popUp">
									<div class="popBg">
										<div class="popCon">
											<div class="popH">
								                <div class="popM">
								                    <h4 class="popTitle">선호장르 변경</h4>
								                    <h5 class="popAlert">변경할 선호장르를 선택하세요.</h5>
								                </div>
								                <div class="mlauto">
								                    <span class="icon cancel fs24">
								                        <i class="fa-solid fa-xmark"></i>
								                    </span>
								                </div>
								            </div> <!-- popH 끝 -->
											<div class="popB">
												<form id="genreForm" method="Fetch">
													<!-- CSRF Token -->
					                                <input type="hidden" id="token" name="${_csrf}" value="${_csrf.token}"/>  
													<div class="genrebox">
													    <c:set var="selectedGenres" value="${fn:split(genreList, ',')}" />
													    <div class="genreboxButtons">
													        <c:forEach var="genre" items="${genres}">
													            <input type="checkbox" id="${genre.genreno}" name="genres" value="${genre.genreno}" class="genrebut"
													                <c:if test="${fn:contains(genreList, genre.genrenm)}">
													                    checked="checked"
													                </c:if>
													            />
													            <label for="${genre.genreno}" class="genrebutlabel">${genre.genrenm}</label>
													        </c:forEach>
													    </div>
													</div>
													<div class="sendButton">
														<button type="cancel" class="cancel">취소</button>
											        	<button type="submit" class="updateBtn">수정</button>
													</div>
											    </form>
											</div>   
										</div><!-- popCon 끝 -->
									</div><!-- popBg 끝 -->
								</div><!-- popUp 끝 -->
                            </div>
                        </div><!-- leftInfo 끝 -->
                        <div class="rightInfo">
							<div class="infoDetail">
                                <div class="th">이메일</div>
                                <div class="td">${user.email}</div>
							</div>
							 <div class="infoDetail">
                                <div class="th">비밀번호</div>
                                <div class="td" id="pwValue">${user.ppass}</div>
                                <button class="modifyBtn pwBtn" type="submit">비밀번호 변경</button>
								<!-- 팝업창 (숨겨진 상태로 시작) -->
								<div class="popUp">
									<div class="popBg">
										<div class="popCon">
											<div class="popH">
								                <div class="popM">
								                    <h4 class="popTitle">비밀번호 변경</h4>
								                    <h5 class="popAlert">변경할 비밀번호를 입력하세요.</h5>
								                </div>
								                <div class="mlauto">
								                    <span class="icon cancel fs24">
								                        <i class="fa-solid fa-xmark"></i>
								                    </span>
								                </div>
								            </div> <!-- popH 끝 -->
											<div class="popB">
												<form id="ppassForm" method="Fetch">
													<div class="inputCon">
														<!-- CSRF Token -->
					                                    <input type="hidden" id="token" name="${_csrf}" value="${_csrf.token}"/>  
												        <input class="replace" type="password" id="ppass" name="newppass" required placeholder="변경할 비밀번호">
												        <input class="replace" type="password" id="ppass_check" name="ppass_check" required placeholder="변경할 비밀번호 재입력">
											        </div>
													<div class="sendButton">
														<button type="cancel" class="cancel">취소</button>
											        	<button type="submit" class="updateBtn">수정</button>
													</div>
											    </form>
											</div>   
										</div><!-- popCon 끝 -->
									</div><!-- popBg 끝 -->
								</div><!-- popUp 끝 -->
                            </div>
                            <div class="infoDetail">
                                <div class="th">생년월일</div>
                                <div class="td" id="birthday">${user.birth}</div>
                                <button class="modifyBtn" type="submit">생년월일 변경</button>
								<!-- 팝업창 (숨겨진 상태로 시작) -->
								<div class="popUp">
								    <div class="popBg">
								        <div class="popCon">
								            <div class="popH">
								                <div class="popM">
								                    <h4 class="popTitle">생년월일 변경</h4>
								                    <h5 class="popAlert">변경할 생년월일을 입력하세요.</h5>
								                </div>
								                <div class="mlauto">
								                    <span class="icon cancel fs24">
								                        <i class="fa-solid fa-xmark"></i>
								                    </span>
								                </div>
								            </div> <!-- popH 끝 -->
								            <div class="popB">
								                <form id="birthForm" method="Fetch">
								                    <div class="inputCon">
								                        <!-- CSRF Token -->
								                        <input type="hidden" id="token" name="${_csrf}" value="${_csrf.token}" />
								                        <div class="check_font" id="birth_check"></div>
								                        <input class="replace" type="text" id="birth" name="newbirth" required placeholder="변경할 생년월일(YYYY-MM-DD)">
								                    </div>
								                    <div class="sendButton">
								                        <button type="cancel" class="cancel">취소</button>
								                        <button type="submit" class="updateBtn">수정</button>
								                    </div>
								                </form>
								            </div>   
								        </div><!-- popCon 끝 -->
								    </div><!-- popBg 끝 -->
								</div><!-- popUp 끝 -->
	                        </div>
                            <div class="infoDetail">
                                <div class="th">환불계좌</div>
								<c:choose>
									<c:when test="${empty user.account}">
		                                <div class="td">환불계좌가 등록되지 않았습니다.</div>
									</c:when>
									<c:otherwise>
		                                <div class="td">${user.account}</div>
									</c:otherwise>
								</c:choose>		
                                <button class="modifyBtn" type="submit">환불계좌 등록</button>
								<!-- 팝업창 (숨겨진 상태로 시작) -->
								<div class="popUp">
									<div class="popBg">
										<div class="popCon">
											<div class="popH">
								                <div class="popM">
								                    <h4 class="popTitle">환불계좌 변경</h4>
								                    <h5 class="popAlert">변경할 환불계좌를 입력하세요.</h5>
								                </div>
								                <div class="mlauto">
								                    <span class="icon cancel fs24">
								                        <i class="fa-solid fa-xmark"></i>
								                    </span>
								                </div>
								            </div> <!-- popH 끝 -->
											<div class="popB">
												<form id="accountForm" method="Fetch">
													<div class="inputCon">
														<!-- CSRF Token -->
					                                    <input type="hidden" id="token" name="${_csrf}" value="${_csrf.token}"/>  
												        <input class="replace" type="text" id="account" name="newaccount" required placeholder="변경할 환불계좌">
											        </div>
													<div class="sendButton">
														<button type="cancel" class="cancel">취소</button>
											        	<button type="submit" class="updateBtn">수정</button>
													</div>
											    </form>
											</div>   
										</div><!-- popCon 끝 -->
									</div><!-- popBg 끝 -->
								</div><!-- popUp 끝 -->
                            </div>
                        </div><!-- rightInfo 끝 -->
                    </div><!-- rightCon 끝 -->
                </div><!-- mainContent 끝-->
            </div><!-- mainContainerContent -->
        </div> <!-- mainContainer -->
    </section>
</body>
</html>
