$(document).ready(function() {
    // 비밀번호 변경 유효성 검사
    $('#resetPwForm').on('submit', function(event) {
        const newPassword = $('#newPassword').val();
        const confirmPassword = $('#confirmPassword').val();

        // 유효성 검사
        if (!newPassword) {
            alert('변경할 비밀번호를 입력해주세요.');
            event.preventDefault(); // 폼 제출 방지
            return;
        }

        if (!confirmPassword) {
            alert('비밀번호를 한번 더 입력해주세요.');
            event.preventDefault(); // 폼 제출 방지
            return;
        }

        if (newPassword !== confirmPassword) {
            alert('비밀번호가 일치하지 않습니다.');
            event.preventDefault(); // 폼 제출 방지
            return;
        }

        // 성공 시 팝업 띄우고 폼 제출 막기 (서버로의 실제 전송 방지)
        event.preventDefault(); // 폼 제출 방지
        $('#resetPwForm').hide(); // 폼 숨기기
        $('#resetHeader').hide(); // 비밀번호 재설정 헤더 숨기기
        $('#popupWrap').show(); // 팝업 표시
    });

    // 로그인 페이지로 이동
    $('#goToLoginButton').on('click', function() {
        window.location.href = "/login"; // 로그인 페이지로 이동
    });
});