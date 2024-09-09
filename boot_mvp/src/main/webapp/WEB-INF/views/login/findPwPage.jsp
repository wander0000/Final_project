<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>비밀번호 찾기</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
    <script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
</head>

<body>
    <div class="container">
        <h2>비밀번호 찾기</h2>
        
        <!-- 비밀번호 찾기 이메일 입력 폼 -->
        <div id="passwordResetStep1">
            <form id="emailForm">
                <label for="email">이메일을 입력하세요:</label>
                <input type="email" id="email" name="email" required>
                <button type="submit">확인</button>
            </form>
        </div>

        <!-- 인증번호 입력 폼, 처음엔 숨김 -->
        <div id="verificationStep" style="display: none;">
            <form id="verificationForm">
                <label for="verificationCode">인증번호를 입력하세요:</label>
                <input type="text" id="verificationCode" name="verificationCode" required>
                <button type="submit">확인</button>
            </form>
        </div>

        <!-- 비밀번호 재설정 폼, 처음엔 숨김 -->
        <div id="resetPasswordStep" style="display: none;">
            <form id="resetPasswordForm">
                <label for="newPassword">새 비밀번호:</label>
                <input type="password" id="newPassword" name="newPassword" required>
                <label for="confirmPassword">비밀번호 확인:</label>
                <input type="password" id="confirmPassword" name="confirmPassword" required>
                <button type="submit">비밀번호 재설정</button>
            </form>
        </div>

        <!-- 결과 메시지 표시 -->
        <div id="resultMessage" style="display: none;">
            <p id="message"></p>
        </div>
    </div>

    <script>
        $(document).ready(function() {
            // 이메일 입력 폼 제출 처리
            $('#emailForm').on('submit', function(event) {
                event.preventDefault();
                var email = $('#email').val();

                $.ajax({
                    url: '/email/check-email',  // 서버에서 이메일 존재 여부를 확인하는 API
                    type: 'POST',
                    data: { email: email },
                    success: function(response) {
                        if (response.emailExists) {
                            alert("인증번호가 해당 이메일로 전송되었습니다.");
                            $('#passwordResetStep1').hide();  // 이메일 입력 폼 숨김
                            $('#verificationStep').show();  // 인증번호 입력 폼 표시
                        } else {
                            alert("이메일을 다시 확인해주세요.");
                        }
                    },
                    error: function() {
                        alert("오류가 발생했습니다. 다시 시도해주세요.");
                    }
                });
            });

            // 인증번호 입력 폼 제출 처리
            $('#verificationForm').on('submit', function(event) {
                event.preventDefault();
                var verificationCode = $('#verificationCode').val();

                $.ajax({
                    url: '/verify-code',  // 서버에서 인증번호 확인하는 API
                    type: 'POST',
                    data: { code: verificationCode },
                    success: function(response) {
                        if (response.codeValid) {
                            alert("인증번호가 확인되었습니다.");
                            $('#verificationStep').hide();  // 인증번호 폼 숨김
                            $('#resetPasswordStep').show();  // 비밀번호 재설정 폼 표시
                        } else {
                            alert("인증번호가 틀렸습니다.");
                        }
                    },
                    error: function() {
                        alert("오류가 발생했습니다. 다시 시도해주세요.");
                    }
                });
            });

            // 비밀번호 재설정 폼 제출 처리
            $('#resetPasswordForm').on('submit', function(event) {
                event.preventDefault();
                var newPassword = $('#newPassword').val();
                var confirmPassword = $('#confirmPassword').val();

                if (newPassword !== confirmPassword) {
                    alert("비밀번호가 일치하지 않습니다.");
                    return;
                }

                $.ajax({
                    url: '/reset-password',  // 서버에서 비밀번호를 재설정하는 API
                    type: 'POST',
                    data: { password: newPassword },
                    success: function() {
                        alert("비밀번호가 성공적으로 재설정되었습니다.");
                        window.location.href = "/login";  // 로그인 페이지로 리디렉션
                    },
                    error: function() {
                        alert("오류가 발생했습니다. 다시 시도해주세요.");
                    }
                });
            });
        });
    </script>
</body>
</html>
