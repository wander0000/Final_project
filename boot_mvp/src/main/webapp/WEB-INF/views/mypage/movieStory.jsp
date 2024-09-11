<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MyMVP_무비스토리</title>
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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage/default.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage/movieStory.css">
	<script src="${pageContext.request.contextPath}/js/mypage/movieStory.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/header.jsp" %>
    <section class="section">
      <%@ include file="mypageHeader.jsp" %>
        <div class="mainContainer">
            <div class="mainContainerContent">
                <h3 class="menuTitle">
                    무비스토리
                </h3>         
                <div class="mainContent">
                    <div class="tabConDiv">
                        <div class="tabCon active" data-tab="watched">본영화</div>
                        <div class="tabCon" data-tab="comment">관람평</div>
                        <div class="tabCon" data-tab="wanted">보고싶어</div>
                        <div class="tabCon" data-tab="partner">파트너스</div>
                    </div>
                   
                    <div class="contentDiv">

                        <div class="contentCon watched active">
                            <div class="movieList">
                            
                                <div class="detailCon">
                                    <div class="movieDetail">
                                        <img src="${pageContext.request.contextPath}/images/common.jfif" alt="#">
                                        <div infoCon>
                                            <div class="movieConM">
                                                <h3 class="movieTitle">나는 니가 지난 여름에 한 일을 알고 있다.</h3>
                                                <div class="detailInfo">
                                                    <div>2024-08-01</div>
                                                    <div>MVP 서면 3관</div>
                                                    <div>2명</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div><!-- movieDetail  영화 하나 끝 -->
                                    <button class="commentBtn" type="submit">관람평남기기</button>
                                </div><!-- detailCon  영화 하나 끝 -->
                                
                                <div class="detailCon">
                                    <div class="movieDetail">
                                        <img src="${pageContext.request.contextPath}/images/common.jfif" alt="#">
                                        <div infoCon>
                                            <div class="movieConM">
                                                <h3 class="movieTitle">행복의 나라</h3>
                                                <div class="detailInfo">
                                                    <div>2024-08-01</div>
                                                    <div>MVP 서면 3관</div>
                                                    <div>2명</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div><!-- movieDetail  영화 하나 끝 -->
                                    <button class="commentBtn" type="submit">관람평남기기</button>
                                </div><!-- detailCon  영화 하나 끝 -->
                                <div class="detailCon">
                                    <div class="movieDetail">
                                        <img src="${pageContext.request.contextPath}/images/common.jfif" alt="#">
                                        <div infoCon>
                                            <div class="movieConM">
                                                <h3 class="movieTitle">행복의 나라</h3>
                                                <div class="detailInfo">
                                                    <div>2024-08-01</div>
                                                    <div>MVP 서면 3관</div>
                                                    <div>2명</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div><!-- movieDetail  영화 하나 끝 -->
                                    <button class="commentBtn" type="submit">관람평남기기</button>
                                </div><!-- detailCon  영화 하나 끝 -->
                                <div class="detailCon">
                                    <div class="movieDetail">
                                        <img src="${pageContext.request.contextPath}/images/common.jfif" alt="#">
                                        <div infoCon>
                                            <div class="movieConM">
                                                <h3 class="movieTitle">행복의 나라</h3>
                                                <div class="detailInfo">
                                                    <div>2024-08-01</div>
                                                    <div>MVP 서면 3관</div>
                                                    <div>2명</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div><!-- movieDetail  영화 하나 끝 -->
                                    <button class="commentBtn" type="submit">관람평남기기</button>
                                </div><!-- detailCon  영화 하나 끝 -->

                            </div><!-- movieList 끝 -->
                        </div><!-- contentCon watched (본영화) 끝 -->  


                        <div class="contentCon comment">
                            <div class="commentList">
                                <div class="commentDetail">
                                    <div class="commentTitle">행복의 나라</div>
                                    <div class="commentTime">2024-08-27</div>
                                    <div class="commentBody">너무 재밌었고 배우분들 너무 연기도 짱짱 몰입감도 짱짱 !! </div>
                                    <div class="commentStar">
                                        <span class="icon">
                                            <i class="fa-solid fa-star"></i>
                                        </span>
                                        <div class="commentStarRate">10</div>
                                    </div>
                                </div><!-- commentDetail 관람평 하나 끝 -->
                                <div class="commentDetail">
                                    <div class="commentTitle">행복의 나라</div>
                                    <div class="commentTime">2024-08-27</div>
                                    <div class="commentBody">너무 재밌었고 배우분들 너무 연기도 짱짱 몰입감도 짱짱 !!너무 재밌었고 배우분들 너무 연기도 짱짱 몰입감도 짱짱 !!너무 재밌었고 배우분들 너무 연기도 짱짱 몰입감도 짱짱 !!</div>
                                    <div class="commentStar">
                                        <span class="icon">
                                            <i class="fa-solid fa-star"></i>
                                        </span>
                                        <div class="commentStarRate">1</div>
                                    </div>
                                </div><!-- commentDetail 관람평 하나 끝 -->
                                <div class="commentDetail">
                                    <div class="commentTitle">나는 니가 지난 여름에 한 일을 알고 있다.</div>
                                    <div class="commentTime">2024-08-27</div>
                                    <div class="commentBody">너무 재밌었고 배우분들 너무 연기도 짱짱 몰입감도 짱짱 !! </div>
                                    <div class="commentStar">
                                        <span class="icon">
                                            <i class="fa-solid fa-star"></i>
                                        </span>
                                        <div class="commentStarRate">8</div>
                                    </div>
                                </div><!-- commentDetail 관람평 하나 끝 -->
                            </div> <!-- commentList 관람평 리스트 끝 -->
                        </div><!-- contentCon comment 끝 -->  



                        <div class="contentCon wanted">
                            <div class="movieList">
                            
                                <div class="detailCon">
                                    <div class="movieDetail">
                                        <img src="${pageContext.request.contextPath}/images/common.jfif" alt="#">
                                        <div class="movieConM">
                                            <h3 class="movieTitle">나는 니가 지난 여름에 한 일을 알고 있다.</h3>
                                        </div>
                                        <div class="movieConB">
                                            <div class="scrapCon">
                                                <span class="iconHeart">
                                                    <i class="fa-regular fa-heart"></i>
                                                </span>
                                                <div class="scrapRate">128</div>
                                            </div>
                                            <button class="bookingBtn" type="submit">예매하기</button>
                                        </div>
                                    </div><!-- movieDetail  영화 하나 끝 -->
                                </div><!-- detailCon  영화 하나 끝 -->


                                <div class="detailCon">
                                    <div class="movieDetail">
                                        <img src="${pageContext.request.contextPath}/images/common.jfif" alt="#">
                                        <div class="movieConM">
                                            <h3 class="movieTitle">행복의 나라</h3>
                                        </div>
                                        <div class="movieConB">
                                            <div class="scrapCon">
                                                <span class="iconHeart">
                                                    <i class="fa-regular fa-heart"></i>
                                                </span>
                                                <div class="scrapRate">12888</div>
                                            </div>
                                            <button class="bookingBtn" type="submit">예매하기</button>
                                        </div>
                                    </div><!-- movieDetail  영화 하나 끝 -->
                                </div><!-- detailCon  영화 하나 끝 -->
                                <div class="detailCon">
                                    <div class="movieDetail">
                                        <img src="${pageContext.request.contextPath}/images/common.jfif" alt="#">
                                        <div class="movieConM">
                                            <h3 class="movieTitle">행복의 나라</h3>
                                        </div>
                                        <div class="movieConB">
                                            <div class="scrapCon">
                                                <span class="iconHeart">
                                                    <i class="fa-regular fa-heart"></i>
                                                </span>
                                                <div class="scrapRate">128</div>
                                            </div>
                                            <button class="bookingBtn" type="submit">예매하기</button>
                                        </div>
                                    </div><!-- movieDetail  영화 하나 끝 -->
                                </div><!-- detailCon  영화 하나 끝 -->
                                <div class="detailCon">
                                    <div class="movieDetail">
                                        <img src="${pageContext.request.contextPath}/images/common.jfif" alt="#">
                                        <div class="movieConM">
                                            <h3 class="movieTitle">행복의 나라</h3>
                                        </div>
                                        <div class="movieConB">
                                            <div class="scrapCon">
                                                <span class="iconHeart">
                                                    <i class="fa-regular fa-heart"></i>
                                                </span>
                                                <div class="scrapRate">128</div>
                                            </div>
                                            <button class="bookingBtn" type="submit">예매하기</button>
                                        </div>
                                    </div><!-- movieDetail  영화 하나 끝 -->
                                </div><!-- detailCon  영화 하나 끝 -->
                                
                                
                            </div><!-- movieList 끝 -->
                        </div><!-- contentCon wanted (보고싶어) 끝 -->  

                        
                        <div class="contentCon partner"><!--partner (파트너스) -->  
                            <div class="partnerList">


                                <div class="partnerDetail">
                                    <div class="partnerDate">2024-08-27</div>
                                    <div class="partnerMember">
                                        <span class="icon">
                                            <i class="fa-solid fa-user-group"></i>
                                        </span>
                                        <div class="memberNum">1/4</div>
                                    </div>
                                    <div class="partnerTheater">
                                        <span class="icon">
                                            <i class="fa-solid fa-location-dot"></i>
                                        </span>
                                        <div class="memberNum">프리미엄해운대(장산역)</div>
                                    </div>
                                    <div class="movieName">
                                        <div class="movie">[</div>
                                        <div class="movie title">행복의 나라</div>
                                        <div class="movie">]</div>
                                    </div>
                                    <div class="partnerTitle">같이 영화 볼 사람~~</div>
                                    <div class="buttonCon">
                                        <button class="messageAlert" type="submit">안읽은 메세지 +2</button>
                                        <button class="modify" type="submit">수정</button>
                                        <button class="delete" type="submit">삭제</button>
                                        <button class="state" type="submit">모집중</button>
                                    </div>
                                </div><!-- partnerDetail 파트너스 하나 끝 -->




                                <div class="partnerDetail">
                                    <div class="partnerDate">2024-08-27</div>
                                    <div class="partnerMember">
                                        <span class="icon">
                                            <i class="fa-solid fa-user-group"></i>
                                        </span>
                                        <div class="memberNum">1/4</div>
                                    </div>
                                    <div class="partnerTheater">
                                        <span class="icon">
                                            <i class="fa-solid fa-location-dot"></i>
                                        </span>
                                        <div class="memberNum">해운대</div>
                                    </div>
                                    <div class="movieName">
                                        <div class="movie">[</div>
                                        <div class="movie title">나는 니가 지난 여름에 한 일을 알고 있다.</div>
                                        <div class="movie">]</div>
                                    </div>
                                    <div class="partnerTitle">영화보자, 영화보자,영화보자, 영화보자,영화보자, 영화보자,영화보자, 영화보자,영화보자, 영화보자,</div>
                                    <div class="buttonCon">
                                        <button class="messageAlert closed" type="submit">안읽은 메세지 +2</button>
                                        <button class="modify closed" type="submit">수정</button>
                                        <button class="delete" type="submit">삭제</button>
                                        <button class="state mojipClosed" type="submit">모집종료</button>
                                    </div>
                                </div><!-- partnerDetail 파트너스 하나 끝 -->
                              
                                <div class="partnerDetail">
                                    <div class="partnerDate">2024-08-27</div>
                                    <div class="partnerMember">
                                        <span class="icon">
                                            <i class="fa-solid fa-user-group"></i>
                                        </span>
                                        <div class="memberNum">1/4</div>
                                    </div>
                                    <div class="partnerTheater">
                                        <span class="icon">
                                            <i class="fa-solid fa-location-dot"></i>
                                        </span>
                                        <div class="memberNum">프리미엄해운대(장산역)</div>
                                    </div>
                                    <div class="movieName">
                                        <div class="movie">[</div>
                                        <div class="movie title">행복의 나라</div>
                                        <div class="movie">]</div>
                                    </div>
                                    <div class="partnerTitle">같이 영화 볼 사람~~</div>
                                    <div class="buttonCon">
                                        <button class="messageAlert" type="submit">안읽은 메세지 +99</button>
                                        <button class="modify" type="submit">수정</button>
                                        <button class="delete" type="submit">삭제</button>
                                        <button class="state" type="submit">모집중</button>
                                    </div>
                                </div><!-- partnerDetail 파트너스 하나 끝 -->
                              
                                <div class="partnerDetail">
                                    <div class="partnerDate">2024-08-27</div>
                                    <div class="partnerMember">
                                        <span class="icon">
                                            <i class="fa-solid fa-user-group"></i>
                                        </span>
                                        <div class="memberNum">1/4</div>
                                    </div>
                                    <div class="partnerTheater">
                                        <span class="icon">
                                            <i class="fa-solid fa-location-dot"></i>
                                        </span>
                                        <div class="memberNum">프리미엄해운대(장산역)</div>
                                    </div>
                                    <div class="movieName">
                                        <div class="movie">[</div>
                                        <div class="movie title">행복의 나라</div>
                                        <div class="movie">]</div>
                                    </div>
                                    <div class="partnerTitle">같이 영화 볼 사람~~</div>
                                    <div class="buttonCon">
                                        <!-- <button class="messageAlert" type="submit">안읽은 메세지 +2</button> -->
                                        <button class="messageAlert" type="submit">안읽은 메세지 +2</button>
                                        <button class="modify" type="submit">수정</button>
                                        <button class="delete" type="submit">삭제</button>
                                        <button class="state" type="submit">모집중</button>
                                    </div>
                                </div><!-- partnerDetail 파트너스 하나 끝 -->
                              


                            </div> <!-- commentList 리스트 끝 -->
                        </div><!-- contentCon partner (파트너스) 끝 -->  


                        





                    </div>  <!-- contentDiv 끝 -->  
                </div><!-- mainContent -->

                
            </div><!-- mainContainerContent -->
        </div> <!-- mainContainer -->
    </section>
	<%@ include file="/WEB-INF/views/footer.jsp" %>
</body>
</html>

