<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>아이디 찾기</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/findIdPage.css">
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

        <section> <!-- section 시작-->
            <div class="wrap">     
                <!-- 아이디 찾기 폼 -->
                <div class="loginWrap" id="loginWrap">
                    <!-- 뒤로가기 아이콘 추가 -->
                    <a href="javascript:history.back();" class="back-icon">
                        <i class="fas fa-arrow-left"></i>
                    </a>

                    <div class="logbox">
                        <div class="background-blur"></div> <!-- 블러 배경 -->
                        <div class="content">
                            <div class="log1">
                                <h5 class="hh">아이디 찾기</h5>
                            </div>
                            <div class="log2">
                                <input type="text" id="name" placeholder="이름" class="idpwbox1">
                            </div>
                            <div class="log3">
                                <input type="text" id="email" placeholder="이메일" class="idpwbox2">
                            </div>
                            <div class="log4">
                                <input type="button" value="확인" class="logbut" id="checkIdButton">
                            </div>
                            <div class="bottbox"></div>
                        </div>
                    </div>
                </div>

                <!-- 결과 팝업 -->
                <div class="loginWrap2" id="resultWrap" style="display: none;">
                    <div class="logbox">
                        <div class="background-blur"></div> <!-- 블러 배경 -->
                        <div class="content">
                            <div class="log1">
                                <h5 class="hh" id="resultTitle"></h5>
                            </div>
                            <div class="log4">
                                <p id="resultMessage"></p>
                            </div>
                            <div class="log4">
                                <input type="button" value="확인" class="logbut2" id="closeModalButton">
                            </div>
                        </div>
                    </div>
                </div>
            </div> <!--wrap 끝 -->
        </section> <!--section 끝 -->
    </div> <!-- logimg 끝-->


</body>

</html>
<script>
    const contextPath = '${pageContext.request.contextPath}';
</script>
<script src="${pageContext.request.contextPath}/js/findIdPage.js"></script>
