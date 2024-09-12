function reviewshow2(movieno, pageNum) 
{
		const csrfToken = document.getElementById('token').value;
		var movieno = $("#movieno").val();	
		
        $.ajax({
           url : '/movie/review2',
           type : 'POST',
           data : 
		   {
              'movieno' : movieno,
              'page' : pageNum
           },
			  headers: 
			  {
				   "X-CSRF-TOKEN": csrfToken
			  },
           dataType:"json",
           success : function(data) {
			console.log('data: ' + JSON.stringify(data));
              var a = '';
              var page = data.page;
              var startpage = data.startpage;
              var endpage = data.endpage;
              var reviewList = data.reviewList;
			  var uuid2 = data.uuid2;			  
			  var reviewNum = data.reviewNum;	
			  var count = data.count;	
			  var movieInfo = data.movieInfo;	
			  	 
              $.each(reviewList, function(key, value) {
                 console.log("data : " + reviewList);
                 console.log(reviewList);
                 console.log(page + "," + startpage + "," + endpage);
                 console.log("start : " + startpage);
                 console.log("end : " + endpage);
				 console.log("uuid2: " + uuid2);
                 a += '<div class="reviewContentWrap">';
                 a += '<div class="reviewContent">';
	                 a += '<div class="idWrap">';
	                 a += '<span class="material-symbols-outlined">account_circle</span>';
					 if(value.userid.length > 4) 
					 {
					     var first = value.userid.substring(0, 4);
					     var remain = value.userid.length - 4;
					     var byul = "*".repeat(remain);
					     var maskedId = first + byul;
					     a += '<h5>' + maskedId + '</h5>';
					 } else {
					     a += '<h5>' + value.userid + '</h5>';
					 }
					 a += '</div>';
				 a += '<div class="review">';
				 a += '<div class="left">';
				 a += '<h5 class="leftTitle">관람평</h5>';	
				 a += '<p class="reviewCon">'+value.review+'</p>';				 
                 a += '</div>';
				 a += '<div class="right">';
				 a += '<span class="material-symbols-outlined">stars</span>';
				 a += '<h5>'+value.star+'</h5>';
				 // uuid가 일치하는 경우에만 수정/삭제 버튼 표시
				 if(value.uuid === uuid2) 
				{
				     a += '<span class="material-symbols-outlined optionReviewB" style="cursor:pointer">more_vert</span>';
				     a += '<ul class="optionReview">';
				     a += '<li class="modify-pop">수정하기</li>';
				     a += '<li class="delete-review">삭제하기</li>';
				     a += '</ul>';
				 }
				 a += '</div>';
				 a += '</div>';
                 a += '</div>';
                 a += '</div>';
              });
              
			  $('.content.reviewDetailInfo .reviewWrapTitle span.reviewNum').html(reviewNum);
			  $('.content.movieDetailInfo .reviewWrapTitle span.reviewNum').html(reviewNum);
			  
			  var reviewWrapH = '<div class="reviewWrapH">';
			  var h ="";
			  h+='<h5 class="reviewWrapTitle"><span class="Strong">'+movieInfo.movienm+'</span> 에 대한 <span class="Strong reviewNum">'+reviewNum+'</span>개의 관람평이 있어요!</h5>';
			  h+='<div class="write">';
			  if(count==0) 
			  {
					h+='<span class="material-symbols-outlined open-pop">edit_square</span>';
					h+='<h5 class="open-pop">관람평 작성하기</h5>';					
			   }
			   else 
			   {
					h+='<h5>이미 등록된 관람평이 있습니다.</h5>';	
			   }		  		  
			  h+='</div>';
			  h+='</div>';

			  
			  // 페이지 버튼 생성
			  var pagingHtml = '<div class="pagingWrap">';
			  for (var num = startpage; num <= endpage; num++) {
			      if (num == page) {
			          pagingHtml += '<a href="#" onclick="reviewshow2(\'' + movieno + '\', ' + num + '); return false;" class="page-btn current">' + num + '</a>';
			      } else {
			          pagingHtml += '<a href="#" onclick="reviewshow2(\'' + movieno + '\', ' + num + '); return false;" class="page-btn">' + num + '</a>';
			      }
			  }
			  pagingHtml += '</div>'; // pagingWrap 종료

			  // 결과 HTML 업데이트
			  $('.content.reviewDetailInfo > .reviewWrap').html(reviewWrapH+h+a + pagingHtml);
         }
      });
}

	$(document).ready(function()
	{
		$(document).on('click', '.open-pop', function() {
			$('.pop.write').css({"display":"flex"});
		});
		
		$(document).on('click', '.modify-pop', function() {
			$('.pop.write').css({"display":"flex"});
			$('.popTitle').text("관람평 수정하기");
			$('.optionReview').removeClass("on");
			
			$('input.button.submit').removeClass("submit").addClass("modify");

			// onclick 속성 변경
			$('input.button.modify').attr("onclick", "modifyReview2()");

			// value (버튼 텍스트)도 변경할 수 있습니다 (선택 사항)
			$('input.button.modify').val("수정");			
		});
		
		$(document).on('click', '.delete-review', function() {	
			$('.pop.delete').css({"display":"flex"});
			$('.popTitle').text("관람평 등록하기");
			$('.optionReview').removeClass("on");
			
			$('input.button.submit').removeClass("modify").addClass("submit");

			// onclick 속성 변경
			$('input.button.modify').attr("onclick", "insertReview2()");

			// value (버튼 텍스트)도 변경할 수 있습니다 (선택 사항)
			$('input.button.modify').val("등록");			
		});
		
		$(document).on('click', '.close-pop', function() {
			closePop();
		});		

		$(document).on('click', '.optionReviewB', function() {
		    var $optionReview = $(this).next('.optionReview');  // 바로 다음에 있는 .optionReview 찾기
		    if ($optionReview.length > 0) {
		        $optionReview.toggle();  // .optionReview의 display 속성을 토글
		    }
		});
				
		let stars = document.querySelectorAll('.star-icon');
		let result = document.getElementById('selected-rating');
		let selectedValue = 0;  // 선택된 별의 값을 저장

		stars.forEach((star, index) =>
		{
		    // 마우스 오버 시 실행되는 함수
		    star.addEventListener('mouseover', function() 
		    {
		        stars.forEach((s, i) => 
		        {
		            if (i <= index) {
		                s.classList.add('filled');  // filled 클래스 추가
		                s.style.opacity = 0.5;  // 투명도 적용
		            } else {
		                s.classList.remove('filled');  // filled 클래스 제거
		                s.style.opacity = 1;  // 투명도 초기화
		            }
		        });
		    });

		    // 마우스가 별에서 나갔을 때 투명도 초기화
		    star.addEventListener('mouseout', function() 
		    {
		        stars.forEach((s, i) => 
		        {
		            if (i < selectedValue) 
		            {
		                s.style.opacity = 1;  // 선택된 별들은 투명도 유지
		            } 
		            else 
		            {
		                s.classList.remove('filled');  // 선택되지 않은 별의 filled 클래스 제거
		                s.style.opacity = 1;  // 투명도 초기화
		            }
		        });
		    });

		    // 별 클릭 시 실행되는 함수
		    star.addEventListener('click', function()
		    {
		        selectedValue = index + 1;  // 선택된 값 설정

		        stars.forEach((s, i) =>
		        {
		            if (i < selectedValue) 
		            {
		                s.classList.add('filled');  // 선택된 별까지 filled 클래스 추가
		            } 
		            else 
		            {
		                s.classList.remove('filled');  // 선택되지 않은 별의 filled 클래스 제거
		            }
		        });

		        // 선택한 값을 출력하는 로직 (필요에 따라 추가)
		        result.textContent = selectedValue;
		        console.log(selectedValue);
		    });
		});
	});
	
	function insertReview2() 
	{
		const csrfToken = document.getElementById('token').value;
		var star = $("input[name='rating']:checked").val();
		var movieno = $("#movieno").val();	
		console.log("@#@# movieno==>"+movieno);
		var textArea = $("#textArea").val();
		alert("관람평이 등록되셨습니다.");
		$.ajax
		({
			url: "/movie/insertReview",
			type: "POST",
			data: {movieno: movieno, star:star, review:textArea},
			headers: {
			    "X-CSRF-TOKEN": csrfToken
			},
			success:function(result) 
			{
				console.log("성공");	
				closePop();
				reviewshow();				
				reviewshow2();
			},
			error:function() 
			{
				console.log("ajax 오류");
			}	
		});
	}
	
	function modifyReview2() 
	{
		const csrfToken = document.getElementById('token').value;
		var star = $("input[name='rating']:checked").val();
		var movieno = $("#movieno").val();	
		console.log("@#@# movieno==>"+movieno);
		var textArea = $("#textArea").val();
		alert("관람평을 수정하셨습니다.");
		$.ajax
		({
			url: "/movie/modifyReview",
			type: "POST",
			data: {movieno: movieno, star:star, review:textArea},
			headers: {
			    "X-CSRF-TOKEN": csrfToken
			},
			success:function(result) 
			{
				console.log("성공");	
				closePop();
				reviewshow();
				reviewshow2();						
			},
			error:function() 
			{
				console.log("ajax 오류");
			}	
		});
	}	

	function deleteReview2() 
	{
		const csrfToken = document.getElementById('token').value;
		var movieno = $("#movieno").val();	
		alert("관람평이 삭제되셨습니다.");
		$.ajax
		({
			url: "/movie/deleteReview",
			type: "POST",
			data: {movieno: movieno},
			headers: {
			    "X-CSRF-TOKEN": csrfToken
			},
			success:function(result) 
			{
				console.log("성공");	
				closePop();
				reviewshow();
				reviewshow2();							
			},
			error:function() 
			{
				console.log("ajax 오류");
			}	
		});
	}		
		
	// 팝업 닫기
	function closePop() 
	{
		var star = $("input[name='rating']:checked").val();
		var textArea = $("#textArea").val();
		
		$('.pop').css({"display":"none"});
		$("#textArea").val("");
		$("input[name='rating']:checked").prop('checked', false);	
			
		let stars = document.querySelectorAll('.star-icon.filled');
		let result = document.getElementById('selected-rating');
		
		stars.forEach((s, i) =>
		{
        	s.classList.remove('filled');  // 선택되지 않은 별의 filled 클래스 제거
		});
				
		result.textContent = "";	
	}	
	
	
