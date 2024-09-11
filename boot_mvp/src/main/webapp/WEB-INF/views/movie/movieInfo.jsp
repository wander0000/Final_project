<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MainPage</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/movieInfo.css">
	
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
    <%@ include file="../header.jsp" %>		
	<section id="section" class="section">
		<div class="movieList">
			<div class="first">
				<div class="movieInfo">
					<h5 class="difference active">
						<c:choose>
						    <c:when test="${movieInfo.daysDifference == 0}">
						        <style>
						            .difference { display: none; }
						        </style>
						    </c:when>
						    <c:otherwise>
						        <style>
						            .difference { display: inline-block; }
						        </style>
						    </c:otherwise>
						</c:choose>
						예매 D-${movieInfo.daysDifference}
					</h5>
					<div class="titleWrap">
						<h5 class="movienm">${movieInfo.movienm}</h5>
						<div class="like">
							<span class="material-symbols-outlined heart">favorite</span>
							<h5>124</h5>
						</div>
					</div>				
					<h5 class="movieen">${movieInfo.movieen}</h5>
					<div class="info2">
						<h5 class="openday">${movieInfo.openday} 개봉</h5>
						<h5 class="ratingno">${movieInfo.ratingno}</h5>
					</div>
					<div class="info3">
						<div class="genreWrap"></div>
						<div class="times">${movieInfo.times}분</div>	
					</div>
					<div class="info4">
						<c:choose>
						    <c:when test="${movieInfo.audiAcc == 0}">
						        <style>
						            .info4, .showType, .typeNm{ display: none; }
						        </style>
						    </c:when>
						    <c:otherwise>
						        <style>
						            .info4, .showType, .typeNm { display: flex; }
						        </style>
						    </c:otherwise>
						</c:choose>					
						<div class="starWrap">
							<div class="top">
								<span class="material-symbols-outlined">rate_review</span>
								<h5 class="topNm">실관람 평점</h5>							
							</div>
							<h5 class="num">8.6</h5>
						</div>
						<div class="audiAcc">
							<div class="top">
								<span class="material-symbols-outlined">group</span>
								<h5 class="topNm">누적 관객수</h5>							
							</div>
							<h5 class="num">${audiAcc}명</h5>
						</div>	
						<div class="audiAcc">
							<div class="top">
								<span class="material-symbols-outlined">paid</span>
								<h5 class="topNm">누적 매출액</h5>							
							</div>
							<h5 class="num">${salesAcc}원</h5>
						</div>						
					</div>
					<button class="book">예매하기</button>				
				</div> <!-- movieInfo 끝-->
				<div class="post">
					<img src="${movieInfo.moviepostimg}" alt="${dto.movienm}">
				</div>
			</div> <!-- first -->
			<div class="tabWrap">
				<div class="tab mainInfo active">주요 정보</div>
				<div class="tab rating">실관람평</div>
				<div class="tab steelcut">예고편 / 스틸컷</div>
			</div>
			<div class="contentWrap">
				<div class="content movieDetailInfo on">
					<div class="contentTXT">
						<h5 class="showType"><span class="contentT">상영타입 :</span> ${movieInfo.showType}</h5>
						<h5 class="typeNm"><span class="contentT">영화유형 :</span> ${movieInfo.typeNm}</h5>
						<h5 class="genreTxt"><span class="contentT">장르 :</span> ${movieInfo.genre}</h5>
						<h5 class="directornm"><span class="contentT">감독 :</span> ${movieInfo.directornm}</h5>
						<h5 class="actor"><span class="contentT">출연진 :</span> ${movieInfo.actor}</h5>
					</div>
					<div class="overviewWrap">
						<div id="overview"></div>
					</div>	
					<div class="reviewWrap">
						<div class="reviewWrapH">
							<h5 class="reviewWrapTitle"><span class="Strong">${movieInfo.movienm}</span> 에 대한 <span class="Strong">31</span>개의 관람평이 있어요!</h5>
							<div class="write">
								<span class="material-symbols-outlined open-pop">edit_square</span>
								<h5>관람평 작성하기</h5>
								<div class="pop">
								    <div class="popInner">
								        <div class="popH">
								            <h5 class="popTitle">관람평 작성하기</h5>
								            <span class="material-symbols-outlined close-pop">close</span>
								        </div>
								        <div class="popB">
								            <div class="popMovienm">
								                ${movieInfo.movienm}<br>
								                <span style="font-size: 18px; color: #111;">영화 어떠셨나요?<span>
								            </div>
								            <div class="popRating">
								                <div class="rating">
								                    <label class="rating__label rating__label--half" for="starhalf">
								                        <input type="radio" id="starhalf" class="rating__input" name="rating" value="1">
								                        <span class="star-icon">
								                        </span>
								                    </label>
								                    <label class="rating__label rating__label--full" for="star1">
								                        <input type="radio" id="star1" class="rating__input" name="rating" value="2">
								                        <span class="star-icon">
								                        </span>
								                    </label>
								                    <label class="rating__label rating__label--half" for="star1half">
								                        <input type="radio" id="star1half" class="rating__input" name="rating" value="3">
								                        <span class="star-icon"></span>
								                    </label>
								                    <label class="rating__label rating__label--full" for="star2">
								                        <input type="radio" id="star2" class="rating__input" name="rating" value="4">
								                        <span class="star-icon"></span>
								                    </label>
								                    <label class="rating__label rating__label--half" for="star2half">
								                        <input type="radio" id="star2half" class="rating__input" name="rating" value="5">
								                        <span class="star-icon"></span>
								                    </label>
								                    <label class="rating__label rating__label--full" for="star3">
								                        <input type="radio" id="star3" class="rating__input" name="rating" value="6">
								                        <span class="star-icon"></span>
								                    </label>
								                    <label class="rating__label rating__label--half" for="star3half">
								                        <input type="radio" id="star3half" class="rating__input" name="rating" value="7">
								                        <span class="star-icon"></span>
								                    </label>
								                    <label class="rating__label rating__label--full" for="star4">
								                        <input type="radio" id="star4" class="rating__input" name="rating" value="8">
								                        <span class="star-icon"></span>
								                    </label>
								                    <label class="rating__label rating__label--half" for="star4half">
								                        <input type="radio" id="star4half" class="rating__input" name="rating" value="9">
								                        <span class="star-icon"></span>
								                    </label>
								                    <label class="rating__label rating__label--full" for="star5">
								                        <input type="radio" id="star5" class="rating__input" name="rating" value="10">
								                        <span class="star-icon"></span>
								                    </label>
								                </div>
								                <div class="rating-result"><span id="selected-rating"></span>점</div>
								            </div>
								            <div class="textAreaWrap">
								                <textarea name="" id="textArea" placeholder="실관람평을 남겨주세요."></textarea>
								            </div>
								        </div>
								        <div class="popF">
								            <div class="buttonWrap">
												<input class="button cancel cancel-pop" value="취소">
												<input class="button submit" value="등록">
								            </div>
								        </div>                
								    </div>        
								</div>								
							</div>
						</div>
						<div class="reviewContentWrap">
							<div class="reviewContent">
								<div class="idWrap">
									<span class="material-symbols-outlined">account_circle</span>
									<h5>jwlme@***</h5>
								</div>
								<div class="review">								
									<div class="left">
										<h5 class="leftTitle">관람평</h5>
										<p class="reviewCon">너무 재밌었고 배우분들 너무 연기도 짱짱 몰입도짱짱!</p>
									</div>
									<div class="right">
										<span class="material-symbols-outlined">stars</span>
										<h5 class="starNum">8</h5>
									</div>
								</div>
							</div> <!-- reviewContent 끝-->
						</div> <!-- reviewContentWrap 끝-->
					</div>	<!-- reviewWrap 끝-->								
				</div> <!-- content 끝-->
				
				<div class="content reviewDetailInfo">
					<div class="reviewWrap">
						<div class="reviewWrapH">
							<h5 class="reviewWrapTitle"><span class="Strong">${movieInfo.movienm}</span> 에 대한 <span class="Strong">31</span>개의 관람평이 있어요!</h5>
							<div class="write">
								<span class="material-symbols-outlined open-pop">edit_square</span>
								<h5>관람평 작성하기</h5>
								<div class="pop">
								    <div class="popInner">
								        <div class="popH">
								            <h5 class="popTitle">관람평 작성하기</h5>
								            <span class="material-symbols-outlined close-pop">close</span>
								        </div>
								        <div class="popB">
								            <div class="popMovienm">
								                ${movieInfo.movienm}<br>
								                <span style="font-size: 18px; color: #111;">영화 어떠셨나요?<span>
								            </div>
								            <div class="popRating">
								                <div class="rating">
								                    <label class="rating__label rating__label--half" for="starhalf">
								                        <input type="radio" id="starhalf" class="rating__input" name="rating" value="1">
								                        <span class="star-icon">
								                        </span>
								                    </label>
								                    <label class="rating__label rating__label--full" for="star1">
								                        <input type="radio" id="star1" class="rating__input" name="rating" value="2">
								                        <span class="star-icon">
								                        </span>
								                    </label>
								                    <label class="rating__label rating__label--half" for="star1half">
								                        <input type="radio" id="star1half" class="rating__input" name="rating" value="3">
								                        <span class="star-icon"></span>
								                    </label>
								                    <label class="rating__label rating__label--full" for="star2">
								                        <input type="radio" id="star2" class="rating__input" name="rating" value="4">
								                        <span class="star-icon"></span>
								                    </label>
								                    <label class="rating__label rating__label--half" for="star2half">
								                        <input type="radio" id="star2half" class="rating__input" name="rating" value="5">
								                        <span class="star-icon"></span>
								                    </label>
								                    <label class="rating__label rating__label--full" for="star3">
								                        <input type="radio" id="star3" class="rating__input" name="rating" value="6">
								                        <span class="star-icon"></span>
								                    </label>
								                    <label class="rating__label rating__label--half" for="star3half">
								                        <input type="radio" id="star3half" class="rating__input" name="rating" value="7">
								                        <span class="star-icon"></span>
								                    </label>
								                    <label class="rating__label rating__label--full" for="star4">
								                        <input type="radio" id="star4" class="rating__input" name="rating" value="8">
								                        <span class="star-icon"></span>
								                    </label>
								                    <label class="rating__label rating__label--half" for="star4half">
								                        <input type="radio" id="star4half" class="rating__input" name="rating" value="9">
								                        <span class="star-icon"></span>
								                    </label>
								                    <label class="rating__label rating__label--full" for="star5">
								                        <input type="radio" id="star5" class="rating__input" name="rating" value="10">
								                        <span class="star-icon"></span>
								                    </label>
								                </div>
								                <div class="rating-result"><span id="selected-rating"></span>점</div>
								            </div>
								            <div class="textAreaWrap">
								                <textarea name="" id="textArea" placeholder="실관람평을 남겨주세요."></textarea>
								            </div>
								        </div>
								        <div class="popF">
								            <div class="buttonWrap">
								                <input class="button cancel cancel-pop" value="취소">
								                <input class="button submit" value="등록">
								            </div>
								        </div>                
								    </div>        
								</div>								
							</div>
						</div>
						<div class="reviewContentWrap">
							<div class="reviewContent">
								<div class="idWrap">
									<span class="material-symbols-outlined">account_circle</span>
									<h5>jwlme@***</h5>
								</div>
								<div class="review">								
									<div class="left">
										<h5 class="leftTitle">관람평</h5>
										<p class="reviewCon">너무 재밌었고 배우분들 너무 연기도 짱짱 몰입도짱짱!</p>
									</div>
									<div class="right">
										<span class="material-symbols-outlined">stars</span>
										<h5 class="starNum">8</h5>
									</div>
								</div>
							</div> <!-- reviewContent 끝-->
						</div> <!-- reviewContentWrap 끝-->
					</div>	<!-- reviewWrap 끝-->						
				</div> <!-- content reviewDetailInfo 끝-->
				
				<div class="content steelcut">
					<c:choose>
					    <c:when test="${movieInfo.movieyoutube == null || movieInfo.movieyoutube == ''}">
					        <style>
					            .movieYoutube{ display: none; }
					        </style>
					    </c:when>
					    <c:otherwise>
					        <style>
					            .movieYoutube { display: flex; }
					        </style>
					    </c:otherwise>
					</c:choose>										
					<div class="movieYoutube">						
						<iframe src="${movieInfo.movieyoutube}"></iframe>
					</div>
					<h5 class="reviewWrapTitle"><span class="Strong">${movieInfo.movienm}</span> 에 대한 스틸컷이 있어요!</h5>
					<div class="steelcutYes">
						
						<div class="steelcutCon"></div>
					</div>				
					<div class="steelcutNo">
						
					</div>				
				</div>	
				
			</div>	<!-- contentWrap 끝-->		
		</div> <!-- movieList 끝--> 		
    </section>	
	<%@ include file="../footer.jsp" %>	
</body>
</html>

<script>	
	// 탭버튼 활성화
	const tabs = document.querySelectorAll('.tab');
	const contentCons = document.querySelectorAll('.content');
	
	tabs.forEach((tab, index) => {
	  tab.addEventListener("click", () => {
	    // 모든 탭에서 active 클래스를 제거
	    tabs.forEach(t => t.classList.remove('active'));

	    // 클릭한 탭에만 active 클래스를 추가
	    tab.classList.add('active');

	    // 모든 rankingCon에서 on 클래스를 제거
	    contentCons.forEach(contentCon => contentCon.classList.remove('on'));

	    // 클릭한 탭에 대응하는 rankingCon에 on 클래스를 추가
	    contentCons[index].classList.add('on');
	  });
	});
	
	// 무비 장르를 짤라서 나열하기
	const genre = "${movieInfo.genre}";
	const genres = genre.split(',');
	// genre가 null 또는 빈 문자열이 아닌 경우에만 처리
	if (genre) {
	    // genre를 쉼표로 나누어 배열로 만듭니다.
	    const genres = genre.split(',');

		console.log(genres);
	    // 문자열을 만들기 위한 변수
	    let str = "";

	    // genres 배열을 순회하여 HTML을 생성합니다.
		for (let i = 0; i < genres.length; i++) 
			{		        
		        
				str += "<input type='button' class='genre' value='"+genres[i].trim()+"' disabled>"; 
		    }

	    // .genreWrap 요소의 HTML을 생성한 문자열로 업데이트합니다.
	    $(".genreWrap").html(str);
	} else {
	    // genre가 없거나 빈 문자열일 경우 .genreWrap을 숨깁니다.
	    $(".genreWrap").css("display", "none");
	}
	
	// 줄거리(intro)를 .를 기준으로 <br>를 넣기
	const text = "${movieInfo.intro}";
	const formattedText = text.replace(/\. /g, '.<br>');
	document.getElementById("overview").innerHTML = formattedText;
	
	
	// 무비 스틸컷을 짤라서 나열하기
	const steelcut = "${movieInfo.steelcut}";
	const steelcuts = steelcut ? steelcut.split(',') : [];

	// steelcuts 배열이 유효한지 확인
	if (steelcuts.length > 0) {
	    let str = "";

	    for (let i = 0; i < steelcuts.length; i++) {
	        const imageUrl = steelcuts[i].trim();
	        if (imageUrl) { // URL이 빈 문자열이 아닌지 확인
	            // 문자열 결합 방식으로 img 태그 생성
	            str += "<div class='steelcutCon'><img src='" + imageUrl + "' alt='Steelcut Image'></div>";
	        }
	    }

	    // .steelcutYes 요소의 HTML을 업데이트
	    $(".steelcutYes").html(str);
	} else {
	    // steelcut이 없을 경우 요소를 숨김
	    $(".steelcutYes").css("display", "none");
	}		
</script>
<script>
    const stars = document.querySelectorAll('.star-icon');
    const result = document.getElementById('selected-rating');
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
</script>
<script>
	document.addEventListener('DOMContentLoaded', () => {
	    const openPopButtons = document.querySelectorAll('.open-pop');
	    const closePopButtons = document.querySelectorAll('.close-pop, .cancel-pop');
	    const popElements = document.querySelectorAll('.pop');

	    // Function to show the pop-up
	    const showPop = () => {
	        popElements.forEach(pop => pop.style.display = 'flex');
	    };

	    // Function to hide the pop-up
	    const hidePop = () => {
	        popElements.forEach(pop => pop.style.display = 'none');
	    };

	    // Add click event listener to "관람평 작성하기" buttons
	    openPopButtons.forEach(button => {
	        button.addEventListener('click', showPop);
	    });

	    // Add click event listener to close and cancel buttons
	    closePopButtons.forEach(button => {
	        button.addEventListener('click', hidePop);
	    });
	});		
</script>
