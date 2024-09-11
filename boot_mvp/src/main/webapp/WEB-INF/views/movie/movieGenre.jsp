<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>장르별영화</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/movieGenre.css">
	
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
			
            <div class="tabWrap">
                <div class="status">
					<h5 class="tab active">
                        장르별 상영영화
                    </h5>                    
                </div> 
				<form method="GET" action="/movie/movieGenre" id="genreForm">
					<input type="hidden" id="csrfTokenId" name="${_csrf}" value="${_csrf.token}"/> 
					<select name="genre" onchange="submitForm()">
				        <c:forEach items="${genres}" var="dto">								
							<option value="${dto.genrenm}" 
				                <c:if test="${genre eq dto.genrenm}">selected</c:if>
				                <c:if test="${genre eq '' && dto.genrenm eq '액션'}">selected</c:if>>
				                ${dto.genrenm}
				            </option>
				        </c:forEach>						
				    </select>					
				</form>             				            
            </div><!-- tabWrap 끝-->  
			<div class="ranking">
				<div class="rankingCon boxOffice on">
					<c:forEach items="${GenreList}" var="dto">
						<div class="movieDiv">
							<img src="${dto.moviepostimg}" alt="${dto.movienm}">
							
							<div class="movieInfo">								
								<div class="movieInfoDetail">									
									<div>
										<c:choose>
										    <c:when test="${fn:length(dto.movienm) > 20}">
										        <h5 class="movienm" style="letter-spacing: -2px;">${dto.movienm}</h5>
										    </c:when>
										    <c:otherwise>
										        <h5 class="movienm">${dto.movienm}</h5>
										    </c:otherwise>
										</c:choose>
										<div class="option">
											<h5 class="releaseday">${dto.openday}<h5>											
										</div>
									</div>
								</div>
								<div class="buttonWrap">
									<button class="active" onclick="location.href=''">예매하기</button>
									<button onclick="location.href='../movie/movieInfo?movieno=${dto.movieno}'">상세보기</button>
								</div>
							</div>
						</div>
					</c:forEach>
				</div><!-- boxOffice 끝-->			
									
			</div>  <!-- ranking 끝-->
		</div> <!-- movieList 끝--> 		
    </section>	
	<%@ include file="../footer.jsp" %>	
</body>
</html>

<script>
	const tabs = document.querySelectorAll('.tab');
	const rankingCons = document.querySelectorAll('.rankingCon');
	
	tabs.forEach((tab, index) => 
	{
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
	
	function submitForm() 
	{
		var form = document.getElementById('genreForm');

		// 폼을 제출
		form.submit();
	}
</script>