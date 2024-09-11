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
    <%@ include file="header.jsp" %>		
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
									<button class="active" onclick="location.href=''">예매하기</button>									
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
					<h5 class="tab active">
		                혜택
		            </h5>		                    
		        </div>
		        <h5 class="more">
		            <a href="">혜택 더보기</a>                    
		        </h5>  				            
		    </div><!-- tabWrap 끝-->
			<div class="benefitImg">
				<div class="bImg">
					<img src="${pageContext.request.contextPath}/images/benefit.jpg">
				</div>
				<div class="bImg">
					<img src="${pageContext.request.contextPath}/images/benefit2.png">
				</div>
			</div> 
		</div> <!-- benefit 끝-->
			
    </section>
	<!--model.addAttribute("boxOffice", boxDTO);
	model.addAttribute("movie", movieDTO);-->
	
	<%@ include file="footer.jsp" %>	
</body>
</html>

<script>
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
</script>
<script>    
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