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
        <div class="container">
        </div>

        <section> <!-- section 시작-->
            <div class="wrap">     
                <div class="loginWrap">
					<!-- 뒤로가기 아이콘 추가 -->
					<a href="javascript:history.back();" class="back-icon">
					    <i class="fas fa-arrow-left"></i>
					</a>
                    <div class="logbox">
                        <div class="background-blur"></div> <!-- 블러 배경 -->
                        <div class="content">
                            <!-- 비밀번호 재설정 헤더 -->
                            <div class="log1" id="resetHeader">
                                <h5 class="hh">비밀번호 재설정</h5>
                            </div>

                            <!-- 비밀번호 재설정 폼 -->
                            <form id="resetPwForm" action="/resetPassword" method="post">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> <!-- CSRF 토큰 추가 -->
                                <input type="hidden" name="email" value="${email}"/> <!-- 이메일 hidden 필드 -->
                                
                                <div class="log2">
                                    <input type="password" id="newPassword" name="newPassword" placeholder="새 비밀번호 입력" class="idpwbox3">
                                </div>
                                
                                <div class="log3">
                                    <input type="password" id="confirmPassword" name="confirmPassword" placeholder="비밀번호 재입력" class="idpwbox3">
                                </div>

                                <div class="log4">
                                    <input type="submit" value="확인" class="logbut">
                                </div>
                            </form>

                            <div class="bottbox">
                            </div>
                        </div>
                    </div>

                    <!-- 팝업 -->
                    <div class="loginWrap2" id="popupWrap" style="display: none;">
						<!-- 뒤로가기 아이콘 추가 -->
						<a href="javascript:history.back();" class="back-icon">
						    <i class="fas fa-arrow-left"></i>
						</a>
                        <div class="logbox">
                            <div class="background-blur"></div> <!-- 블러 배경 -->
                            <div class="content">
                                <div class="log1">
                                    <h5 class="hh" id="popupTitle">비밀번호가 변경되었습니다!</h5>
                                </div>
                                <div class="log4">
                                    <input type="button" value="로그인 하러가기" class="logbut2" id="goToLoginButton">
                                </div>
                            </div>
                        </div>
                    </div>
                    
                </div> <!--loginWrap 끝-->
            </div> <!-- wrap 끝 -->
        </section> <!--section 끝 -->
    </div> <!-- logimg 끝-->

</body>
</html>
<script>
    const contextPath = '${pageContext.request.contextPath}';
</script>
<script src="${pageContext.request.contextPath}/js/login/resetPwPage.js"></script>  