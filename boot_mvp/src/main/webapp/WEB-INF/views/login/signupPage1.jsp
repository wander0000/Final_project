<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입 1단계</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/signupPage1.css">
    <!-- import font-awesome, line-awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/line-awesome/1.3.0/line-awesome/css/line-awesome.min.css">
    <!-- import pretendard font -->
    <link rel="stylesheet" as="style" crossorigin href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.8/dist/web/variable/pretendardvariable.css"/>
    <!-- import js -->
    <script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/js/signupPage1.js"></script>
  
</head>



<body>
    <div class="logimg">
        <div class="container"></div>
        <section>
            <div class="wrap">
                <div class="loginWrap">
                    <div class="logbox">
                        <div class="background-blur"></div>
                        <div class="content">
                            <div class="log1">
                                <h5 class="hh">회원가입</h5>
                            </div>
                            <form action="${pageContext.request.contextPath}/signup/step1" method="post">
                                <!-- CSRF 토큰 추가 -->
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
								<div class="log2">
								    <h5 class="nobo">*</h5>
								    <input type="text" name="pname" placeholder="이름" class="idpwbox3" required id="pname">
								</div>
                                <div class="log3">
                                    <h5 class="nobo">*</h5>
                                    <input type="email" name="email" placeholder="이메일 주소" class="idpwbox3" required id="emailInput">
                                    <a href="javascript:void(0)" class="emaillink" id="checkEmail">중복확인</a>
                                </div>
								<div class="log3">
								    <h5 class="nobo">*</h5>
								    <input type="text" name="userid" placeholder="아이디" class="idpwbox3" required id="userIdInput">
								    <a href="javascript:void(0)" class="emaillink" id="checkId">중복확인</a>
								</div>
                                <div class="log3">
                                    <h5 class="nobo">*</h5>
                                    <input type="password" id="ppass" name="ppass" placeholder="비밀번호" class="idpwbox3" required>
                                    <a href="javascript:void(0)" class="pwllink" id="togglePassword">비밀번호보기</a>
                                </div>
                                <div class="log3">
                                    <h5 class="nobo">*</h5>
                                    <input type="password" id="ppassConfirm" name="ppassConfirm" placeholder="비밀번호 확인" class="idpwbox3" required>
                                    <a href="javascript:void(0)" class="pwwllink" id="checkPasswordMatch">중복확인</a>
                                </div>
                                <div class="log3">
                                    <h5 class="nobo">*</h5>
                                    <input type="tel" name="phone" placeholder="휴대폰 번호 (010-1234-5678)" class="idpwbox3" required id="phoneInput" maxlength="13">
                                </div>
                                <div class="log3">
                                    <h5 class="nobo">*</h5>
                                    <input type="text" name="birth" placeholder="생년월일 (YYYY-MM-DD)" class="idpwbox3" required id="birthInput" maxlength="10">
                                </div>
                                <div class="gdbox1">
                                    <h5 class="gdgd11">*</h5>
                                    <h5 class="gdgd22">성별</h5>
                                    <div class="gdbox2">
                                        <button type="button" class="gender-button male-button" id="male" onclick="setGender('1')">남</button>
                                    </div>
                                    <div class="gdbox3">
                                        <button type="button" class="gender-button female-button" id="female" onclick="setGender('2')">여</button>
                                    </div>
                                    <input type="hidden" id="gender" name="gender" />
                                </div>
								<div class="agreebox">
								    <div class="agr11">
								        <input type="checkbox" name="chk1" id="check1" class="chk">
								        <label for="check1"></label>
								        <h5 class="agrh">전체약관에 동의합니다</h5>
								    </div>
									<div class="agr1">
									    <input type="checkbox" name="chk2" id="check2" class="chk" required>
									    <label for="check2" class="agrh">(필수) 만 14세 이상입니다</label>
									</div>
									<div class="agr1">
									    <input type="checkbox" name="chk3" id="check3" class="chk" required>
									    <label for="check3" class="agrh">(필수) 서비스 이용 약관</label>
									    <a href="javascript:void(0)" class="agrhh" onclick="openModal('service')">보기</a>
									</div>
									<div class="agr1">
									    <input type="checkbox" name="chk4" id="check4" class="chk" required>
									    <label for="check4" class="agrh">(필수) 개인정보 수집 및 이용 동의</label>
									    <a href="javascript:void(0)" class="agrhh" onclick="openModal('privacy')">보기</a>
									</div>


                                    <!-- 모달 창 -->
                                    <div id="modal" class="modal" style="display:none;">
                                        <div class="modal-content">
                                            <!-- 닫기 버튼과 타이틀을 같은 선상에 배치 -->
                                            <div class="modal-header">
                                                <h1 class="modal-title" id="modalTitle">MVP 서비스 이용 약관</h1>
                                                <span class="close" onclick="closeModal()">&times;</span>
                                            </div>
                                            <div id="modalText" class="modal-text">
                                                <!-- 여기에 동적으로 텍스트가 추가됩니다 -->
                                            </div>
                                        </div>
                                    </div>

								</div>
                                <div class="log4">
                                    <input type="submit" value="다음으로" class="logbut">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</body>
</html>



