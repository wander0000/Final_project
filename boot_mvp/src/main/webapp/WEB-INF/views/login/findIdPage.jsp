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
    <script src="${pageContext.request.contextPath}/js/findIdPage.js"></script>
  
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
                                <input type="button" value="확인" class="logbut" id="closeModalButton">
                            </div>
                        </div>
                    </div>
                </div>
            </div> <!--wrap 끝 -->
        </section> <!--section 끝 -->
    </div> <!-- logimg 끝-->

	<script>
		$(document).ready(function() {
		    // CSRF 토큰을 HTML 메타 태그로 삽입하고, Ajax 요청 시 함께 사용
		    const csrfToken = $("meta[name='_csrf']").attr("content");

		    // 확인 버튼 클릭 시 서버로 요청
		    $('#checkIdButton').on('click', function() {
		        var name = $('#name').val();
		        var email = $('#email').val();

		        $.ajax({
		            url: "${pageContext.request.contextPath}/userid",
		            type: "GET",
		            data: { pname: name, email: email },
		            beforeSend: function(xhr) {
		                xhr.setRequestHeader("X-CSRF-TOKEN", csrfToken);  // CSRF 토큰 추가
		            },
		            success: function(response) {
		                // 성공적인 응답일 때
		                $('#resultTitle').html('회원님의 아이디는<br>' + response + ' 입니다');
		                $('#loginWrap').hide();  // 폼 섹션 숨기기
		                $('#resultWrap').show();  // 결과 섹션 표시
		                // 버튼의 텍스트를 '로그인 하러가기'로 변경
		                $('#closeModalButton').val('로그인 하러가기');
		                // 버튼 클릭 시 /login 페이지로 이동
		                $('#closeModalButton').off('click').on('click', function() {
		                    window.location.href = "${pageContext.request.contextPath}/login";
		                });
		            },
		            error: function(xhr) {
		                if (xhr.status === 404) {
		                    // 일치하는 아이디가 없을 때
		                    $('#resultTitle').text('일치하는 아이디가 없습니다');
		                    $('#loginWrap').hide();  // 폼 섹션 숨기기
		                    $('#resultWrap').show();  // 결과 섹션 표시
		                    // 버튼의 텍스트를 '확인'으로 설정
		                    $('#closeModalButton').val('확인');
		                    // 팝업 닫기 버튼 동작 설정
		                    $('#closeModalButton').off('click').on('click', function() {
		                        $('#resultWrap').hide();
		                        $('#loginWrap').show();  // 폼 섹션 다시 표시
		                    });
		                } else {
		                    alert('오류가 발생했습니다. 다시 시도해 주세요.');
		                }
		            }
		        });
		    });

		    // 팝업 닫기 버튼 기본 동작
		    $('#closeModalButton').on('click', function() {
		        $('#resultWrap').hide();
		        $('#loginWrap').show();  // 폼 섹션 다시 표시
		    });
		});

	</script>

</body>

</html>
