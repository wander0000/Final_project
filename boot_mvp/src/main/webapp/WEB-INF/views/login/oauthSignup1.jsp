<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>OAuth 추가 회원가입</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <h1>OAuth 추가 회원가입</h1>

	<form id="oauthSignupForm" action="/oauthSignupSubmit1" method="POST">
	    <label for="userid">아이디:</label>
	    <input type="text" id="userid" name="userid" required>
	    <button type="button" id="checkUserIdBtn">아이디 중복 검사</button>
	    <span id="userIdCheckResult"></span>
	    <br><br>

	    <label for="pname">이름:</label>
	    <input type="text" id="pname" name="pname" required>
	    <br><br>

	    <label for="phone">전화번호:</label>
	    <input type="text" id="phone" name="phone" required>
	    <br><br>

	    <label for="birth">생년월일:</label>
	    <input type="date" id="birth" name="birth" required>
	    <br><br>

	    <label for="gender">성별:</label>
	    <select id="gender" name="gender" required>
	        <option value="1">남성</option>
	        <option value="2">여성</option>
	    </select>
	    <br><br>

	    <!-- 히든 필드로 oauthUserId와 registrationId 값 추가 -->
	    <input type="hidden" id="oauthUserId" name="oauthUserId" value="${oauthUserId}">
	    <input type="hidden" id="registrationId" name="registrationId" value="${registrationId}">
		<input type="hidden" id="email" name="email" value="${email}">
	    <!-- CSRF 토큰 히든 필드 추가 -->
	    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

	    <input type="submit" value="가입하기">
	</form>

    <script>
		$(document).ready(function() {
		    // 아이디 중복 검사 버튼 클릭 시
		    $('#checkUserIdBtn').on('click', function() {
		        var userid = $('#userid').val();

		        if (!userid) {
		            alert('아이디를 입력해주세요.');
		            return;
		        }

		        // 두 개의 API를 호출하여 아이디 중복 여부를 체크
		        $.when(
		            // 첫 번째 테이블에서 중복 체크
		            $.ajax({
		                url: '/checkUserId',
		                type: 'GET',
		                data: { userid: userid }
		            }),
		            // 두 번째 테이블에서 중복 체크 (OAuth 테이블)
		            $.ajax({
		                url: '/oauthCheckUserid',
		                type: 'GET',
		                data: { userid: userid }
		            })
		        ).done(function(checkUserIdResponse, oauthCheckUseridResponse) {
		            // 두 API 응답에서 userExists 값 확인
		            var userIdExists = checkUserIdResponse[0].userExists;
		            var oauthUserIdExists = oauthCheckUseridResponse[0].userExists;

		            // 두 테이블에서 모두 중복된 아이디가 없을 때만 "사용 가능"
		            if (!userIdExists && !oauthUserIdExists) {
		                $('#userIdCheckResult').text('사용 가능한 아이디입니다.');
		            } else {
		                $('#userIdCheckResult').text('이미 사용 중인 아이디입니다.');
		            }
		        }).fail(function() {
		            alert('아이디 중복 검사 중 오류가 발생했습니다.');
		        });
		    });
		});

    </script>
</body>
</html>
