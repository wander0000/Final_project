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
        2024-09-01 서연주
        회원탈퇴 확인 POPUP > 탈퇴완료
    */
    $("button.withdrawButton").on("click",function(e){
        e.preventDefault();
        $(this).parents().siblings(".popUp").show();
        

        $(".popUp .submit").on("click",function(e){
			e.preventDefault();

			// 사용자가 입력한 값 확인
			const confirmationText = $('#confirmationText').val().trim();
			const requiredText = "모든 정보가 영구적으로 삭제됨을 확인했습니다.";

			// 입력된 값이 정확한지 확인
			if (confirmationText !== requiredText) {
			    alert("정확한 문장을 입력해주세요.");
			    $('#confirmationText').focus();
			    return;
			}

			// 문장이 정확하면 사용자 정보 삭제 요청 (REST 방식)
			$.ajax({
			    url: "/api/user", // 사용자 삭제 REST API 경로
			    method: "DELETE",
			    headers: {
			        "X-CSRF-TOKEN": $('#token').val() // CSRF 토큰 헤더에 추가
			    },
			    success: function(response) {
			        alert("회원 정보가 성공적으로 삭제되었습니다.");
			        window.location.href = "/login"; // 탈퇴 후 로그인페이지로
			    },
			    error: function(error) {
			        console.error("에러 발생:", error);
			        alert("회원정보 삭제에 실패했습니다.");
			    }
			});

        }); //.popUp .submit  클릭 끝
    });//button.withdrawButton 클릭 끝
	
	
	// 입력값 유효성 검사
	const requiredText = "모든 정보가 영구적으로 삭제됨을 확인했습니다.";
    const submitBtn = $('#submitBtn');
	
    $('#confirmationText').on('input', function() {
        const inputValue = $(this).val().trim();
        
        if (inputValue === requiredText) {
            // 문장이 정확하면 에러 메시지 숨기고 버튼 활성화
           $('#del_check').css('display', 'none'); // 유효하면 메시지를 숨김
            submitBtn.prop('disabled', false);
        } else {
            // 문장이 정확하지 않으면 에러 메시지 보여주고 버튼 비활성화
           $('#del_check').text('"모든 정보가 영구적으로 삭제됨을 확인했습니다."를 입력하세요');
           $('#del_check').css('display', 'flex');
            submitBtn.prop('disabled', true);
        }
    });
	
	
	
    /*
        2024-09-01 서연주
        회원탈퇴 POPUP > 닫기
    */
    $('.popUp .icon.cancel').click(function(){
        console.log("click");
        $(this).parents('.popUp').css({"display":"none"}); 
    }); 

    $('button.cancel').click(function(){
        console.log("click");
        $(this).parents('.popUp').css({"display":"none"}); 
    }); 



});// document ready 끝