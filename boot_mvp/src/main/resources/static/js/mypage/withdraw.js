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
        $(".section").addClass("blur-background");
        $(this).parents().siblings(".popUp").show();
        

        $(".popUp .submit").on("click",function(e){
            e.preventDefault();
            console.log("탈퇴팝업 예 누름");

            var id = document.getElementById('id').value;
            var password = document.getElementById('password').value;
            console.log(id);
            console.log(password);
            
            var login_id = '<%=(String)session.getAttribute("login_id")%>';
            var login_pw = '<%=(String)session.getAttribute("login_pw")%>';
            console.log(login_pw);
            console.log(login_id);

            if(!id){
                alert("아이디를 입력해주세요.");
            }else if(!password){
                alert("비밀번호를 입력해주세요.");
            }else if(id != login_id){
                alert("회원 아이디가 일치하지 않습니다.");
                alert("입력한 id"+id+"세션아이디"+login_id);
            }else if(password != login_pw){
                alert("비밀번호가 일치하지 않습니다.")
            }else{
                var withdrawForm = $("#withdrawForm");
                withdrawForm.attr("action","delUserInfo").submit();//삭제할 회원정보가지고 콘트롤러단으로
                
            }

        }); 
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