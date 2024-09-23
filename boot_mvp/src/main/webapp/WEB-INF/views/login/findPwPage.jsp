<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>비밀번호 재설정</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/findPwPage.css">
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

        <section> <!-- section 시작 -->
            <div class="wrap">     
                <div class="loginWrap">
					<!-- 뒤로가기 아이콘 추가 -->
					<a href="javascript:history.back();" class="back-icon">
					    <i class="fas fa-arrow-left"></i>
					</a>
                    <div class="logbox">
						
                        <div class="background-blur"></div> <!-- 블러 배경 -->
						
                        <div class="content">	
							
                            <div class="log1">
                                <h5 class="hh">비밀번호 재설정</h5>
                            </div>
                            <!-- 이메일 입력 폼 -->
                            <div class="log2">
                                <input type="text" id="email" value="" placeholder="이메일" class="idpwbox3">
                                <a href="javascript:void(0);" class="oklink" id="sendCodeButton">전송</a> <!-- 전송 버튼 -->
                            </div>
                            <!-- 인증번호 입력 폼 -->
                            <div class="log3">
                                <input type="text" id="code" value="" placeholder="인증번호" class="idpwbox3">
                                <a href="javascript:void(0);" class="oklink" id="verifyCodeButton">확인</a> <!-- 인증번호 확인 버튼 -->
                            </div>
                            <div class="log4">
                                <input type="button" value="재설정 하러가기" class="logbut" id="finalSubmitButton" style="display: none;"> <!-- 비밀번호 재설정 버튼 -->
                            </div>
                            <div class="bottbox"></div>
                        </div>
                    </div>
                </div> <!-- loginWrap 끝 -->
            </div> <!-- wrap 끝 -->
        </section> <!-- section 끝 -->
    </div> <!-- logimg 끝 -->
</body>
</html>

<script>
    const contextPath = '${pageContext.request.contextPath}';
</script>
<script src="${pageContext.request.contextPath}/js/login/findPwPage.js"></script>  
