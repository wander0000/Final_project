function getCsrfToken() {
    return $("#csrf").val();
}

function dis_pay_button() {
	alert('결제 방법을 선택하세요.');
	return false;
}

function switchbutton(page) { //결제 방법에 따른 결제 버튼 변환
	$.ajax({
		type: 'post',
		url: page+"_ajax",
		headers: {
			'X-CSRF-TOKEN': getCsrfToken() // CSRF 토큰 추가
		},
		success: function(response) {
			//console.log("서버에서 반환된 HTML:", response);
			$("#paybutton").html(response);
		}, error: function(xhr, status, error) {
            // 오류가 발생했을 때 처리할 내용
            console.error("AJAX 요청 실패:", xhr.status, status, error);
        }
	});
}