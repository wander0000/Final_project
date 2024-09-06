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
    <script src="js/index.js"></script>


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
                                            <input type="checkbox" id="${genre.genreno}" name="genres" value="${genre.genreno}" class="genrebut" />
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

				<!-- 2 회원가입 완료 -->
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
    $(document).ready(function() {
        // 로그인 하러가기 버튼 클릭 시 /login으로 이동
        $('#loginButton').on('click', function() {
            window.location.href = "${pageContext.request.contextPath}/login";
        });

        // 폼 제출 시 장르 선택 확인 및 섹션 전환
        $('form').on('submit', function(event) {
            var selectedGenres = $('input[name="genres"]:checked').length;

            // 3개 미만일 경우 경고 메시지를 띄우고 폼 제출을 막음
            if (selectedGenres < 3) {
                alert('최소 3개의 장르를 선택해 주세요.');
                event.preventDefault(); // 폼 제출 방지
            } else {
                // 폼이 유효하면 폼을 전송
                // 기본 폼 제출이 발생하고 나서 페이지 리프레시 대신 섹션을 전환하고 싶다면 AJAX를 사용해야 함.
                $.ajax({
                    type: 'POST',
                    url: "${pageContext.request.contextPath}/signup/step2",
                    data: $('form').serialize(), // 폼 데이터를 직렬화하여 전송
                    success: function(response) {
                        // 서버 응답이 성공적이면 섹션을 전환
                        $('.loginWrap').hide(); // 첫 번째 장르 선택 섹션 숨기기
                        $('.loginWrap2').show(); // 두 번째 회원가입 완료 섹션 표시
                    },
                    error: function(error) {
                        alert('폼 제출에 실패했습니다. 다시 시도해 주세요.');
                    }
                });
                event.preventDefault(); // 기본 폼 제출을 방지하고 AJAX로 제출
            }
        });
    });
</script>