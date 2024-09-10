<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginPage.css">

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

                    <div class="logbox">
                        <div class="background-blur"></div> <!-- 블러 배경 -->
                        <div class="content">
                            <div class="log1">
                                <h5 class="hh">로그인</h5>
                            </div>
                            <div class="log2">
                                <!-- Start of Login Form -->
                                <form action="/auth" method="post">
									<!-- CSRF Token -->
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>                                    
									
                                    <input type="text" name="userid" placeholder="아이디" class="idpwbox1" required>
                            </div>
                            <div class="log3">
                                    <input type="password" name="ppass" placeholder="비밀번호" class="idpwbox2" required>
                            </div>
                            <div class="log4">
                                    <input type="submit" value="로그인" class="logbut">
                                </form> <!-- End of Login Form -->
                            </div>
                            <div class="bottbox">
                                <div class="bot1">
                                    <div class="a1">
                                        <a href="findIdPage" class="aa1">아이디 찾기</a>
                                    </div>
                                    <div class="a2">
                                        <a href="findPwPage" class="aa1">비밀번호 재설정</a>
                                    </div>
                                    <div class="a1">
                                        <a href="${pageContext.request.contextPath}/signup" class="aa1">회원가입</a>
                                    </div>
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
<script src="${pageContext.request.contextPath}/js/loginPage.js"></script>  
