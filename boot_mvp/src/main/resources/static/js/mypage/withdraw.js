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
        // 배경을 흐리게 처리하고, 오버레이 추가
//        $(".section").addClass("blur-background");
        $(this).parents().siblings(".popUp").show();
        

        $(".popUp .submit").on("click",function(e){
            e.preventDefault();
            console.log("탈퇴팝업 예 누름");

           
            if(!id){
                alert("아이디를 입력해주세요.");
            }else if(!password){
                alert("비밀번호를 입력해주세요.");
            }else{
				// AJAX 요청을 회원정보를 삭제(회원id는 서버에서 토큰을 통해 인증된 사용자를 확인 할 수 있으므로 body에 data를 보낼 필욥 없음)
				$.ajax({
				    url: url,
				    method: "DELETE",
				    headers: {
				        "X-CSRF-TOKEN": csrfToken
				    },
				    success: function(data) {
				        alert(data);
				        $(this).parents('.popUp').css({"display":"none"}); 
				    },
				    error: function(xhr, status, error) {
				        console.error("에러 발생:", error);
				        alert(`회원정보 삭제에 실패했습니다: ${error}`);
				    }
				});// AJAX 끝
				
            }

        }); //.popUp .submit  클릭 끝
    });//button.withdrawButton 클릭 끝
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