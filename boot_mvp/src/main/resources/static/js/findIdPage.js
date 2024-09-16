$(document).ready(function() {
    // 확인 버튼 클릭 시 서버로 요청
    $('#checkIdButton').on('click', function() {
        var name = $('#name').val();
        var email = $('#email').val();

        $.ajax({
            url: contextPath + "/userid", // contextPath 사용
            type: "GET",
            data: { pname: name, email: email },
            success: function(response) {
                // 성공적인 응답일 때
                $('#resultTitle').html('회원님의 아이디는<br>' + response + ' 입니다');
                $('#loginWrap').hide();  // 폼 섹션 숨기기
                $('#resultWrap').show();  // 결과 섹션 표시
                $('#closeModalButton').val('로그인 하러가기');
                $('#closeModalButton').off('click').on('click', function() {
                    window.location.href = contextPath + "/login";
                });
            },
            error: function(xhr) {
                if (xhr.status === 404) {
                    $('#resultTitle').text('일치하는 아이디가 없습니다');
                    $('#loginWrap').hide();  // 폼 섹션 숨기기
                    $('#resultWrap').show();  // 결과 섹션 표시
                    $('#closeModalButton').val('확인');
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
