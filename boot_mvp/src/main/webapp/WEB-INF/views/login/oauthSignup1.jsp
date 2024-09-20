<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입 1단계</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/oauthSignup1.css">
    <!-- import font-awesome, line-awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/line-awesome/1.3.0/line-awesome/css/line-awesome.min.css">
    <!-- import pretendard font -->
    <link rel="stylesheet" as="style" crossorigin href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.8/dist/web/variable/pretendardvariable.css"/>
    <!-- import js -->
    <script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>

</head>
<body>
    <div class="logimg">
        <div class="container"></div>

        <!-- 뒤로가기 버튼 추가 -->
        <a href="javascript:history.back()" class="back-icon">
            <i class="fa fa-arrow-left"></i>
        </a>

        <section>
            <div class="wrap">
                <div class="loginWrap2">
                    <div class="logbox">
                        <div class="background-blur"></div>
                        <div class="content">
                            <div class="log1">
                                <h5 class="hh">회원가입</h5>
                            </div>
                            <form id="oauthSignupForm" action="/oauthSignupSubmit1" method="POST">
                                <!-- CSRF 토큰 추가 -->
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <div class="log2">
                                    <h5 class="nobo">*</h5>
                                    <input type="text" id="pname" name="pname" required placeholder="이름" class="idpwbox3">
                                </div>

                                <div class="log3">
                                    <h5 class="nobo">*</h5>
                                    <input type="text" id="userid" name="userid" required placeholder="아이디" class="idpwbox3">
                                    <a href="javascript:void(0)" class="emaillink" id="checkId">중복확인</a>
                                </div>


                                <div class="log3">
                                    <h5 class="nobo">*</h5>
                                    <input type="text" name="phone" placeholder="휴대폰 번호 (010-1234-5678)" class="idpwbox3" required id="phone" maxlength="13">
                                </div>

                                <div class="log3">
                                    <h5 class="nobo">*</h5>
                                    <input type="text" name="birth" placeholder="생년월일 (YYYY-MM-DD)" class="idpwbox3" required id="birth" maxlength="10">
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
                                            <div class="modal-header">
                                                <h1 class="modal-title" id="modalTitle">MVP 서비스 이용 약관</h1>
                                                <span class="close" onclick="closeModal()">&times;</span>
                                            </div>
                                            <div id="modalText" class="modal-text"></div>
                                        </div>
                                    </div>
                                </div>

                                <!-- 히든 필드로 oauthUserId와 registrationId 값 추가 -->
                                <input type="hidden" id="oauthUserId" name="oauthUserId" value="${oauthUserId}">
                                <input type="hidden" id="registrationId" name="registrationId" value="${registrationId}">
                                <input type="hidden" id="email" name="email" value="${email}">
                                <!-- CSRF 토큰 히든 필드 추가 -->
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

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
<script>
    const contextPath = '${pageContext.request.contextPath}';
</script>
<script src="${pageContext.request.contextPath}/js/oauthSignup1.js"></script>

