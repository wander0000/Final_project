$(document).ready(function()
{
    /*
        2024-09-01 서연주 
        서브메뉴 눌렀을 때 메뉴 활성화 : active
    */
    $('.subHeader ul li').click(function(){
        $(this).addClass('active');
        $('.navMenu ul li').not(this).removeClass('active');
    });

	/*
		2024-09-04 서연주
		POPUP > 열기
	*/
	$('.modifyBtn').click(function() {
	    console.log("click");
	    // 버튼의 부모 요소 중 .infoDetail을 찾고, 그 안의 .popUp을 선택하여 보여줌
	    $(this).closest('.infoDetail').find('.popUp').css({"display":"flex"}); 
	});

	
	
	/*
		2024-09-04 서연주
		POPUP > 닫기
	*/
	$('.popUp .icon.cancel').click(function(){
		console.log("click");
		$(this).parents('.popUp').css({"display":"none"}); 
	}); 

	$('button.cancel').click(function(){
		console.log("click");
		$(this).parents('.popUp').css({"display":"none"}); 
	}); 

	/*
		2024-09-04 서연주
		폼 제출 및 이메일 변경 요청
	*/
	$('.updateBtn').click(function(){
	    event.preventDefault(); // 폼 제출 기본 동작을 막음

	    const email = document.getElementById("email").value;
	    const csrfToken  = document.getElementById("token").value;
		console.log("csrfToken:"+csrfToken); 
		
//		const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');

	    // 인증 토큰이 필요하면 헤더에 추가
	    fetch("/api/user/email", {
	        method: "PATCH",
	        headers: {
	            "Content-Type": "application/json",
				'X-CSRF-TOKEN': csrfToken
	        },
	        body: JSON.stringify({ email: email }) // JSON 형식으로 이메일을 보냄
//	        body: JSON.stringify(email) // JSON 형식으로 이메일을 보냄
			
	    })
	    .then(response => {
	        if (response.ok) {
	            return response.text();
	        } else {
	            throw new Error("이메일 변경에 실패했습니다.");
	        }
	    })
	    .then(data => {
	        alert(data); // 성공 메시지 표시
			$(this).parents('.popUp').css({"display":"none"});// 팝업창 닫기
//	        closePopup(); // 팝업창 닫기
	    })
	    .catch(error => {
	        console.error("에러 발생:", error);
	        alert(error.message);
	    });
	});
	


});// document ready 끝