<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <!--<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MainPage</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/movieInfo.css">
	
	 import pretendard font 
	<link rel="stylesheet" type="text/css" href='https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/static/pretendard.css'>  

     google font 
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />    
	
     import font-awesome, line-awesome 
     <link rel="stylesheet" href="${pageContext.request.contextPath}/css/board_detail.css"> 
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/line-awesome/1.3.0/line-awesome/css/line-awesome.min.css">
	
     swiper
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css">
	
     import js 
    <script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>   
	
	 AOS 라이브러리 불러오기
    <link rel="stylesheet" href="https://unpkg.com/aos@2.3.1/dist/aos.css"> 
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
    <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script> --> 
	
</head>
<body>
	<!-- 입력 팝업 -->
	
	<div class="pop write2">
	    <div class="popInner">
	        <div class="popH">
				<h5 class="popTitle">관람평 작성하기</h5>
				<span class="material-symbols-outlined close-pop2" style="cursor:pointer">close</span>					
	        </div>
	        <div class="popB">
	            <div class="popMovienm">
	                ${movieInfo.movienm}<br>
	                <span style="font-size: 18px; color: #111;">영화 어떠셨나요?<span>
	            </div>
	            <div class="popRating">
	                <div class="rating">
	                    <label class="rating__label rating__label--half" for="starhalf6">
	                        <input type="radio" id="starhalf6" class="rating__input" name="rating2" value="1">
	                        <span class="star-icon one">
	                        </span>
	                    </label>
	                    <label class="rating__label rating__label--full" for="star6">
	                        <input type="radio" id="star6" class="rating__input" name="rating2" value="2">
	                        <span class="star-icon one">
	                        </span>
	                    </label>
	                    <label class="rating__label rating__label--half" for="star1half7">
	                        <input type="radio" id="star1half7" class="rating__input" name="rating2" value="3">
	                        <span class="star-icon one"></span>
	                    </label>
	                    <label class="rating__label rating__label--full" for="star7">
	                        <input type="radio" id="star7" class="rating__input" name="rating2" value="4">
	                        <span class="star-icon one"></span>
	                    </label>
	                    <label class="rating__label rating__label--half" for="star2half8">
	                        <input type="radio" id="star2half8" class="rating__input" name="rating2" value="5">
	                        <span class="star-icon one"></span>
	                    </label>
	                    <label class="rating__label rating__label--full" for="star8">
	                        <input type="radio" id="star8" class="rating__input" name="rating2" value="6">
	                        <span class="star-icon one"></span>
	                    </label>
	                    <label class="rating__label rating__label--half" for="star3half9">
	                        <input type="radio" id="star3half9" class="rating__input" name="rating2" value="7">
	                        <span class="star-icon one"></span>
	                    </label>
	                    <label class="rating__label rating__label--full" for="star9">
	                        <input type="radio" id="star9" class="rating__input" name="rating2" value="8">
	                        <span class="star-icon one"></span>
	                    </label>
	                    <label class="rating__label rating__label--half" for="star4hal10">
	                        <input type="radio" id="star4half10" class="rating__input" name="rating2" value="9">
	                        <span class="star-icon one"></span>
	                    </label>
	                    <label class="rating__label rating__label--full" for="star10">
	                        <input type="radio" id="star10" class="rating__input" name="rating2" value="10">
	                        <span class="star-icon one"></span>
	                    </label>
	                </div>
	                <div class="rating-result"><span id="selected-rating2"></span>점</div>
	            </div>
	            <div class="textAreaWrap">
	                <textarea name="" id="textArea2" placeholder="실관람평을 남겨주세요."></textarea>
	            </div>
	        </div>
	        <div class="popF">
	            <div class="buttonWrap">
	                <input class="button cancel close-pop2" value="취소">
	                <input class="button submit" value="등록" onclick="insertReview2();">
	            </div>
	        </div>                
	    </div>        
	</div>
	
	
	<!-- 삭제 팝업-->
	<div class="pop delete2">
	    <div class="popInner">
	        <div class="popH">
				<h5 class="popTitle">관람평 삭제하기</h5>
				<span class="material-symbols-outlined close-pop" style="cursor:pointer">close</span>					
	        </div>
	        <div class="popB">
	            <div class="popMovienm">
	                정말로 삭제하시겠습니까?
	            </div>		            
	        </div>
	        <div class="popF">
	            <div class="buttonWrap">
	                <input class="button cancel close-pop" value="취소">
	                <input class="button delete" value="삭제" onclick="deleteReview();">
	            </div>
	        </div>                
	    </div>        
	</div>	
		 

	
	<div class="reviewContentWrap">
		<div class="reviewWrapH">
			<h5 class="reviewWrapTitle"><span class="Strong">${movieInfo.movienm}</span> 에 대한 <span class="Strong ">${reviewNum}</span>개의 관람평이 있어요!</h5>
			<div class="write">			
				<c:if test="${count==0}">
					<span class="material-symbols-outlined open-pop2">edit_square</span>
					<h5 class="open-pop2">관람평 작성하기</h5>	
				</c:if>	
				<c:if test="${count!=0}">
					<h5>이미 등록된 관람평이 있습니다.</h5>	
				</c:if>											
			</div>
		</div> <!-- reviewWrapH 끝-->
		<c:forEach items="${reviewInfo}" var="dto" begin="0" end="4"> 
			<div class="reviewContent">
				<div class="idWrap">
					<span class="material-symbols-outlined">account_circle</span>
					<h5>
						<c:choose>
							<c:when test="${fn:length(dto.userid)>4}">
								<c:set var="first" value="${fn:substring(dto.userid,1,4)}"/>
								<c:set var="remain" value="${fn:length(dto.userid)-4}"/>
								<c:set var="byul" value="${fn:substring('******************',1,remain)}"/>
								<c:set var="id" value="${first}${byul}"/>
								${id}
							</c:when>
							<c:otherwise>
								${dto.userid}
							</c:otherwise>
						</c:choose>
					</h5>
				</div>
				<div class="review">								
					<div class="left">
						<h5 class="leftTitle">관람평</h5>
						<p class="reviewCon">${dto.review}</p>
					</div>
					<div class="right">
						<span class="material-symbols-outlined">stars</span>
						<h5 class="starNum">${dto.star}</h5>
						<c:if test="${dto.uuid == uuid}">
							<span class="material-symbols-outlined optionReviewB" style="cursor:pointer">more_vert</span>
							<ul class="optionReview">
								<li class="modify-pop2">수정하기</li>
								<li class="delete-review2">삭제하기</li>
							</ul>
						</c:if>
					</div>
				</div>
			</div> <!-- reviewContent 끝-->
		</c:forEach>
	</div> <!-- reviewContentWrap 끝-->		
</body>		
</html>
<script>
	$(document).ready(function()
	{
		$('.open-pop2').click(function(){
			$('.pop.write2').css({"display":"flex"});
		});
		
		$('.modify-pop2').click(function(){
			$('.pop.write2').css({"display":"flex"});
			$('.popTitle').text("관람평 수정하기");
			$('.optionReview').removeClass("on");
			
			$('input.button.submit').removeClass("submit").addClass("modify");

			// onclick 속성 변경
			$('input.button.modify').attr("onclick", "modifyReview2()");

			// value (버튼 텍스트)도 변경할 수 있습니다 (선택 사항)
			$('input.button.modify').val("수정");			
		});
		
		$('.delete-review2').click(function(){
			$('.pop.delete2').css({"display":"flex"});
			$('.popTitle').text("관람평 수정하기");
			$('.optionReview').removeClass("on");
			
			$('input.button.submit').removeClass("submit").addClass("modify");

			// onclick 속성 변경
			$('input.button.modify').attr("onclick", "modifyReview2()");

			// value (버튼 텍스트)도 변경할 수 있습니다 (선택 사항)
			$('input.button.modify').val("수정");			
		});
		
		$('.close-pop2').click(function(){
			closePop();
		});		

		$('.optionReviewB').click(function(){
			$(this).closest('.optionReview').toggleClass("on");
		});
				
		let stars2 = document.querySelectorAll('.star-icon.one');
		let result2 = document.getElementById('selected-rating2');
		let selectedValue2 = 0;  // 선택된 별의 값을 저장

		stars2.forEach((star2, index) =>
		{
		    // 마우스 오버 시 실행되는 함수
		    star2.addEventListener('mouseover', function() 
		    {
		        stars2.forEach((s, i) => 
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
		    star2.addEventListener('mouseout', function() 
		    {
		        stars2.forEach((s, i) => 
		        {
		            if (i < selectedValue2) 
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
		    star2.addEventListener('click', function()
		    {
		        selectedValue2 = index + 1;  // 선택된 값 설정

		        stars2.forEach((s, i) =>
		        {
		            if (i < selectedValue2) 
		            {
		                s.classList.add('filled');  // 선택된 별까지 filled 클래스 추가
		            } 
		            else 
		            {
		                s.classList.remove('filled');  // 선택되지 않은 별의 filled 클래스 제거
		            }
		        });

		        // 선택한 값을 출력하는 로직 (필요에 따라 추가)
		        result2.textContent = selectedValue2;
		        console.log(selectedValue2);
		    });
		});
	});
	
	function insertReview2() 
	{
		const csrfToken = document.getElementById('token').value;
		var star2 = $("input[name='rating2']:checked").val();
		var movieno = $("#movieno").val();	
		console.log("@#@# movieno==>"+movieno);
		var textArea2 = $("#textArea2").val();
		
		
		// star 또는 textArea가 비어 있을 경우
		if (!star2) {
		    alert("별점을 선택해 주세요.");
		    return;
		}

		if (!textArea2 || textArea2.trim() === "") {
		    alert("관람평을 작성해 주세요.");
		    return;
		}
		
		alert("관람평이 등록되셨습니다.");
		
		$.ajax
		({
			url: "/movie/insertReview",
			type: "POST",
			data: {movieno: movieno, star:star2, review:textArea2},
			headers: {
			    "X-CSRF-TOKEN": csrfToken
			},
			success:function(result) 
			{
				console.log("성공");				
				closePop();
				reviewshow2();		
				reviewshow();				
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
		var star2 = $("input[name='rating2']:checked").val();
		var movieno = $("#movieno").val();	
		console.log("@#@# movieno==>"+movieno);
		var textArea2 = $("#textArea2").val();
		alert("관람평을 수정하셨습니다.");		
		$.ajax
		({
			url: "/movie/modifyReview",
			type: "POST",
			data: {movieno: movieno, star:star2, review:textArea2},
			headers: {
			    "X-CSRF-TOKEN": csrfToken
			},
			success:function(result) 
			{
				console.log("성공");	
				closePop();
				reviewshow2();		
				reviewshow();
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
				reviewshow2();		
				reviewshow();							
			},
			error:function() 
			{
				console.log("ajax 오류");
			}	
		});
	}		
		
	// 팝업 닫기
	function closePop2() 
	{
		var star2 = $("input[name='rating2']:checked").val();
		var textArea2 = $("#textArea2").val();
		
		$('.pop').css({"display":"none"});
		$("#textArea2").val("");
		$("input[name='rating2']:checked").prop('checked', false);	
			
		let stars2 = document.querySelectorAll('.star-icon.one.filled');
		let result2 = document.getElementById('selected-rating2');
		
		stars2.forEach((s, i) =>
		{
        	s.classList.remove('filled');  // 선택되지 않은 별의 filled 클래스 제거
		});
				
		result2.textContent = "";	
	}	
	
</script>


				