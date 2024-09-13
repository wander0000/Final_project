// DOMContentLoaded 이벤트가 발생한 후 스크립트를 실행합니다.
document.addEventListener('DOMContentLoaded', function() {
    // 모든 체크박스와 레이블을 선택합니다.
    const checkboxes = document.querySelectorAll('.genrebut');
    const labels = document.querySelectorAll('.genrebutlabel');

    // 각 체크박스에 대해 이벤트 리스너를 추가합니다.
    checkboxes.forEach((checkbox, index) => {
        checkbox.addEventListener('change', function() {
            if (checkbox.checked) {
                // 체크된 경우 스타일을 적용합니다.
                labels[index].style.backgroundColor = '#F3B931';
                labels[index].style.color = '#000000';
                labels[index].style.fontWeight = 500;
            } else {
                // 체크 해제된 경우 기본 스타일로 되돌립니다.
                labels[index].style.backgroundColor = 'rgba(153, 153, 153, 0.2)';
                labels[index].style.color = '#99A2B9';
                labels[index].style.fontWeight = 500;
            }
        });
    });
});



$(document).ready(function() {
    // 로그인 하러가기 버튼 클릭 시 /login으로 이동
    $('#loginButton').on('click', function() {
        window.location.href = "/login";
    });

    // 폼 제출 시 장르 선택 확인 및 섹션 전환
    $('form').on('submit', function(event) {
        var selectedGenres = $('input[name="genres"]:checked').length;

        // 3개 미만일 경우 경고 메시지를 띄우고 폼 제출을 막음
        if (selectedGenres < 3) {
            alert('최소 3개의 장르를 선택해 주세요.');
            event.preventDefault(); // 폼 제출 방지
        } else {
            // 기본 폼 제출 방지
            event.preventDefault();

            // 폼 데이터를 직렬화하여 AJAX로 전송
            $.ajax({
                type: 'POST',
				url: contextPath + "/oauthSignupSubmit2", // 경로 확인
                data: $(this).serialize(), // 폼 데이터를 직렬화하여 전송
                success: function(response) {
                    // 성공 시 처리
                    $('.loginWrap').hide(); 
                    $('.loginWrap2').show(); 
                },
				error: function(xhr, status, error) {
				    console.log("Error Status: " + status);
				    console.log("Error Thrown: " + error);
				    console.log("XHR Response: " + xhr.responseText);
				    alert('폼 제출에 실패했습니다. 다시 시도해 주세요.');
				}
            });
        }
    });
});
