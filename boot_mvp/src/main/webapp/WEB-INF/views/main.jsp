<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MainPage</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
	
	<!-- import pretendard font -->
	<link rel="stylesheet" type="text/css" href='https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/static/pretendard.css'>  

    <!-- google font -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />    
	
    <!-- import font-awesome, line-awesome -->
    <!-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/board_detail.css"> -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/line-awesome/1.3.0/line-awesome/css/line-awesome.min.css">
	
    <!-- swiper-->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css">
	
    <!-- import js -->
    <script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>   
	
	<!-- AOS 라이브러리 불러오기-->
    <link rel="stylesheet" href="https://unpkg.com/aos@2.3.1/dist/aos.css"> 
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
    <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script> 

 
</head>
<body>
   <!-- <%@ include file="header.jsp" %>	-->	
   <input type="hidden" id="token" name="${_csrf}" value="${_csrf.token}"/>
   <div class="recommendPop"></div>
	<section id="section" class="section">        
        <div class="swiper mySwiper">
            <div id="mainSlider" class="swiper-wrapper">
				<c:forEach items="${boxOffice}" var="dto">
					<div class="swiper-slide">
						<img src="${dto.moviebackimg}" alt="${dto.movienm}">
					</div>
				</c:forEach>
			</div>
            <!-- 네비게이션 버튼 지정 -->
<!--        <div class="swiper-button-next"></div>
            <div class="swiper-button-prev"></div>-->
            <!-- 페이징 -->
            <div class="swiper-pagination"></div>
        </div><!-- swiper mySwiper 끝-->
		
		<div class="movieList">
			
            <div class="tabWrap">
                <div class="status">
					<h5 class="tab active">
                        박스오피스
                    </h5>
                    <h5 class="tab">
                        현재 상영 영화
                    </h5>
                    <h5 class="tab">
                        상영 예정작
                    </h5>
                </div>
                <h5 class="more">
                    <a href="../movie/movie">영화 더보기</a>                    
                </h5>  				            
            </div><!-- tabWrap 끝-->  
			<div class="ranking">
				<div class="rankingCon boxOffice on">
					<c:forEach items="${boxOffice}" var="dto" begin="0" end="3">
						<div class="movieDiv">
							<img src="${dto.moviepostimg}" alt="${dto.movienm}">
							<div class="movieInfo">
								<h5 class="movienm">${dto.movienm}</h5>
								<div class="option">
									<h5 class="releaseday">${dto.openday}<h5>											
								</div>
								<div class="buttonWrap">
									<button class="active" onclick="location.href='/ticketing/move_ticketing?movieno=${dto.movieno}'">예매하기</button>									
									<button onclick="location.href='../movie/movieInfo?movieno=${dto.movieno}'">상세보기</button>
								</div>
							</div>
						</div>
					</c:forEach>
				</div><!-- boxOffice 끝-->	
				
				<div class="rankingCon PlayingList">
					<c:forEach items="${moviePlayingList}" var="dto" begin="0" end="3">
						<div class="movieDiv">
							<img src="${dto.moviepostimg}" alt="${dto.movienm}">
							<div class="movieInfo">
								<h5 class="movienm">${dto.movienm}</h5>
								<div class="option">
									<h5 class="releaseday">${dto.openday}<h5>											
								</div>
								<div class="buttonWrap">
									<button class="active" onclick="location.href=''">예매하기</button>
									<button onclick="location.href='../movie/movieInfo?movieno=${dto.movieno}'">상세보기</button>
								</div>
							</div>
						</div>
					</c:forEach>
				</div><!-- PlayingList 끝-->
				
				<div class="rankingCon UpcomingList">
					<c:forEach items="${movieUpcomingList}" var="dto" begin="0" end="3">
						<div class="movieDiv">
							<img src="${dto.moviepostimg}" alt="${dto.movienm}">
							<div class="movieInfo">
								<h5 class="movienm">${dto.movienm}</h5>
								<div class="option">
									<h5 class="releaseday">${dto.openday}</h5>											
									<h5 class="difference active">개봉 D-${dto.daysDifference}</h5>											
								</div>								
								<div class="buttonWrap">
									<button class="active" onclick="location.href=''">예매하기</button>
									<button onclick="location.href='../movie/movieInfo?movieno=${dto.movieno}'">상세보기</button>
								</div>
							</div>
						</div>							
					</c:forEach>
				</div> <!-- UpcomingList 끝-->					
			</div>  <!-- ranking 끝-->
		</div> <!-- movieList 끝--> 
		<div class="benefit">
			<div class="tabWrap">
		        <div class="status">
					<h5 class="tabBenefit active">
		                혜택
		            </h5>		                    
		        </div>
		        <h5 class="more">
		            <a href="/benefit">혜택 더보기</a>                    
		        </h5>  				            
		    </div><!-- tabWrap 끝-->
			<div class="benefitImg">
				<div class="bImg">
					<a href="/benefit">    
						<img src="${pageContext.request.contextPath}/images/benefit2.png">
					</a>
				</div>
				<div class="bImg">
					<a href="/benefit">    
						<img src="${pageContext.request.contextPath}/images/benefit1.png" >
					</a>
				</div>
			</div> 
		</div> <!-- benefit 끝-->
		<!-- 팝업창 (숨겨진 상태로 시작) -->
		<div class="popUp attend">
			<div class="popBg">
				<div class="popCon">
					<div class="popH">
	                    <h4 class="popTitle">매일이 혜택! 출석체크!</h4>
	                    <span class="material-symbols-outlined cancelPOP">close</span>
		            </div> <!-- popH 끝 -->
					<div class="popM">
<!--	                    <h5 class="popAlert">로그인하면 자동으로 출석이벤트에 참여됩니다.</h5>-->
<!--	                    <h5 class="popAlert">매일 로그인하세요~ 10포인트 지급</h5>-->
<!--	                    <h5 class="popAlert">만근하면 5% 할인쿠폰 지급</h5>-->
						<button id="checkAttendanceStatus">출석 현황 확인</button>
	                </div>
					<div class="popB">
						<div class="checkCon">
					         <input type="checkbox" id="dontShowTodayCheckbox">
							 <label for="dontShowTodayCheckbox">오늘은 더 이상 보지 않기</label>
						</div>
						<div class="checkCon">
					         <input type="checkbox" id="closePopup">
							 <label for="closePopup">닫기</label>
						</div>
					</div>   
				</div><!-- popCon 끝 -->
			</div><!-- popBg 끝 -->
		</div><!-- popUp 끝 -->
			
    </section>
	<!--model.addAttribute("boxOffice", boxDTO);
	model.addAttribute("movie", movieDTO);-->
	
	<%@ include file="footer.jsp" %>	
</body>
</html>

<script>
	//페이지가 완전히 로드된 후 실행되는 함수
	window.onload = function() 
	{
		recommendPop();
	};		
	
	const tabs = document.querySelectorAll('.tab');
	const rankingCons = document.querySelectorAll('.rankingCon');
	
	tabs.forEach((tab, index) => {
	  tab.addEventListener("click", () => {
	    // 모든 탭에서 active 클래스를 제거
	    tabs.forEach(t => t.classList.remove('active'));

	    // 클릭한 탭에만 active 클래스를 추가
	    tab.classList.add('active');

	    // 모든 rankingCon에서 on 클래스를 제거
	    rankingCons.forEach(rankingCon => rankingCon.classList.remove('on'));

	    // 클릭한 탭에 대응하는 rankingCon에 on 클래스를 추가
	    rankingCons[index].classList.add('on');
	  });
	});
	
	function recommendPop() 
	{
		const csrfToken = document.getElementById('token').value;	
		$.ajax
		({
			url:"/recommendPop",
			type:"GET",
			headers: {
			    "X-CSRF-TOKEN": csrfToken
			},
			success:function(result) 
			{
				console.log(result.uuid);
				if (typeof result !== 'object') 
				{
				    $('.recommendPop').css({"display": "none"});
				    console.log("로그인하지 않은 사용자입니다.");
				    return;
				}
				
				var str ='';
				str+=' <div class="popInner">';
				str+='<div class="popButtonWrap">';
				str+='<div class="buttonArrow arrowLeft">';
				str+='<span class="material-symbols-outlined ">arrow_back_ios</span>';
				str+='</div>';
				str+='<div class="buttonArrow arrowRight">';
				str+='<span class="material-symbols-outlined ">arrow_forward_ios</span>';
				str+='</div>';
				str+='</div>';									
				str+='<div class="popH">';
				str+='<h5 class="popTitle">당신을 위한 추천 영화</h5>';
				str+='<span class="material-symbols-outlined cancelPOP">close</span>';
				str+='</div>';
				str+='<div class="popB">';

				
				result.forEach(function(movie, index)
				{					
					str += '<div class="imgCon" style="--i:' + index + ';">';  // index를 --i에 할당			    
					str+='<img src="'+movie.moviepostimg+'">';			    
					str+='</div>';		    
				    // 생성한 요소를 영화 리스트 컨테이너에 추가				    
				});
				
				str+='</div>';	
				str+='</div>';	
				$('.recommendPop').html(str);	
				
				// 추천 슬라이드 부분
				let imgs = document.querySelectorAll('.imgCon');
				let currentIndex = 0;

				let popB = document.querySelector('.popB');
				let prev = document.querySelector('.buttonArrow.arrowLeft');
				let next = document.querySelector('.buttonArrow.arrowRight');

				function updateButtons() 
				{
				    prev.style.background = currentIndex === 0 ? '#2b2b2b' : ''; // 비활성화 색상
				    prev.style.cursor = currentIndex === 0 ? 'inherit' : ''; // 비활성화 색상
				    next.style.background = currentIndex === imgs.length - 1 ? '#2b2b2b' : ''; // 비활성화 색상
				    next.style.cursor = currentIndex === imgs.length - 1 ? 'inherit' : ''; // 비활성화 색상
				    prev.disabled = currentIndex === 0; // 버튼 비활성화
				    next.disabled = currentIndex === imgs.length - 1; // 버튼 비활성화
				}

				if (prev && next) 
				{
				    next.addEventListener('click', function() {
				        if (currentIndex < imgs.length - 1) 
						{
				            currentIndex++;
				            popB.style.transform = 'translateX(-' + (currentIndex * 400) + 'px)';
				            updateButtons(); // 버튼 상태 업데이트
				        }
				    });

				    prev.addEventListener('click', function() {
				        if (currentIndex > 0) {
				            currentIndex--;
				            popB.style.transform = 'translateX(-' + (currentIndex * 400) + 'px)';
				            updateButtons(); // 버튼 상태 업데이트
				        }
				    });

				    updateButtons(); // 초기 버튼 상태 업데이트
				} else {
				    console.error("버튼 요소를 찾을 수 없습니다.");
				}					
				
			},
			error:function() 
			{
			    console.log("ajax 오류");
				$('.recommendPop').css({"display": "none"});
			}	
		});
	} // recommendPop 끝
	


</script>
<script>
	$(document).on('click', '.recommendPop .popInner .popH span.cancelPOP', function() 
	{
	    $('.recommendPop').css({"display": "none"});
	});	
		
    var swiper = new Swiper(".mySwiper",     
    {            
        loopedSlides: 10,
        slidesPerView: 3,
        spaceBetween: 40,
        loopedSlides: 3,
        loop:true,
        autoplay: 
        {
            delay: 3000, // 3초마다 슬라이드 이동
            disableOnInteraction: false, // 사용자가 상호작용한 후에도 자동 재생 유지
        },
        navigation : 
        {
            nextEl : '.swiper-button-next', // 다음 버튼 클래스명
            prevEl : '.swiper-button-prev', // 이번 버튼 클래스명
        },
        pagination: 
        {
            el: ".swiper-pagination",
			clickable: true
        }        
    });
</script>
<script>
    /*
    240917.서연주 
    출석이벤트 팝업, 쿠키생성 
    */
    document.addEventListener('DOMContentLoaded', function () {
		
		console.log("현재 쿠키: " + document.cookie);
        // 쿠키 값 확인
        let showAttendancePopup = document.cookie
          .split('; ')
          .find(row => row.startsWith('showAttendancePopup='))
          ?.split('=')[1];
        
        console.log("showAttendancePopup값은? " + showAttendancePopup);

        // 변수 이름 통일
        if (showAttendancePopup === 'true') {
            // 출석 팝업 표시
            $('.popUp.attend').css({"display":"flex"});
        }
		console.log($('.popUp.attend').css("display"));  // 팝업의 display 상태를 확인
		
		

		// 자정까지 남은 시간(초)을 계산하는 함수
		function getSecondsUntilMidnight() {
		    var now = new Date();
		    var midnight = new Date(now.getFullYear(), now.getMonth(), now.getDate() + 1, 0, 0, 0);
		    return Math.floor((midnight - now) / 1000);
		}

		// 자정까지 남은 시간을 자바스크립트 변수로 설정
		var secondsUntilMidnight = getSecondsUntilMidnight();

        // 팝업 닫기 버튼 클릭 이벤트
		$(''.popUp.attend .cancelPOP, .popUp.attend #closePopup').click(function() {
		    console.log("닫기버튼이나 x표 click");
			
			// 체크박스가 체크된 경우 쿠키 설정
            if (document.getElementById('dontShowTodayCheckbox').checked) {
                 document.cookie = "showAttendancePopup=false; path=/; max-age=" + secondsUntilMidnight + ";";; // 자정까지 도메인의 모든 경로에서 쿠키가 유효
            }
		    $(this).parents('.popUp.attend').css({"display":"none"}); 
		});
		
		
		
		//출석현황 버튼 누르면 마이페이지 메인으로가기
		document.getElementById('checkAttendanceStatus').addEventListener('click', function() {
		    window.location.href = "${pageContext.request.contextPath}/mypage";
		});
      
    });
</script>
