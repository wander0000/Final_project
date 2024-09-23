<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Header</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/signupPage2.css">
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
        <section>
            <div class="wrap">

                <!-- 1 장르 선택 -->
                <div class="loginWrap">
                    <div class="logbox">
                        <div class="background-blur"></div>
                        <div class="content">
                            <div class="log1">
                                <h5 class="hh">회원가입</h5>
                            </div>
                            <form action="${pageContext.request.contextPath}/signup/step2" method="post">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                <div class="grh">
                                    <h5 class="grgr">*</h5>
                                    <h5 class="grhh">회원님이 좋아하는 장르를 선택해주세요! (3개 이상)</h5>
                                </div>
                                <div class="genrebox">
                                    <div class="genreboxButtons">
                                        <c:forEach var="genre" items="${genres}">
                                            <input type="checkbox" id="${genre.genreno}" name="genres" value="${genre.genrenm}" class="genrebut" />
                                            <label for="${genre.genreno}" class="genrebutlabel">${genre.genrenm}</label>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="log4">
                                    <input type="submit" value="가입하기" class="logbut">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

				<!-- 22 회원가입 완료 -->
				<div class="loginWrap2">
				    <div class="logbox">
				        <div class="background-blur"></div>
				        <div class="content">
				            <div class="log1">
				                <h5 class="hh">축하합니다!<br>회원가입이 완료되었습니다</h5>
				            </div>
				            <div class="log4">
				                <input type="button" value="로그인 하러가기" class="logbut2" id="loginButton">
				            </div>
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
<script src="${pageContext.request.contextPath}/js/login/signupPage2.js"></script>